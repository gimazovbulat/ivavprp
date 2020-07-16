package ru.itis.ivavprp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.RegistrationDto;
import ru.itis.ivavprp.services.RegistrationService;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody RegistrationDto registrationDto) {
        System.out.println(true);
        registrationService.save(registrationDto);
        return;
    }

}
