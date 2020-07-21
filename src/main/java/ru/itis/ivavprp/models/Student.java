package ru.itis.ivavprp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.itis.ivavprp.dto.StudentDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "student")
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
        Student student = Student.studentBuilder()
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
        student.setResumes(studentDto.getResumes().stream()
                .map(Resume::fromResumeDto)
                .collect(Collectors.toList()));
                return student;
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
                .resumes(student.getResumes().stream()
                        .map(Resume::toResumeDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo='" + photo + '\'' +
                ", rating=" + rating +
                ", course=" + course +
                ", resumes=" + resumes +
                '}';
    }
}

//
//    public List<Resume> getResumes() { return resumes; }
//
//    public void setResumes(List<Resume> resumes) { this.resumes = resumes; }
//}

