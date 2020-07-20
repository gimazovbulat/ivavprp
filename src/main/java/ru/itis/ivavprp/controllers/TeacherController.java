package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.TeacherServiceImpl;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/restApi/teachers/{id}")
    public ResponseEntity<TeacherInfoDto> get(@PathVariable("id") Long id) {
        System.out.println("qq");
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/teachers")
    public ResponseEntity<TeacherInfoDto> update(@RequestBody TeacherInfoDto info, @CurrentUser UserDetails userDetails) {
        Teacher teacher = (Teacher) userDetails;
        TeacherInfoDto updatedTeacher = teacherService.update(teacher.getId(), info);
        return ResponseEntity.ok(updatedTeacher);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/students/{id}/skills/confirm")
    public ResponseEntity<List<SkillDto>> confirmSkills(@PathVariable("id") Long studentId,
                             @RequestBody List<Long> skillIds,
                              @CurrentUser UserDetails userDetails) {
        Teacher teacher = (Teacher) userDetails;
        List<SkillDto> confirmedSkills = teacherService.confirmSkills(Teacher.toTeacherDto(teacher), studentId, skillIds);
        return ResponseEntity.ok(confirmedSkills);
    }
}
