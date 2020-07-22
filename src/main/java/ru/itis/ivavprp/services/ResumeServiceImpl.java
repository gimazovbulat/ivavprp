package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.*;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.repositories.ResumesRepository;
import ru.itis.ivavprp.repositories.SkillsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumesRepository resumesRepository;
    private final SkillsRepository skillsRepository;

    public ResumeServiceImpl(ResumesRepository resumesRepository, SkillsRepository skillsRepository) {
        this.resumesRepository = resumesRepository;
        this.skillsRepository = skillsRepository;
    }

    @Override
    public List<ResumeDto> findAll(Specification<Resume> spec, int page, int size) {
        return resumesRepository.findAll(spec, PageRequest.of(page, size)).stream()
                .map(Resume::toResumeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> findAllBySkills(List<SkillDto> skillsDtos) {
        List<Skill> skills = skillsDtos.stream().map(Skill::fromSkillDto).collect(Collectors.toList());
        return resumesRepository.findAllBySkillsIn(skills).stream().map(Resume::toResumeDto).collect(Collectors.toList());

    }

    @Override
    public ResumeDto findById(Long id) {
        Resume resume = resumesRepository.getOne(id);
        ResumeDto resumeDto = Resume.toResumeDto(resume);
        resumeDto.setStudentId(resume.getStudent().getId());
        return resumeDto;
    }

    @Override
    public void save(ResumeForm resumeDto, Student student) {
        Resume resume = Resume
                .builder()
                .employmentType(EmploymentType.getEmploymentType(resumeDto.getEmploymentType()))
                .workSchedule(WorkSchedule.getWorkSchedule(resumeDto.getWorkSchedule()))
                .name(resumeDto.getName())
                .text(resumeDto.getText())
                .student(student)
                .build();
        List<Skill> skills = new ArrayList<>();
        for (Long skillId : resumeDto.getSkills()) {
            skills.add(skillsRepository.findById(skillId).orElseThrow(IllegalStateException::new));
        }
        resume.setSkills(skills);
        resumesRepository.save(resume);
        return;
    }

    public void delete(Long id) {
        resumesRepository.deleteById(id);
    }

}
