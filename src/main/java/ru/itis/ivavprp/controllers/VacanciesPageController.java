package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VacanciesPageController {
    @GetMapping("/vacancies_page")
    public String getPage(){
        return "vacancies_search";
    }
}
