package ru.itis.ivavprp.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.TeacherDto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "ivavprp", name = "teacher")
public class Teacher extends User {
    private String firstName;
    private String lastName;
    private String photo;

    @Builder(builderMethodName = "teacherBuilder")
    public Teacher(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String firstName, String lastName, String photo, List<Token> tokens, Token currentToken) {
        super(id, email, password, isActive, roles, tokens, currentToken);
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }


    public static Teacher fromTeacherDto(TeacherDto teacherDto) {
        return Teacher.teacherBuilder()
                .id(teacherDto.getId())
                .email(teacherDto.getEmail())
                .password(teacherDto.getPassword())
                .isActive(teacherDto.getIsActive())
                .roles(teacherDto.getRoles())
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .photo(teacherDto.getPhoto())
                .build();
    }

    public static TeacherDto toTeacherDto(Student student) {
        return TeacherDto.teacherDtoBuilder()
                .id(student.getId())
                .email(student.getEmail())
                .isActive(student.getIsActive())
                .password(student.getPassword())
                .roles(student.getRoles())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .photo(student.getPhoto())
                .build();
    }


}
