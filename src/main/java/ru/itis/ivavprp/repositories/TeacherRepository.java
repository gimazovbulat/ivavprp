package ru.itis.ivavprp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findById(Long id);

    @Modifying
    @Query(value = "update ivavprp.skills_students set confirmed = true where student_id = :studentId and skill_id = :skillId",
            nativeQuery = true)
    void confirmSkill(@Param("studentId") Long studentId, @Param("skillId") Long skillId);
}
