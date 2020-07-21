package ru.itis.ivavprp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyEditPageController {
    @GetMapping("/company_page/{id}/edit")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String getPage(@PathVariable("id") Long id, Model model) {
        return "companyEdit";
    }
}
