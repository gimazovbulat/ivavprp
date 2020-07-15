package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.services.CompanyServiceImpl;

@RestController
@RequestMapping(value = "/company/")
public class CompanyController {


    private final CompanyServiceImpl companyService;


    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/registration")
    public CompanyDto saveCompany(@RequestBody CompanyDto companyDto) {
        companyService.save(companyDto);
        return companyDto;
    }

}
