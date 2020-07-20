package ru.itis.ivavprp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.services.SkillsService;

import java.util.List;

@RestController
public class SkillsController {
    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skills")
    @ResponseStatus(HttpStatus.CREATED)
    public SkillDto saveSkill(@RequestBody SkillDto skill) {
        return skillsService.save(skill);
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillDto>> getByName(@RequestParam(required = false) String name) {
        List<SkillDto> skills;
        if (name != null) {
            skills = skillsService.findByName(name);
        } else {
            skills = skillsService.findAll();
        }
        return ResponseEntity.ok(skills);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable("id") Long id) {
        skillsService.remove(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/skills")
    public ResponseEntity<SkillDto> update(@RequestBody SkillDto skill) {
        SkillDto updatedSkill = skillsService.update(skill);
        return ResponseEntity.ok(updatedSkill);
    }
}
