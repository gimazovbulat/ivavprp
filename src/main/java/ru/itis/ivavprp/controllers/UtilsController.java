package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.EmploymentType;
import ru.itis.ivavprp.dto.WorkSchedule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UtilsController {
    @GetMapping("/employment_type")
    public ResponseEntity<List<String>> getEmplTypes(){
        List<String> emplTypes = Arrays.stream(EmploymentType.values())
                .map(employmentType -> toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(emplTypes);
    }

    @GetMapping("/work_schedule")
    public ResponseEntity<List<String>> getWorkSchedule(){
        List<String> workScheduleTypes = Arrays.stream(WorkSchedule.values())
                .map(workSchedule -> toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(workScheduleTypes);
    }
}
