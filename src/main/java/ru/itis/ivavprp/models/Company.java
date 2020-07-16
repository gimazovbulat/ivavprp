package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.CompanyDto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "ivavprp", name = "company")
public class Company extends User {
    private String name;
    private String photo;
    private String about;

    @Builder(builderMethodName = "companyBuilder")
    public Company(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String name, String photo, String about, List<Token> tokens, Token currentToken) {
        super(id, email, password, isActive, roles, tokens, currentToken);

        this.name = name;
        this.photo = photo;
        this.about = about;
    }

    public static Company fromCompanyDto(CompanyDto companyDto) {
        return Company.companyBuilder()
                .id(companyDto.getId())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword())
                .isActive(companyDto.getIsActive())
                .roles(companyDto.getRoles())
                .name(companyDto.getName())
                .about(companyDto.getAbout())
                .photo(companyDto.getPhoto())
                .build();
    }

    public static CompanyDto toCompanyDto(Company company) {
        return CompanyDto.companyDtoBuilder()
                .id(company.getId())
                .email(company.getEmail())
                .isActive(company.getIsActive())
                .password(company.getPassword())
                .roles(company.getRoles())
                .name(company.getName())
                .about(company.getAbout())
                .photo(company.getPhoto())
                .build();
    }


}
