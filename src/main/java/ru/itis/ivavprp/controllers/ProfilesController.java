package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilesController {

    private final UserDetailsService userDetailsService;

    public ProfilesController(@Qualifier("userService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

@GetMapping("/profile")
    public ResponseEntity<String> getProfilePage(@RequestHeader("AUTH") String auth) {

        return ResponseEntity.ok(String.valueOf(userDetailsService.loadUserByUsername(auth)));
    }
}
