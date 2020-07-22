package ru.itis.ivavprp.services;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.ResumeForm;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Student;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> findAll(Specification<Resume> spec, int page, int size);

    List<ResumeDto> findAllBySkills(List<SkillDto> skills);

    ResumeDto findById(Long id);

    void save(ResumeForm resumeForm, Student student);

    void delete(Long id);


}
