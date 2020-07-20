package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.CompanyDto;
import ru.itis.ivavprp.dto.CompanyInfoDto;

public interface CompaniesService {
    boolean save(CompanyDto companyDto);

    CompanyDto saveInfo(Long id, CompanyInfoDto info);

    CompanyDto findOne(Long id);

    CompanyDto update(Long id, CompanyInfoDto info);
}
