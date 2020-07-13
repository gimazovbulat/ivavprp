package ru.itis.ivavprp.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.Skill;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT skill from Skill skill where skill.name like %:name%")
    List<Skill> findByName(@Param("name") String name);

    Optional<Skill> findById(Long id);

    @Query("SELECT distinct skill from Skill skill order by skill.name")
    List<Skill> getTopSkills(Pageable pageable);
}
