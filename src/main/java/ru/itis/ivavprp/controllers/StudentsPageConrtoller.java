package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentsPageConrtoller {

    @GetMapping("/students/{id}")
    public String getPage(@PathVariable String id){
        return "profile_stud";
    }
}
