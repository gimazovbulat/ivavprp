package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.*;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.repositories.CompanyRepository;
import ru.itis.ivavprp.repositories.SkillsRepository;
import ru.itis.ivavprp.repositories.VacanciesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.itis.ivavprp.models.Vacancy.toVacancyDto;

@Service
public class VacanciesServiceImpl implements VacanciesService {
    private final VacanciesRepository vacanciesRepository;
    private final SkillsRepository skillsRepository;
    private final CompanyRepository companyRepository;

    public VacanciesServiceImpl(VacanciesRepository vacanciesRepository,
                                SkillsRepository skillsRepository,
                                CompanyRepository companyRepository) {
        this.vacanciesRepository = vacanciesRepository;
        this.skillsRepository = skillsRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public VacancyDto save(VacancyForm vacancy) {
        Vacancy vacancyToSave = Vacancy.builder()
                .employmentType(EmploymentType.getEmploymentType(vacancy.getEmploymentType()))
                .workSchedule(WorkSchedule.getWorkSchedule(vacancy.getWorkSchedule()))
                .maxSalary(vacancy.getMaxSalary())
                .minSalary(vacancy.getMinSalary())
                .name(vacancy.getName())
                .time(LocalDateTime.now())
                .company(companyRepository.getOne(vacancy.getCompanyId()))
                .text(vacancy.getText())
                .build();

        List<Long> skillsIds = vacancy.getSkillsIds();
        if (skillsIds != null) {
            for (Long id : skillsIds) {
                Optional<Skill> optionalSkill = skillsRepository.findById(id);
                if (optionalSkill.isPresent()) {
                    Skill skill = optionalSkill.get();
                    vacancyToSave.getSkills().add(skill);
                }
            }
        }

        return toVacancyDto(vacanciesRepository.save(vacancyToSave));
    }

    @Override
    public VacancyDto update(VacancyForm vacancy) {
        Optional<Vacancy> optionalVacancy = vacanciesRepository.findById(vacancy.getId());
        if (optionalVacancy.isPresent()) {
            Vacancy vacancyToUpdate = optionalVacancy.get();
            if (vacancy.getEmploymentType() != null) {
                vacancyToUpdate.setEmploymentType(EmploymentType.getEmploymentType(vacancy.getEmploymentType()));
            }
            if (vacancy.getMaxSalary() != null) {
                vacancyToUpdate.setMaxSalary(vacancy.getMaxSalary());
            }
            if (vacancy.getMinSalary() != null) {
                vacancyToUpdate.setMinSalary(vacancy.getMinSalary());
            }
            if (vacancy.getName() != null) {
                vacancyToUpdate.setName(vacancy.getName());
            }
            if (vacancy.getSkillsIds() != null) {
                List<Skill> newSkills = new ArrayList<>();
                List<Long> skillsIds = vacancy.getSkillsIds();
                if (skillsIds != null) {
                    for (Long id : skillsIds) {
                        Optional<Skill> optionalSkill = skillsRepository.findById(id);
                        if (optionalSkill.isPresent()) {
                            Skill skill = optionalSkill.get();
                            newSkills.add(skill);
                        }
                    }
                }
                vacancyToUpdate.setSkills(newSkills);
            }
            if (vacancy.getText() != null) {
                vacancyToUpdate.setText(vacancy.getText());
            }
            return Vacancy.toVacancyDto(vacanciesRepository.save(vacancyToUpdate));
        }
        throw new IllegalStateException();
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
    public List<VacancyDto> findAllBySkills(List<SkillDto> skillsDtos) {
        List<Skill> skills = skillsDtos.stream().map(Skill::fromSkillDto).collect(Collectors.toList());
        return vacanciesRepository.findAllBySkillsIn(skills).stream().map(Vacancy::toVacancyDto).collect(Collectors.toList());
    }


    @Override
    public VacancyDto addOrRemoveSkills(Long vacId, AddOrRemoveSkills addOrRemoveSkills) {
        Optional<Vacancy> optionalVacancy = vacanciesRepository.findById(vacId);
        if (optionalVacancy.isPresent()) {
            Vacancy vacancy = optionalVacancy.get();
            for (Long id : addOrRemoveSkills.getSkillsIds()) {
                Optional<Skill> optionalSkill = skillsRepository.findById(id);
                if (optionalSkill.isPresent()) {
                    Skill skill = optionalSkill.get();
                    if (addOrRemoveSkills.getAction() == 1) {
                        if (!vacancy.getSkills().contains(skill)) {
                            vacancy.getSkills().add(skill);
                        }
                    } else {
                        vacancy.getSkills().remove(skill);
                    }
                }
            }
            return Vacancy.toVacancyDto(vacancy);
        }
        throw new IllegalStateException(); //custom exception
    }

    @Override
    public VacancyDto getOneById(Long id) {
        Optional<Vacancy> byId = vacanciesRepository.findById(id);
        if (byId.isPresent()){
            return Vacancy.toVacancyDto(byId.get());
        }
        throw new IllegalStateException(); //custom
    }
}
