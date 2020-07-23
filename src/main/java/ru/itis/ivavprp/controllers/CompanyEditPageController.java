package ru.itis.ivavprp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
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
public class CompanyEditPageController {
    @GetMapping("/company_page/{id}/edit")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String getPage(@PathVariable("id") Long id, Model model, @CurrentUser UserDetails userDetails) {
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
        return "companyEdit";
    }
}
