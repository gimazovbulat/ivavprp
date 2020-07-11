package ru.itis.ivavprp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.repositories.SkillsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepository skillsRepository;

    public SkillsServiceImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public List<SkillDto> findByName(String name) {
        return skillsRepository.findByName(name).stream().map(Skill::toSkillDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SkillDto save(SkillDto skill) {
        if (!skill.getName().isEmpty() && skill.getPoints() > 0) {
            return Skill.toSkillDto(skillsRepository.save(Skill.fromSkillDto(skill)));
        }
        throw new IllegalStateException(); //todo custom exception
    }

    @Transactional
    @Override
    public void remove(Long id) {
        skillsRepository.deleteById(id);
    }

    @Transactional
    @Override
    public SkillDto update(SkillDto skill) {
        return save(skill);
    }

    @Override
    public SkillDto findById(Long id) {
        Optional<Skill> optionalSkill = skillsRepository.findById(id);
        if (optionalSkill.isPresent()) {
            return Skill.toSkillDto(optionalSkill.get());
        } else throw new IllegalStateException(); //todo custom exception
    }
}
