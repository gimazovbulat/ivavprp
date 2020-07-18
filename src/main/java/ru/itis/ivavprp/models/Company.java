package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.itis.ivavprp.dto.CompanyDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "ivavprp", name = "company")
public class Company extends User {
    private String name;
    private String photo;
    private String about;
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;

    @Builder(builderMethodName = "companyBuilder")
    public Company(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String name, String photo, String about, List<Token> tokens, Token currentToken) {
        super(id, email, password, isActive, roles, tokens, currentToken);

        this.name = name;
        this.photo = photo;
        this.about = about;
    }

    public static Company fromCompanyDto(CompanyDto companyDto) {
        Company company = Company.companyBuilder()
                .id(companyDto.getId())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword())
                .isActive(companyDto.getIsActive())
                .roles(companyDto.getRoles())
                .name(companyDto.getName())
                .about(companyDto.getAbout())
                .photo(companyDto.getPhoto())
                .build();
        company.setVacancies(companyDto.getVacancies().stream()
                .map(Vacancy::fromVacancyDto)
                .collect(Collectors.toList()));
        return company;
    }

    public static CompanyDto toCompanyDto(Company company) {
        return CompanyDto.companyDtoBuilder()
                .id(company.getId())
                .email(company.getEmail())
                .isActive(company.getIsActive())
                .password(company.getPassword())
                .roles(company.getRoles())
                .vacancies(company.getVacancies().stream()
                        .map(Vacancy::toVacancyDto)
                        .collect(Collectors.toList()))
                .name(company.getName())
                .about(company.getAbout())
                .photo(company.getPhoto())
                .photo(company.getPhoto())
                .build();
    }


    @Override
    public String toString() {
        return "Company{" +
                "id='" + getId() + '\'' +
                "email='" + getEmail() + '\'' +
                "name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", about='" + about + '\'' +
                ", vacancies=" + vacancies +
                '}';
    }
}
