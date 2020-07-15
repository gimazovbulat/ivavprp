package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.models.Teacher;

public interface TeacherService {
    boolean save(TeacherDto teacherDto);
}
