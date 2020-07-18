package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.models.Role;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto extends UserDto {
    private String name;
    private String photo;
    private String about;
    private List<VacancyDto> vacancies;

    @Builder(builderMethodName = "companyDtoBuilder")
    public CompanyDto(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String name, String photo, String about, List<VacancyDto> vacancies) {
        super(id, email, password, isActive, roles);

        this.name = name;
        this.photo = photo;
        this.about = about;
        this.vacancies = vacancies;
    }
}
