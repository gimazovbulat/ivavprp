package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SignUpDto;
import ru.itis.ivavprp.services.SignUpService;

@RestController
public class SignUpController {
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/reg")
    public void save(@RequestBody SignUpDto signUpDto) {
        signUpService.save(signUpDto);
        return;
    }

}
