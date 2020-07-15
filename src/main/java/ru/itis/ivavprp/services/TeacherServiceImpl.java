package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.UserDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.repositories.TeacherRepository;

import java.util.Collections;
@Service
public class TeacherServiceImpl extends UserService implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(TeacherDto teacherDto) {
        if (teacherRepository.findByEmail(teacherDto.getEmail()).isPresent()) {
            return false;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setPhoto(teacherDto.getPhoto());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setIsActive(true);
        teacher.setRoles(Collections.singleton(Role.USER));
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacherRepository.save(teacher);
        return true;
    }
}
