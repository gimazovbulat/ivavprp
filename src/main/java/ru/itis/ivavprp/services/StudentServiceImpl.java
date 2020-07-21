package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.StudentInfoDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.StudentRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
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

    @Transactional
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
    public StudentDto findOne(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return Student.toStudentDto(optionalStudent.get());
        }
        throw new EntityNotFoundException();
    }

    @Override
    public StudentInfoDto update(Long id, StudentInfoDto info) {
        Student student = studentRepository.getOne(id);
        if (info.getFirstName() != null) {
            student.setFirstName(info.getFirstName());
        }
        if (info.getLastName() != null) {
            student.setLastName(info.getLastName());
        }
        if (info.getPhoto() != null) {
            student.setPhoto(info.getPhoto());
            System.out.println(student);
        }
        Student savedStudent = studentRepository.save(student);
        StudentInfoDto dto = StudentInfoDto
                .builder()
                .firstName(savedStudent.getFirstName())
                .lastName(savedStudent.getLastName())
                .photo(savedStudent.getPhoto())
                .build();
        return dto;
    }
}
