package ru.itis.ivavprp.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.TeacherRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.Collections;

@Service
public class TeacherServiceImpl extends UserService implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean save(TeacherDto teacherDto) {
        if (userRepository.findByEmail(teacherDto.getEmail()).isPresent()) {
            return false;
        }
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherDto.getEmail());
        teacher.setIsActive(true);
        ;
        teacher.setRoles(Collections.singleton(Role.TEACHER));
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacherRepository.save(teacher);
        return true;
    }
}
