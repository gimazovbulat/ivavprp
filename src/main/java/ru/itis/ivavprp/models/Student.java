package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    //can be confirmed and not
    @ManyToMany
    @JoinTable(schema = "ivavprp", name = "skills_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> allSkills;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Resume> resumes;

    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                   String firstName, String lastName, String photo, Integer rating, Integer course,
                   List<Token> tokens, Token currentToken, List<Resume> resumes, List<Skill> skills) {
        super(id, email, password, isActive, roles, tokens, currentToken);
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.setAllSkills(skills);
        this.photo = photo;
        this.rating = rating;
        this.resumes = resumes;
    }

    public static Student fromStudentDto(StudentDto studentDto) {
        return Student.studentBuilder()
                .id(studentDto.getId())
                .email(studentDto.getEmail())
                .password(studentDto.getPassword())
                .skills(studentDto.getSkills().stream().map(Skill::fromSkillDto).collect(Collectors.toList()))
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
                .skills(student.getAllSkills().stream().map(Skill::toSkillDto).collect(Collectors.toList()))
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

