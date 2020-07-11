package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.AchievementDto;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "ivavprp", name = "achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int points;
    private String path;
    @Transient
    private File sourceFile;
    @ManyToMany
    @JoinTable(schema = "ivavprp", name = "skills_achievements",
            joinColumns = @JoinColumn(name = "achievement_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    @PostLoad
    public void loadFile() {
        sourceFile = new File(path);
    }

    @PreUpdate
    public void updateFileInformation() {
        this.path = sourceFile.getPath();
    }

    public static Achievement fromAchievementDto(AchievementDto achievementDto) {
        return Achievement.builder()
                .id(achievementDto.getId())
                .points(achievementDto.getPoints())
                .name(achievementDto.getName())
                .sourceFile(achievementDto.getSourceFile())
                .skills(achievementDto.getSkills().stream().map(Skill::fromSkillDto).collect(Collectors.toList()))
                .build();
    }

    public static AchievementDto toAchievementDto(Achievement achievement){
        return AchievementDto.builder()
                .name(achievement.getName())
                .sourceFile(achievement.getSourceFile())
                .points(achievement.getPoints())
                .skills(achievement.getSkills().stream().map(Skill::toSkillDto).collect(Collectors.toList()))
                .id(achievement.getId())
                .build();
    }
}
