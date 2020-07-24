package ru.itis.ivavprp.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.security.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentsPageController {


    @GetMapping("/students/{id}/edit")
    public String getRedactPage(@PathVariable Long id, Model model, @CurrentUser UserDetails userDetails) {
        User user = (User) userDetails;
        if (userDetails != null) {
            List<String> authoritiesNames = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            model.addAttribute("user", user);
            if (authoritiesNames.contains("STUDENT")) {
                model.addAttribute("isStudent", true);
            } else {
                model.addAttribute("isStudent", false);
            }
        } else {
            model.addAttribute("isStudent", false);
        }
        return "profile_redact";
    }

    @GetMapping("/students/{id}")
    public String getPage(@PathVariable("id") Long id, Model model, @CurrentUser UserDetails userDetails) {
        User user = (User) userDetails;
        if (userDetails != null) {
            List<String> authoritiesNames = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            model.addAttribute("user", user);
            if (authoritiesNames.contains("STUDENT")) {
                model.addAttribute("isStudent", true);
            } else {
                model.addAttribute("isStudent", false);
            }
        } else {
            model.addAttribute("isStudent", false);
        }
        return "profile_stud";
    }
}
