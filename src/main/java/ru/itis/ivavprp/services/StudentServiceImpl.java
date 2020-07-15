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
public class StudentServiceImpl extends UserService implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(StudentDto studentDto) {


        if (studentRepository.findByEmail(studentDto.getEmail()).isPresent()) {
            return false;
        }
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPhoto(studentDto.getPhoto());
        student.setEmail(studentDto.getEmail());
        student.setIsActive(true);
        student.setRoles(Collections.singleton(Role.USER));
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        studentRepository.save(student);
        return true;
    }
}
