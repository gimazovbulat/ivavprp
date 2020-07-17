package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.LoginDto;
import ru.itis.ivavprp.dto.TokenDto;
import ru.itis.ivavprp.security.authentication.TokenAuthentication;
import ru.itis.ivavprp.services.AuthService;

@RestController
public class SignInController {

    @Autowired
    private AuthService service;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenAuthentication tokenAuthentication;


    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }
}