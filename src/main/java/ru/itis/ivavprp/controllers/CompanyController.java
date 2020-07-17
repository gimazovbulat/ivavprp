package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.CompanyInfoDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.CompanyService;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/saveInfo")
    public ResponseEntity<CompanyDto> save(@CurrentUser UserDetails userDetails, @RequestBody CompanyInfoDto info) {
        Company company = (Company) userDetails;
        CompanyDto companyDto = companyService.saveInfo(company.getId(), info);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        CompanyDto companyDto = companyService.findCompanyById(id);
        return ResponseEntity.ok(companyDto);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/companies")
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyInfoDto info, @CurrentUser UserDetails userDetails){
        Company company = (Company) userDetails;
        CompanyDto updatedCompany = companyService.update(company.getId(), info);
        return ResponseEntity.ok(updatedCompany);
    }

}
