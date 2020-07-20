package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.CompanyInfoDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.CompaniesService;

import java.util.List;

@RestController
public class CompanyController {
    private final CompaniesService companyService;

    public CompanyController(CompaniesService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/saveInfo")
    public ResponseEntity<CompanyDto> save(@CurrentUser UserDetails userDetails, @RequestBody CompanyInfoDto info) {
        Company company = (Company) userDetails;
        CompanyDto savedCompany = companyService.saveInfo(company.getId(), info);
        return ResponseEntity.ok(savedCompany);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        CompanyDto companyDto = companyService.findOne(id);
        return ResponseEntity.ok(companyDto);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/companies")
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyInfoDto info, @CurrentUser UserDetails userDetails) {
        Company company = (Company) userDetails;
        CompanyDto updatedCompany = companyService.update(company.getId(), info);
        return ResponseEntity.ok(updatedCompany);
    }

    @GetMapping("/companies/{id}/vacancies")
    public ResponseEntity<List<VacancyDto>> getVacancies(@PathVariable("id") Long id){
        CompanyDto company = companyService.findOne(id);
        List<VacancyDto> vacancies = companyService.findOne(company.getId()).getVacancies();
        return ResponseEntity.ok(vacancies);
    }

}
