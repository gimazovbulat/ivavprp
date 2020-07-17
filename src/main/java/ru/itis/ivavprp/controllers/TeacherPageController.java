package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeacherPageController {


    @GetMapping("/teachers/{id}")
    public String getPage(@PathVariable String id) {
        return "profile_teach";
    }

    @GetMapping("/teacher/edit")
    public String getEditPage() {
        return "teacher_edit";
    }


}
