package ru.itis.ivavprp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.AuthenticationRequestDto;
import ru.itis.ivavprp.dto.LoginDto;
import ru.itis.ivavprp.dto.TokenDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.services.LoginService;
import ru.itis.ivavprp.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }
}