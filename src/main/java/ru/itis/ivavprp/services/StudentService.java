package ru.itis.ivavprp.services;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.StudentInfoDto;
import ru.itis.ivavprp.models.Student;

import java.util.List;

public interface StudentService {
    boolean save(StudentDto studentDto);

    List<StudentDto> findAll(Specification<Student> spec, int page, int size);

    List<SkillDto> addSkills(StudentDto studentDto, List<Long> skillIds);

    List<SkillDto> removeSkills(StudentDto studentDto, List<Long> skillIds);

    List<SkillDto> addSkillsToResume(Long studentId, Long resumeId, List<Long> skillIds);

    List<SkillDto> removeSkillsToResume(Long studentId, Long resumeId, List<Long> skillIds);

    StudentDto findStudentById(Long id);

    StudentInfoDto findStudentInfo(Long id);
}
