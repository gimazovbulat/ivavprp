package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumesPageController {
    @GetMapping("/resumes_page")
    public String getPage(){
        return "resumes_search";
    }
}
