package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.models.Role;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor public class TeacherDto extends UserDto{
    private String firstName;
    private String lastName;
    private String photo;

    @Builder(builderMethodName = "teacherDtoBuilder")
    public TeacherDto(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                      String firstName, String lastName, String photo){
        super(id, email, password, isActive, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }
}
