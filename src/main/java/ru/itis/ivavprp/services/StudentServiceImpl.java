package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.StudentInfoDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.repositories.StudentRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
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
    public StudentInfoDto findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(IllegalStateException::new);
        StudentInfoDto dto = StudentInfoDto
                .builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .course(student.getCourse())
                .photo(student.getPhoto())
                .rating(student.getRating())
                .resumes(student.getResumes())
                .build();
        return dto;
    }
}
