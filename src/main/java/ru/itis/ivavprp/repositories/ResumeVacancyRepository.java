package ru.itis.ivavprp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.ResumeVacancy;

import java.util.List;

@Repository
public interface ResumeVacancyRepository extends JpaRepository<ResumeVacancy, Long> {

    List<ResumeVacancy> findAllByVacancy_Company_Id(Long companyId);

    List<ResumeVacancy> findAllByResume_Student_Id(Long studentId);
}
