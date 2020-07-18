package ru.itis.ivavprp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.ivavprp.models.ResumeVacancy;

import java.util.List;

@Repository
public interface ResumeVacancyRepository extends JpaRepository<ResumeVacancy, Long> {

  //  @Query("SELECT rv from ResumeVacancy rv WHERE rv.vacancy.company.id = ?1")
  //List<ResumeVacancy> findAllByVacancy_Company_Id(Long companyId);

 //   @Query("SELECT rv from ResumeVacancy rv where rv.resume.student.id = ?1")
    List<ResumeVacancy> findAllByResume_Student_Id(Long studentId);
}
