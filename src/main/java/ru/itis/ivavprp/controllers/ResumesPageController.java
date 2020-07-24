package ru.itis.ivavprp.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.security.CurrentUser;

import javax.jws.WebParam;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResumesPageController {
    @GetMapping("/resumes_page")
    public String getPage() {
        return "resumes_search";
    }

    @GetMapping("/resume/{id}")
    public String getResumePage(@PathVariable("id") long id, @CurrentUser UserDetails userDetails, Model model) {
        User user = (User) userDetails;
        if (userDetails != null) {
            model.addAttribute("user", user);
        }
        return "resume";
    }

    @GetMapping("/resume/add")
    public String getResumeEditPage(@CurrentUser UserDetails userDetails, Model model) {
        User user = (User) userDetails;
        if (userDetails != null) {
            model.addAttribute("user", user);
        }
        return "resume_redact";
    }
}
