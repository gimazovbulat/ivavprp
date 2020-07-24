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
public class VacanciesPageController {
    @GetMapping("/vacancies/{id}")
    public String getVacancyPage(@PathVariable("id") long id) {
        return "offer";
    }

    @GetMapping("/vacancies/add")
    public String getVacancyEditPage(@CurrentUser UserDetails userDetails, Model model) {
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
        } else {
            model.addAttribute("isCompany", false);
        }
        return "offer_new";
    }
}
