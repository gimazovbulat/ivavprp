package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.AchievementDto;
import ru.itis.ivavprp.services.AchievementsService;

import java.util.List;

@RestController
public class AchievementController {
    private final AchievementsService achievementsService;

    public AchievementController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    @GetMapping("/achievements/{id}")
    public ResponseEntity<AchievementDto> getById(@PathVariable("id") Long id) {
        AchievementDto achievement = achievementsService.findById(id);
        return ResponseEntity.ok(achievement);
    }

    @GetMapping("/achievements")
    public ResponseEntity<List<AchievementDto>> getByName(@RequestParam("name") String name, int page, int size) {
        List<AchievementDto> achievements = achievementsService.findByName(name, page, size);
        return ResponseEntity.ok(achievements);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("achievements/{id}")
    public void delete(@PathVariable("id") Long id){
        achievementsService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("achievements")
    public ResponseEntity<AchievementDto> update(@RequestBody AchievementDto achievementDto){
        AchievementDto savedAch = achievementsService.save(achievementDto);
        return ResponseEntity.ok(savedAch);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("achievements")
    public ResponseEntity<AchievementDto> save(@RequestBody AchievementDto achievementDto){
        AchievementDto savedAch = achievementsService.save(achievementDto);
        return ResponseEntity.ok(savedAch);
    }
}
