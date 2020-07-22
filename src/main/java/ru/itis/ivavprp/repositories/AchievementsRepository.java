package ru.itis.ivavprp.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.Achievement;

import java.util.List;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievement, Long> {

    @Query("SELECT ach from Achievement ach where ach.name like %:name%")
    List<Achievement> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT ach from Achievement ach")
    List<Achievement> getAll(Pageable pageable);
}
