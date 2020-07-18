package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.CompanyInfoDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;
import ru.itis.ivavprp.models.Teacher;

import java.util.Optional;

public interface TeacherService {
    boolean save(TeacherDto teacherDto);
    TeacherInfoDto findTeacherById(Long id);
    TeacherInfoDto update(Long id, TeacherInfoDto info);
}
