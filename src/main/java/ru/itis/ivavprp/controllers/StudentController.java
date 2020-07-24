package ru.itis.ivavprp.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.SkillForm;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private final SearchService searchService;
    private final StudentService studentService;
    private final UserDetailsService userDetailsService;

    public StudentController(SearchService searchService,
                             StudentService studentService1,
                             @Qualifier("userService") UserDetailsService userDetailsService) {
        this.searchService = searchService;
        this.studentService = studentService1;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/students")
    @ResponseBody
    public List<VacancyDto> findAllBySpecification(@RequestParam(value = "search", required = false) String search,
                                                   int page,
                                                   int size) {
        List<?> results = searchService.getStudentsResults(search, page, size);
        return (List<VacancyDto>) results;
    }

    @GetMapping("/students/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<String> getProfilePage(@CurrentUser UserDetails userDetails) {
        Student secStudent = (Student) userDetails;
        StudentDto student = studentService.findStudentById(secStudent.getId());
        return ResponseEntity.ok(student.toString());
    }

    @GetMapping("/students/{id}/skills")
    public ResponseEntity<List<SkillDto>> getSkills(@PathVariable("id") Long id,
                                                    @RequestParam(required = false) Boolean confirmed) {
        StudentDto student = studentService.findStudentById(id);
        if (confirmed == null) {
            return ResponseEntity.ok(student.getSkills());
        } else if (!confirmed) {
            return ResponseEntity.ok(student.getSkills().stream()
                    .filter(skill -> !skill.isConfirmed()).
                            collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(student.getSkills().stream()
                    .filter(SkillDto::isConfirmed).
                            collect(Collectors.toList()));
        }
    }

    @GetMapping("/students/skills")
    public ResponseEntity<List<SkillDto>> getCurrentStudentSkills(@CurrentUser User user,
                                                                  @RequestParam(required = false) Boolean confirmed) {
        StudentDto student = studentService.findStudentById(user.getId());
        if (confirmed == null) {
            return ResponseEntity.ok(student.getSkills());
        } else if (!confirmed) {
            return ResponseEntity.ok(student.getSkills().stream()
                    .filter(skill -> !skill.isConfirmed()).
                            collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(student.getSkills().stream()
                    .filter(SkillDto::isConfirmed).
                            collect(Collectors.toList()));
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/students/{id}/skills")
    public ResponseEntity<List<SkillDto>> addSkills(@PathVariable("id") Long id,
                                                    @RequestBody List<Long> skillsIds,
                                                    @CurrentUser UserDetails userDetails) {
        System.out.println(skillsIds);
        Student student = (Student) userDetails;
        if (student.getId().equals(id)) {
            List<SkillDto> savedSkills = studentService.addSkills(Student.toStudentDto(student), skillsIds);
            return ResponseEntity.ok(savedSkills);
        }
        throw new IllegalStateException();
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @DeleteMapping("/students/{id}/skills")
    public ResponseEntity<List<SkillDto>> removeSkills(@PathVariable("id") Long id,
                                                       @RequestBody List<Long> skillsIds,
                                                       @CurrentUser UserDetails userDetails) {
        Student student = (Student) userDetails;
        if (student.getId().equals(id)) {
            List<SkillDto> savedSkills = studentService.removeSkills(Student.toStudentDto(student), skillsIds);
            return ResponseEntity.ok(savedSkills);
        }
        throw new IllegalStateException();
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/resumes/{id}/skills")
    public ResponseEntity<List<SkillDto>> addSkillsToResume(@PathVariable("id") Long resumeId,
                                                            @RequestBody List<Long> skillIds,
                                                            @CurrentUser UserDetails userDetails) {
        Student student = (Student) userDetails;
        List<SkillDto> addedSkills = studentService.addSkillsToResume(student.getId(), resumeId, skillIds);
        return ResponseEntity.ok(addedSkills);
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @DeleteMapping("/resumes/{id}/skills")
    public ResponseEntity<List<SkillDto>> removeSkillsToResume(@PathVariable("id") Long resumeId,
                                                               @RequestBody List<Long> skillIds,
                                                               @CurrentUser UserDetails userDetails) {
        Student student = (Student) userDetails;
        List<SkillDto> addedSkills = studentService.removeSkillsToResume(student.getId(), resumeId, skillIds);
        return ResponseEntity.ok(addedSkills);
    }

    @GetMapping("/restApi/students/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PostMapping("/restApi/students/add")
    public void addSkillsToStudent(@RequestBody SkillForm skill) {
        studentService.addSkills(studentService.findStudentById(skill.getStudentId()), skill.getSkills());
    }

    @GetMapping("/restApi/students/{studentId}/{skillId}")
    public void removeSkillsFromStudent(@PathVariable("studentId") Long studentId, @PathVariable("skillId") Long skillId, HttpServletResponse response) throws IOException {
        List<Long> removeSkills = new ArrayList<>();
        removeSkills.add(skillId);
        studentService.removeSkills(studentService.findStudentById(studentId), removeSkills);
        response.sendRedirect("/students/" + studentId);
    }
    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/restApi/students/{id}/edit")
    public void updateStudent(@PathVariable("id") Long id,@RequestBody StudentDto student, HttpServletResponse response) throws IOException {
        System.out.println(student);
        System.out.println(id);
        studentService.update(id,student);
        System.out.println("Hello");
        response.sendRedirect("/students/"+id);
        System.out.println("Hello");
    }
}
