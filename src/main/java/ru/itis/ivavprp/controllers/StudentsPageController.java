package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentsPageController {

    @GetMapping("/students/{id}")
    public String getPage(@PathVariable String id){
        return "profile_stud";
    }


    @GetMapping("/students/{id}/edit")
    public String getRedactPage(@PathVariable String id){
        return "profile_redact";
    }
}
