package ru.itis.ivavprp.controllers;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.security.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchPagesController {
    @GetMapping("/vacancies_search_page")
    public String getVacanciesPage(@CurrentUser UserDetails userDetails, Model model) {
        User user = (User) userDetails;
        if (userDetails != null) {
            model.addAttribute("user", user);
            List<String> authoritiesNames = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            if (authoritiesNames.contains("COMPANY")) {
                model.addAttribute("isCompany", true);
            } else {
                model.addAttribute("isCompany", false);
            }
            if (authoritiesNames.contains("STUDENT")) {
                model.addAttribute("isStudent", true);
            } else {
                model.addAttribute("isStudent", false);
            }
            if (authoritiesNames.contains("TEACHER")) {
                model.addAttribute("isTeacher", true);
            } else {
                model.addAttribute("isTeacher", false);
            }
        } else {
            model.addAttribute("isCompany", false);
        }
        return "vacancies_search";
    }

    @GetMapping("/students_search_page")
    public String getStudentsPage() {
        return "students_search";
    }

    @GetMapping("/resumes_search_page")
    public String getPage(@CurrentUser UserDetails userDetails, Model model) {
        User user = (User) userDetails;
        if (userDetails != null) {
            model.addAttribute("user", user);
            List<String> authoritiesNames = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            if (authoritiesNames.contains("COMPANY")) {
                model.addAttribute("isCompany", true);
            } else {
                model.addAttribute("isCompany", false);
            }
            if (authoritiesNames.contains("STUDENT")) {
                model.addAttribute("isStudent", true);
            } else {
                model.addAttribute("isStudent", false);
            }
            if (authoritiesNames.contains("TEACHER")) {
                model.addAttribute("isTeacher", true);
            } else {
                model.addAttribute("isTeacher", false);
            }
        } else {
            model.addAttribute("isCompany", false);
        }
        return "resumes_search";
    }
}
