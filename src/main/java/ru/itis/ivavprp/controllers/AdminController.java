package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.TeacherStatusDto;
import ru.itis.ivavprp.services.SkillsService;
import ru.itis.ivavprp.services.TeacherService;

import java.util.List;


@RestController()
public class AdminController {

    private final SkillsService skillsService;
    private final TeacherService teacherService;

    public AdminController(SkillsService skillsService, TeacherService teacherService) {
        this.skillsService = skillsService;
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/skill")
    public ResponseEntity<SkillDto> saveSkill(@RequestBody SkillDto skill) {
        SkillDto skillDto = skillsService.save(skill);
        return ResponseEntity.ok(skillDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/skill")
    public ResponseEntity<List<SkillDto>> getSkills() {
        return ResponseEntity.ok(skillsService.findAll());
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("admin/skill")
    public void deleteSkill(@RequestBody SkillDto skill) {
        skillsService.remove(skill.getId());
        return;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/teachers")
    public void changeStatus(@RequestBody TeacherStatusDto teacherStatusDto) {
        teacherService.updateStatus(teacherStatusDto);
        return;
    }
}
