package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VacanciesSearchPageController {
    @GetMapping("/vacancies_search_page")
    public String getPage(){
        return "vacancies_search";
    }
}
