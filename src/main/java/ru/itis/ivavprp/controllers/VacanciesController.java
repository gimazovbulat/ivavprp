package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.AddOrRemoveSkills;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.dto.VacancyForm;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.VacanciesService;

import java.util.List;

@RestController
public class VacanciesController {
    private final VacanciesService vacanciesService;
    private final SearchService searchService;

    public VacanciesController(VacanciesService vacanciesService, SearchService searchService) {
        this.vacanciesService = vacanciesService;
        this.searchService = searchService;
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/restApi/vacancies")
    public void save(@RequestBody VacancyForm vacancy, @CurrentUser UserDetails userDetails) {
        Company company = (Company) userDetails;
        vacancy.setCompanyId(company.getId());
        VacancyDto savedVacancy = vacanciesService.save(vacancy);
        return;
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @DeleteMapping("/restApi/vacancies/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        vacanciesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/restApi/vacancies/{id}")
    public ResponseEntity<VacancyDto> update(@PathVariable("id") Long id, @RequestBody VacancyForm vacancy, @CurrentUser UserDetails userDetails) {
        Company company = (Company) userDetails;
        vacancy.setCompanyId(company.getId());
        vacancy.setId(id);
        VacancyDto updatedVacancy = vacanciesService.update(vacancy);
        return ResponseEntity.ok(updatedVacancy);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/vacancies/{id}/skills")
    public ResponseEntity<VacancyDto> addOrRemoveSkills(@PathVariable("id") Long vacId,
                                                        @RequestBody AddOrRemoveSkills addOrRemoveSkills,
                                                        @CurrentUser UserDetails userDetails) {
        VacancyDto vacancy = vacanciesService.getOneById(vacId);
        VacancyDto vacancyToUpdate;
        Company company = (Company) userDetails;
        if (company.getId().equals(vacancy.getCompany().getId())) {
            vacancyToUpdate = vacanciesService.addOrRemoveSkills(vacId, addOrRemoveSkills);
            return ResponseEntity.ok(vacancyToUpdate);
        }
        return ResponseEntity.ok(vacancy);
    }

    @GetMapping("/restApi/vacancies/{id}")
    public ResponseEntity<VacancyDto> getById(@PathVariable(value = "id", required = false) Long id) {
        VacancyDto vacDto = vacanciesService.getOneById(id);
        return ResponseEntity.ok(vacDto);
    }


    @GetMapping("/vacancies")
    @ResponseBody
    public ResponseEntity<List<VacancyDto>> findAllBySpecification(@RequestParam(value = "search") String search,
                                                                   @RequestParam Integer page,
                                                                   @RequestParam Integer size,
                                                                   @RequestParam Integer coll) {
        List<VacancyDto> results = (List<VacancyDto>) searchService.getVacanciesResults(search, page, size, coll);
        return ResponseEntity.ok(results);
    }


}
