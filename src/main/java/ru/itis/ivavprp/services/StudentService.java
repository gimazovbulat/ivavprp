package ru.itis.ivavprp.services;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.StudentInfoDto;
import ru.itis.ivavprp.models.Student;

import java.util.List;

public interface StudentService {
    boolean save(StudentDto studentDto);

    List<StudentDto> findAll(Specification<Student> spec, int page, int size);

    StudentDto findOne(Long id);

    StudentInfoDto update(Long id, StudentInfoDto info);
}
