package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPage {
    @GetMapping("/admin")
    public String getPage() {
        return "admin_page";
    }
}
