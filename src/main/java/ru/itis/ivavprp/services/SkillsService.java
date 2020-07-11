package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.SkillDto;

import java.util.List;

public interface SkillsService {
    List<SkillDto> findByName(String name);

    SkillDto save(SkillDto skill);

    void remove(Long id);

    SkillDto update(SkillDto skill);

    SkillDto findById(Long id);
}
