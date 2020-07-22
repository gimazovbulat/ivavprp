package ru.itis.ivavprp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.ResumeVacancyDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Status;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.ResumeVacancyService;
import ru.itis.ivavprp.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class NotificationsController {

    private final ResumeVacancyService resumeVacancyService;


    public NotificationsController(ResumeVacancyService resumeVacancyService) {
        this.resumeVacancyService = resumeVacancyService;
    }


    @GetMapping("api/notif/stud/{id}")
    public ResponseEntity<List<ResumeVacancyDto>> showNotificationsForStudents(@PathVariable Long id) {
        return new ResponseEntity<>(resumeVacancyService.getAllByStudentId(id), HttpStatus.OK);
    }



    @GetMapping("/api/notif/comp/{id}")
    public ResponseEntity<List<ResumeVacancyDto>> showNotificationsForCompanies(@PathVariable Long id) {
        System.out.println("im here");
        return new ResponseEntity<>(resumeVacancyService.getAllByCompanyIdNotChecked(id), HttpStatus.OK);
    }


    @PostMapping("/notif/comp/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
        System.out.println("change status");
        if(status.equals("APLIED")){
            resumeVacancyService.changeStatus(id, Status.APPLIED);
        }
        if(status.equals("NOT_APLIED")){
            resumeVacancyService.changeStatus(id, Status.NOT_APPLIED);
        }

        return ResponseEntity.ok().build();
    }

}
