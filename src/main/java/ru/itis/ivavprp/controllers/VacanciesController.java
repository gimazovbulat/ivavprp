package ru.itis.ivavprp.controllers;

import com.google.common.base.Joiner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.search.SearchOperation;
import ru.itis.ivavprp.search.VacancySpecificationBuilder;
import ru.itis.ivavprp.services.VacanciesService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class VacanciesController {
    private final VacanciesService vacanciesService;

    public VacanciesController(VacanciesService vacanciesService) {
        this.vacanciesService = vacanciesService;
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
                                                   int size) {

        VacancySpecificationBuilder builder = new VacancySpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Vacancy> spec = builder.build();
        return vacanciesService.findAll(spec, page, size);
    }
}
