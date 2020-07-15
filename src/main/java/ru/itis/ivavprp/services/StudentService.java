package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.models.Student;

public interface StudentService {
    boolean save(StudentDto studentDto);
}
