package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.services.VacanciesService;

import java.util.List;

@RestController
public class VacanciesController {
    private final VacanciesService vacanciesService;

    public VacanciesController(VacanciesService vacanciesService) {
        this.vacanciesService = vacanciesService;
    }

    @GetMapping("/vacancies")
    public ResponseEntity<List<VacancyDto>> getAll(@RequestParam(value = "name", required = false) String name,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        List<VacancyDto> vacancies;
        if (name != null) {
            vacancies = vacanciesService.findByName(name, page, size);
        } else {
            vacancies = vacanciesService.findAll(page, size);
        }
        return ResponseEntity.ok(vacancies);
    }

    @PostMapping("/vacancies")
    public ResponseEntity<VacancyDto> save(@RequestBody VacancyDto vacancyDto){
        VacancyDto savedVacancy = vacanciesService.save(vacancyDto);
        return ResponseEntity.ok(savedVacancy);
    }

    @DeleteMapping("/vacancies/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        vacanciesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/vacancies")
    public ResponseEntity<VacancyDto> update(@RequestBody VacancyDto vacancyDto){
        VacancyDto updatedVacancy = vacanciesService.update(vacancyDto);
        return ResponseEntity.ok(updatedVacancy);
    }
}
