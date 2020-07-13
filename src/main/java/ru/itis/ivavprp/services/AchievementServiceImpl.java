package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.AchievementDto;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.models.Achievement;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.repositories.AchievementsRepository;
import ru.itis.ivavprp.repositories.SkillsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementsService {
    private final AchievementsRepository achievementsRepository;
    private final SkillsRepository skillsRepository;

    public AchievementServiceImpl(AchievementsRepository achievementsRepository, SkillsRepository skillsRepository) {
        this.achievementsRepository = achievementsRepository;
        this.skillsRepository = skillsRepository;
    }

    @Override
    public List<AchievementDto> getAll(int page, int size) {
        return achievementsRepository.findAll(PageRequest.of(page, size)).stream().map(Achievement::toAchievementDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        achievementsRepository.deleteById(id);
    }

    @Override
    public AchievementDto update(AchievementDto achievement) {
        return Achievement.toAchievementDto(achievementsRepository.save(Achievement.fromAchievementDto(achievement)));
    }

    @Override
    public AchievementDto save(AchievementDto achievement) {
        return Achievement.toAchievementDto(achievementsRepository.save(Achievement.fromAchievementDto(achievement)));
    }

    @Override
    public List<AchievementDto> findByName(String name, int page, int size) {
        return achievementsRepository.findByName(name, PageRequest.of(page, size)).stream()
                .map(Achievement::toAchievementDto)
                .collect(Collectors.toList());
    }

    @Override
    public AchievementDto findById(Long id) {
        return Achievement.toAchievementDto(achievementsRepository.getOne(id));
    }

    @Override
    public List<SkillDto> addSkill(Long achievementId, Long skillId) {
        Optional<Achievement> optionalVacancy = achievementsRepository.findById(achievementId);
        Optional<Skill> optionalSkill = skillsRepository.findById(skillId);
        Achievement achievement;

        if (optionalVacancy.isPresent() && optionalSkill.isPresent()) {
            achievement = optionalVacancy.get();
            Skill skill = optionalSkill.get();
            if (!achievement.getSkills().contains(skill)) {
                achievement.getSkills().add(skill);
            }
            return achievement.getSkills().stream()
                    .map(Skill::toSkillDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalStateException(); // todo custom exception
    }

    @Override
    public List<SkillDto> removeSkill(Long achievementId, Long skillId) {
        Optional<Achievement> optionalVacancy = achievementsRepository.findById(achievementId);
        Optional<Skill> optionalSkill = skillsRepository.findById(skillId);
        Achievement achievement;

        if (optionalVacancy.isPresent() && optionalSkill.isPresent()) {
            achievement = optionalVacancy.get();
            Skill skill = optionalSkill.get();
            if (!achievement.getSkills().contains(skill)) {
                achievement.getSkills().remove(skill);
            }
            return achievement.getSkills().stream()
                    .map(Skill::toSkillDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalStateException(); // todo custom exception
    }
}
