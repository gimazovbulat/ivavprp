package ru.itis.ivavprp.services;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.dto.AddOrRemoveSkills;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.dto.VacancyForm;
import ru.itis.ivavprp.models.Vacancy;

import java.util.List;

public interface VacanciesService {
    VacancyDto save(VacancyForm vacancy);

    VacancyDto update(VacancyForm vacancy);

    void delete(Long id);

    List<VacancyDto> findAll(Specification<Vacancy> spec, int page, int size);

    List<VacancyDto> findAllBySkills(List<SkillDto> skills);

    VacancyDto addOrRemoveSkills(Long vacId, AddOrRemoveSkills addOrRemoveSkills);

    VacancyDto getOneById(Long id);
}
