package ru.itis.ivavprp.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.CompanyInfoDto;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.repositories.CompanyRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class CompanyServiceImpl extends UserService implements CompaniesService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
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

    @Transactional
    @Override
    public CompanyDto saveInfo(Long id, CompanyInfoDto info) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(info.getName());
            company.setAbout(info.getAbout());
            company.setPhoto(info.getPhoto());
            Company savedCompany = companyRepository.save(company);
            return Company.toCompanyDto(savedCompany);
        }
        throw new IllegalStateException();
    }

    @Override
    public CompanyDto findOne(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            return Company.toCompanyDto(company);
        }
        throw new IllegalStateException(); //custom exception
    }

    @Transactional
    @Override
    public CompanyDto update(Long id, CompanyInfoDto info) {
        System.out.println(info);
        System.out.println(id);
        Company company = companyRepository.getOne(id);
        if (info.getAbout() != null){
            company.setAbout(info.getAbout());
        }
        if (info.getName() != null){
            company.setName(info.getName());
        }
        if (info.getPhoto() != null){
            company.setPhoto(info.getPhoto());
            System.out.println(company);
        }

        Company savedCompany = companyRepository.save(company);
        return Company.toCompanyDto(savedCompany);
    }
}
