package ru.itis.ivavprp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationPageController {

    @GetMapping("/notif/comp/{id}")
    public String getPageForCompany(@PathVariable String id){
        return "notif_for_company";
    }

    @GetMapping("/notif/stud/{id}")
    public String getPageForStudent(@PathVariable String id){
        return "notif_for_students";
    }
}
