package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.repositories.ResumesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumesRepository resumesRepository;

    public ResumeServiceImpl(ResumesRepository resumesRepository) {
        this.resumesRepository = resumesRepository;
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
}
