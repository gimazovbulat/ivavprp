package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.StudentDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "ivavprp", name = "student")
public class Student extends User {
    private String firstName;
    private String lastName;
    private String photo;
    private Integer rating;
    private Integer course;


    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String firstName, String lastName, String photo, Integer rating, Integer course,
                   List<Token> tokens, Token currentToken) {
        super(id, email, password, isActive, roles, tokens, currentToken);
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.photo = photo;
        this.rating = rating;
    }


    public static Student fromStudentDto(StudentDto studentDto) {
        return Student.studentBuilder()
                .id(studentDto.getId())
                .email(studentDto.getEmail())
                .password(studentDto.getPassword())
                .isActive(studentDto.getIsActive())
                .roles(studentDto.getRoles())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .course(studentDto.getCourse())
                .rating(studentDto.getRating())
                .photo(studentDto.getPhoto())
                .build();
    }

    public static StudentDto toStudentDto(Student student) {
        return StudentDto.studentDtoBuilder()
                .id(student.getId())
                .email(student.getEmail())
                .isActive(student.getIsActive())
                .password(student.getPassword())
                .roles(student.getRoles())
                .course(student.getCourse())
                .rating(student.getRating())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .photo(student.getPhoto())
                .build();
    }
}
