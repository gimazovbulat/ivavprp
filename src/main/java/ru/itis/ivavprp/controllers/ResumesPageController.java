package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResumesPageController {
    @GetMapping("/resumes_page")
    public String getPage() {
        return "resumes_search";
    }

    @GetMapping("/resume/{id}")
    public String getResumePage(@PathVariable("id") long id) {
        return "resume";
    }

    @GetMapping("/resume/add")
    public String getResumeEditPage() {
        return "resume_redact";
    }
}
