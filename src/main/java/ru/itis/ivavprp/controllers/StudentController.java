package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private final SearchService searchService;
    private final StudentService studentService;
    private final UserDetailsService userDetailsService;

    public StudentController(SearchService searchService, StudentService studentService1, @Qualifier("userService") UserDetailsService userDetailsService) {
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

        Student student = (Student) userDetails;
        return ResponseEntity.ok(student.toString());
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/students/{id}/skills")
    public ResponseEntity<List<SkillDto>> addSkills(@PathVariable("id") Long id, @RequestBody List<Long> skillsIds, @CurrentUser UserDetails userDetails) {
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
    public ResponseEntity<List<SkillDto>> removeSkills(@PathVariable("id") Long id, @RequestBody List<Long> skillsIds, @CurrentUser UserDetails userDetails) {
        Student student = (Student) userDetails;
        if (student.getId().equals(id)) {
            List<SkillDto> savedSkills = studentService.removeSkills(Student.toStudentDto(student), skillsIds);
            return ResponseEntity.ok(savedSkills);
        }
        throw new IllegalStateException();
    }
}
