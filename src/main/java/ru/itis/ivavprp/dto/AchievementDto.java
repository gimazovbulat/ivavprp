package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AchievementDto {
    private Long id;
    private String name;
    private int points;
    private File sourceFile;
    private List<SkillDto> skills;

}
