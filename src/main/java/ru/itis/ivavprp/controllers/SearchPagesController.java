package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchPagesController {
    @GetMapping("/vacancies_search_page")
    public String getVacanciesPage(){
        return "vacancies_search";
    }

    @GetMapping("/students_search_page")
    public String getStudentsPage(){
        return "students_search";
    }

    @GetMapping("/resumes_search_page")
    public String getPage(){
        return "resumes_search";
    }
}
