package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.AchievementDto;
import ru.itis.ivavprp.dto.SkillDto;

import java.util.List;

public interface AchievementsService {
    List<AchievementDto> getAll(int page, int size);

    void delete(Long id);

    AchievementDto update(AchievementDto achievement);

    AchievementDto save(AchievementDto achievementDto);

    List<AchievementDto> findByName(String name, int page, int size);

    AchievementDto findById(Long id);

    List<SkillDto> addSkill(Long achievementId, Long skillId);

    List<SkillDto> removeSkill(Long achievementId, Long skillId);
}
