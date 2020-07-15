package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.*;
import ru.itis.ivavprp.services.SkillsService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UtilsController {
    private final SkillsService skillsService;

    public UtilsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/employment_type")
    public ResponseEntity<List<EmplTypesToFront>> getEmplTypes(){
        List<EmplTypesToFront> emplTypes = Arrays.stream(EmploymentType.values())
                .map(employmentType -> new EmplTypesToFront(employmentType.getValueToShow(), employmentType.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(emplTypes);
    }

    @GetMapping("/work_schedule")
    public ResponseEntity<List<WorkScheduleToFront>> getWorkSchedule(){
        List<WorkScheduleToFront> workScheduleTypes = Arrays.stream(WorkSchedule.values())
                .map(workSchedule -> new WorkScheduleToFront(workSchedule.getValueToShow(), workSchedule.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(workScheduleTypes);
    }
}
