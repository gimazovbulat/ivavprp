package ru.itis.ivavprp.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.UserDto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "db_inheritance", name = "teacher")
public class Teacher extends User{
    private String firstName;
    private String lastName;
    private String photo;

}
