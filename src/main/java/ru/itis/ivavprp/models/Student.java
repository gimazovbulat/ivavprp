package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "db_inheritance", name = "student")
public class Student extends User {
    private String firstName;
    private String lastName;
    private String photo;
}
