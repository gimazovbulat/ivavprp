package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.StudentRepository;
import ru.itis.ivavprp.repositories.TeacherRepository;

import java.util.Collections;

@Service
public class StudentServiceImpl extends UserService implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public boolean save(StudentDto studentDto) {


        if (studentRepository.findByEmail(studentDto.getEmail()).isPresent()) {
            return false;
        }
        Student student = new Student();
        student.setEmail(studentDto.getEmail());
        student.setRating(0);
        student.setIsActive(true);
        student.setRoles(Collections.singleton(Role.STUDENT));
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        studentRepository.save(student);
        return true;
    }
}
