package ru.itis.ivavprp.services;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Vacancy;

import java.util.List;

public interface VacanciesService {
    VacancyDto save(VacancyDto vacancy);

    VacancyDto update(VacancyDto vacancy);

    void delete(Long id);

    List<VacancyDto> findAll(Specification<Vacancy> spec, int page, int size);

    List<SkillDto> addSkill(Long vacancyId, Long skillId);

    List<SkillDto> removeSkill(Long vacancyId, Long skillId);

    List<VacancyDto> findAllBySkills(List<SkillDto> skills);
}
