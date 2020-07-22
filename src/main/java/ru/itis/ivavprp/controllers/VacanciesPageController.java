package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VacanciesPageController {
    @GetMapping("/vacancies/{id}")
    public String getVacancyPage(@PathVariable("id") long id) {
        return "offer";
    }

    @GetMapping("/vacancies/add")
    public String getVacancyEditPage() {
        return "offer_new";
    }
}
