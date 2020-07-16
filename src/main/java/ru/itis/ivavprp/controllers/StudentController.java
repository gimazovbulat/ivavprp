package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.services.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private final SearchService searchService;
    private final StudentService studentService;

    public StudentController(SearchService searchService, StudentService studentService1) {
        this.searchService = searchService;
        this.studentService = studentService1;
    }

    @GetMapping("/students")
    @ResponseBody
    public List<VacancyDto> findAllBySpecification(@RequestParam(value = "search", required = false) String search,
                                                   int page,
                                                   int size) {
        List<?> results = searchService.getStudentsResults(search, page, size);
        return (List<VacancyDto>) results;
    }

}
