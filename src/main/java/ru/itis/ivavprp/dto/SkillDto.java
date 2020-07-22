package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SkillDto {
    private Long id;
    private String name;
    private int points;
    private String nameToShow;
    private boolean confirmed;
}
