package ru.itis.ivavprp.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.Vacancy;

import java.util.List;

@Repository
public interface VacanciesRepository extends JpaRepository<Vacancy, Long>, JpaSpecificationExecutor<Vacancy> {
    @Query("SELECT vacancy from Vacancy vacancy where vacancy.name like %:name%")
    List<Vacancy> findAllByName(String name, Pageable pageable);
}
