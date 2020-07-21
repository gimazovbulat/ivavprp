package ru.itis.ivavprp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ru.itis.ivavprp.dto.StudentDto;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    @JsonManagedReference
    private List<Resume> resumes;

    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String firstName, String lastName, String photo, Integer rating, Integer course,
                   List<Token> tokens, Token currentToken, List<Resume> resumes) {
        super(id, email, password, isActive, roles, tokens, currentToken);
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.photo = photo;
        this.rating = rating;
        this.resumes = resumes;
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
                .resumes(studentDto.getResumes())
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
                .resumes(student.getResumes())
                .build();
    }
}

//
//    public List<Resume> getResumes() { return resumes; }
//
//    public void setResumes(List<Resume> resumes) { this.resumes = resumes; }
//}

