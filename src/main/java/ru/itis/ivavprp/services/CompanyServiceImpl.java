package ru.itis.ivavprp.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.repositories.CompanyRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.Collections;

@Service
public class CompanyServiceImpl extends UserService implements CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean save(CompanyDto companyDto) {
        if (userRepository.findByEmail(companyDto.getEmail()).isPresent()) {
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
