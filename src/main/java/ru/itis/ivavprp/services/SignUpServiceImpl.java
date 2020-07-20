package ru.itis.ivavprp.services;

import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.SignUpDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.TeacherDto;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final CompaniesService companyService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public SignUpServiceImpl(CompaniesService companyService, StudentService studentService, TeacherService teacherService) {
        this.companyService = companyService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void save(SignUpDto signUpDto) {
        if (signUpDto.getRole().equals("TEACHER")) {
            teacherService.save(TeacherDto
                    .teacherDtoBuilder()
                    .email(signUpDto.getEmail())
                    .password(signUpDto.getPassword())
                    .build());
        }
        if (signUpDto.getRole().equals("STUDENT")) {
            studentService.save(StudentDto
                    .studentDtoBuilder()
                    .email(signUpDto.getEmail())
                    .password(signUpDto.getPassword())
                    .build());
        }
        if (signUpDto.getRole().equals("COMPANY")) {
            companyService.save(CompanyDto
                    .companyDtoBuilder()
                    .email(signUpDto.getEmail())
                    .password(signUpDto.getPassword())
                    .build());
        }

    }
}
