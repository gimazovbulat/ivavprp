package ru.itis.ivavprp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Skill;

import java.util.List;

@Repository
public interface ResumesRepository extends JpaRepository<Resume, Long>, JpaSpecificationExecutor<Resume> {
    List<Resume> findAllBySkillsIn(List<Skill> skills);

}
