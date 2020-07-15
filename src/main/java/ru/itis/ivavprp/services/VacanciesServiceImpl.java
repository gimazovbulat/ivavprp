package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.repositories.SkillsRepository;
import ru.itis.ivavprp.repositories.VacanciesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.itis.ivavprp.models.Vacancy.fromVacancyDto;
import static ru.itis.ivavprp.models.Vacancy.toVacancyDto;

@Service
public class VacanciesServiceImpl implements VacanciesService {
    private final VacanciesRepository vacanciesRepository;
    private final SkillsRepository skillsRepository;

    public VacanciesServiceImpl(VacanciesRepository vacanciesRepository,
                                SkillsRepository skillsRepository) {
        this.vacanciesRepository = vacanciesRepository;
        this.skillsRepository = skillsRepository;
    }

    @Override
    public VacancyDto save(VacancyDto vacancy) {
        return toVacancyDto(vacanciesRepository.save(fromVacancyDto(vacancy)));
    }

    @Override
    public VacancyDto update(VacancyDto vacancy) {
        return save(vacancy);
    }

    @Override
    public void delete(Long id) {
        vacanciesRepository.deleteById(id);
    }

    @Override
    public List<VacancyDto> findAll(Specification<Vacancy> spec, int page, int size) {
        return vacanciesRepository.findAll(spec, PageRequest.of(page, size, Sort.by("time").descending())).stream()
                .map(Vacancy::toVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillDto> addSkill(Long vacancyId, Long skillId) {
        Optional<Vacancy> optionalVacancy = vacanciesRepository.findById(vacancyId);
        Optional<Skill> optionalSkill = skillsRepository.findById(skillId);
        Vacancy vacancy;

        if (optionalVacancy.isPresent() && optionalSkill.isPresent()) {
            vacancy = optionalVacancy.get();
            Skill skill = optionalSkill.get();
            if (!vacancy.getSkills().contains(skill)) {
                vacancy.getSkills().add(skill);
            }
            return vacancy.getSkills().stream()
                    .map(Skill::toSkillDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalStateException(); // todo custom exception
    }

    @Override
    public List<SkillDto> removeSkill(Long vacancyId, Long skillId) {
        Optional<Vacancy> optionalVacancy = vacanciesRepository.findById(vacancyId);
        Optional<Skill> optionalSkill = skillsRepository.findById(skillId);
        Vacancy vacancy;

        if (optionalVacancy.isPresent() && optionalSkill.isPresent()) {
            vacancy = optionalVacancy.get();
            Skill skill = optionalSkill.get();
            if (!vacancy.getSkills().contains(skill)) {
                vacancy.getSkills().remove(skill);
            }
            return vacancy.getSkills().stream()
                    .map(Skill::toSkillDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalStateException(); // todo custom exception
    }

    @Override
    public List<VacancyDto> findAllBySkills(List<SkillDto> skillsDtos) {
        List<Skill> skills = skillsDtos.stream().map(Skill::fromSkillDto).collect(Collectors.toList());
        return vacanciesRepository.findAllBySkillsIn(skills).stream().map(Vacancy::toVacancyDto).collect(Collectors.toList());
    }
}
