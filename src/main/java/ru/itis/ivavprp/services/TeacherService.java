package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;

public interface TeacherService {
    boolean save(TeacherDto teacherDto);

    TeacherInfoDto findTeacherById(Long id);

    TeacherInfoDto update(Long id, TeacherInfoDto info);
}
