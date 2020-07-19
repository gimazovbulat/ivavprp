package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}
