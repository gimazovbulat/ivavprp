package ru.itis.ivavprp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.services.AuthService;

@RestController
public class LogoutController {
    private final AuthService authService;

    public LogoutController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public void logout(@RequestHeader("AUTH") String auth) {
        authService.logout(auth);
        return;
    }
}
