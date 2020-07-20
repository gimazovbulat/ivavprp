package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.*;
import ru.itis.ivavprp.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    boolean save(TeacherDto teacherDto);

    TeacherInfoDto findTeacherById(Long id);

    TeacherInfoDto updateInfo(Long id, TeacherInfoDto info);

    List<TeacherDto> getAll();

    void remove(Long id);

    void updateStatus(TeacherStatusDto teacherDto);
}
