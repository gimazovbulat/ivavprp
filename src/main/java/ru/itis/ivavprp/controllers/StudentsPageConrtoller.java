package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsPageConrtoller {
    @GetMapping("/students_page")
    public String getPage(){
        return "students_search";
    }
}
