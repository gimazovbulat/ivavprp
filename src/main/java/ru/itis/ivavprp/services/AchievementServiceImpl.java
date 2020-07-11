package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.AchievementDto;
import ru.itis.ivavprp.models.Achievement;
import ru.itis.ivavprp.repositories.AchievementsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementsService {
    private final AchievementsRepository achievementsRepository;

    public AchievementServiceImpl(AchievementsRepository achievementsRepository) {
        this.achievementsRepository = achievementsRepository;
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
}
