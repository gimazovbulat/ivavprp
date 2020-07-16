package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.CompanyRepository;

import java.util.Collections;

@Service
public class CompanyServiceImpl extends UserService implements CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyServiceImpl(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(CompanyDto companyDto) {
        if (companyRepository.findByEmail(companyDto.getEmail()).isPresent()) {
            return false;
        }
        Company company = new Company();
        company.setEmail(companyDto.getEmail());
        company.setIsActive(true);
        company.setRoles(Collections.singleton(Role.COMPANY));
        company.setPassword(passwordEncoder.encode(companyDto.getPassword()));
        companyRepository.save(company);
        return true;
    }
}
