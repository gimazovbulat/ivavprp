package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.User;

@RestController
public class ProfilesController {
    private final UserDetailsService userDetailsService;
    public ProfilesController(@Qualifier("userService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/profile")
    public ResponseEntity<String> getProfilePage(@RequestHeader("AUTH") String auth) {

        //получение юзера из SecurityContext
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //получение юзера из сервиса
        return ResponseEntity.ok(user.toString());
    }
}