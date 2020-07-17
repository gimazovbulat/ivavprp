package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpPageController {
    @GetMapping("/signUp")
    public String getPage() {
        return "sign_up";
    }
}

