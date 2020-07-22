package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.ResumeVacancyDto;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Status;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.services.ResumeService;
import ru.itis.ivavprp.services.ResumeVacancyService;
import ru.itis.ivavprp.services.VacanciesService;

@RestController
public class ResponsesController {

    private final ResumeVacancyService resumeVacancyService;
    private final ResumeService resumeService;
    private final VacanciesService vacanciesService;

    public ResponsesController(ResumeVacancyService resumeVacancyService
            , ResumeService resumeService
            , VacanciesService vacanciesService){
        this.resumeVacancyService = resumeVacancyService;
        this.resumeService = resumeService;
        this.vacanciesService = vacanciesService;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/vacancies/{vacancyId}/response/{resumeId}")
    public ResponseEntity<?> makeResponse(@PathVariable Long vacancyId, @PathVariable Long resumeId){
        resumeVacancyService.save(ResumeVacancyDto.builder()
                .vacancy(Vacancy.fromVacancyDto(vacanciesService.getOneById(vacancyId)))
                .resume(Resume.fromResumeDto(resumeService.findById(resumeId)))
                .status(Status.NOT_CHECKED)
                .build());
        return ResponseEntity.accepted().build();
    }

}
