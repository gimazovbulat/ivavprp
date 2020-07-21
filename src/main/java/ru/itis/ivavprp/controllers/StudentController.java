package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.StudentServiceImpl;

import java.util.List;

@RestController
public class StudentController {
    private final SearchService searchService;
    private final StudentServiceImpl studentService;

    public StudentController(SearchService searchService, StudentServiceImpl studentService) {
        this.searchService = searchService;
        this.studentService = studentService;
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

    @GetMapping("/restApi/students/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable("id") Long id) {
        System.out.println("qq");
        return ResponseEntity.ok(studentService.findStudentById(id));
    }
}
