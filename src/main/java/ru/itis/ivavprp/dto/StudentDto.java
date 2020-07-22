package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Role;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends UserDto {
    private String firstName;
    private String lastName;
    private String photo;
    private Integer course;
    private Integer rating;
    private List<ResumeDto> resumes;
    private List<SkillDto> skills;

    @Builder(builderMethodName = "studentDtoBuilder")
    public StudentDto(Long id, String email, String password, Boolean isActive, Set<Role> roles,
                      String firstName, String lastName, String photo, Integer rating, Integer course, List<ResumeDto> resumes, List<SkillDto> skills) {
        super(id, email, password, isActive, roles);
        this.firstName = firstName;
        this.skills = skills;
        this.lastName = lastName;
        this.course = course;
        this.photo = photo;
        this.rating = rating;
        this.resumes = resumes;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo='" + photo + '\'' +
                ", course=" + course +
                ", rating=" + rating +
                ", resumes=" + resumes +
                ", skills=" + skills +
                '}';
    }
}
