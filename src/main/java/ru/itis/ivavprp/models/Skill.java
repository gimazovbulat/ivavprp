package ru.itis.ivavprp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.SkillDto;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "ivavprp", name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int points;
    @ManyToMany(mappedBy = "skills")
    private List<Achievement> achievements;
    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private List<Vacancy> vacancies;
    @Transient
    private boolean confirmed;

    public static Skill fromSkillDto(SkillDto skillDto) {
        return Skill.builder()
                .id(skillDto.getId())
                .name(skillDto.getName())
                .points(skillDto.getPoints())
                .confirmed(skillDto.isConfirmed())
                .build();
    }

    public static SkillDto toSkillDto(Skill skill) {
        return SkillDto.builder()
                .id(skill.getId())
                .nameToShow(skill.getName())
                .confirmed(skill.isConfirmed())
                .name(skill.getName().replaceAll(" ", "_"))
                .points(skill.getPoints())
                .build();
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
