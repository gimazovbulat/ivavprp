package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.StudentRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends UserService implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;


    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public boolean save(StudentDto studentDto) {


        if (userRepository.findByEmail(studentDto.getEmail()).isPresent()) {
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

    @Override
    public List<StudentDto> findAll(Specification<Student> spec, int page, int size) {
        return studentRepository.findAll(spec, PageRequest.of(page, size)).stream()
                .map(Student::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            return Student.toStudentDto(student);
        }
        throw new IllegalStateException(); //custom exception
    }

    @Override
    public StudentDto update(Long id, StudentDto info) {
        Student student = studentRepository.getOne(id);
        if (info.getFirstName() != null) {
            student.setFirstName(info.getFirstName());
        }
        if (info.getLastName() != null) {
            student.setLastName(info.getLastName());
        }
        Student savedStudent = studentRepository.save(student);
        StudentDto dto = Student.toStudentDto(savedStudent);
        return dto;
    }
}
