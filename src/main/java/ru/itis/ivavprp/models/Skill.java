package ru.itis.ivavprp.models;

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
//    @ManyToMany(mappedBy = "skills")/
//    private List<Vacancy> vacancies;

    public static Skill fromSkillDto(SkillDto skillDto) {
        return Skill.builder()
                .id(skillDto.getId())
                .name(skillDto.getName())
                .points(skillDto.getPoints())
                .build();
    }

    public static SkillDto toSkillDto(Skill skill) {
        return SkillDto.builder()
                .id(skill.getId())
                .nameToShow(skill.getName())
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
