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

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/notif/stud")
    public ResponseEntity<List<ResumeVacancyDto>> showNotificationsForStudents(@CurrentUser UserDetails userDetails) {
        User user = (User) userDetails;
        return new ResponseEntity<>(resumeVacancyService.getAllByStudentId(user.getId()), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/notif/comp")
    public ResponseEntity<List<ResumeVacancyDto>> showNotificationsForCompanies(@CurrentUser UserDetails userDetails) {
        User user = (User) userDetails;
        return new ResponseEntity<>(resumeVacancyService.getAllByCompanyId(user.getId()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/notif/comp/{id}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @PathVariable String status){
        resumeVacancyService.changeStatus(id, Status.valueOf(status));
        return ResponseEntity.accepted().build();
    }


}
