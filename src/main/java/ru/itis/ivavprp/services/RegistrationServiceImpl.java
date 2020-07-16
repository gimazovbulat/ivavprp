package ru.itis.ivavprp.services;

import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.RegistrationDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.TeacherDto;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final CompanyService companyService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public RegistrationServiceImpl(CompanyService companyService, StudentService studentService, TeacherService teacherService) {
        this.companyService = companyService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void save(RegistrationDto registrationDto) {
        if (registrationDto.getRole().equals("TEACHER")) {
            teacherService.save(TeacherDto
                    .teacherDtoBuilder()
                    .email(registrationDto.getEmail())
                    .password(registrationDto.getPassword())
                    .build());
        }
        if (registrationDto.getRole().equals("STUDENT")) {
            studentService.save(StudentDto
                    .studentDtoBuilder()
                    .email(registrationDto.getEmail())
                    .password(registrationDto.getPassword())
                    .build());
        }
        if (registrationDto.getRole().equals("COMPANY")) {
            companyService.save(CompanyDto
                    .companyDtoBuilder()
                    .email(registrationDto.getEmail())
                    .password(registrationDto.getPassword())
                    .build());
        }

    }
}
