package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.search.SearchService;
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

    @PostMapping("/vacancies")
    public ResponseEntity<VacancyDto> save(@RequestBody VacancyDto vacancyDto) {
        VacancyDto savedVacancy = vacanciesService.save(vacancyDto);
        return ResponseEntity.ok(savedVacancy);
    }

    @DeleteMapping("/vacancies/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        vacanciesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/vacancies")
    public ResponseEntity<VacancyDto> update(@RequestBody VacancyDto vacancyDto) {
        VacancyDto updatedVacancy = vacanciesService.update(vacancyDto);
        return ResponseEntity.ok(updatedVacancy);
    }

    @PutMapping("/vacancies/{id1}/skills/{id2}")
    public ResponseEntity<List<SkillDto>> addSkills(@PathVariable("id1") Long vacId, @PathVariable("id2") Long skillId) {
        List<SkillDto> skills = vacanciesService.addSkill(vacId, skillId);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/vacancies")
    @ResponseBody
    public List<VacancyDto> findAllBySpecification(@RequestParam(value = "search", required = false) String search,
                                                   int page,
                                                   int size,
                                                   int coll) {
        List<?> results = searchService.getVacanciesResults(search, page, size, coll);
        return (List<VacancyDto>) results;
    }
}
