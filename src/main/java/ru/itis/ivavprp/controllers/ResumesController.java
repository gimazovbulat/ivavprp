package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.ResumeForm;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.search.SearchService;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.ResumeService;

import java.util.List;

@RestController
public class ResumesController {
    private final SearchService searchService;
    private final ResumeService resumeService;

    public ResumesController(SearchService searchService, ResumeService resumeService) {
        this.searchService = searchService;
        this.resumeService = resumeService;
    }

    @GetMapping("/resumes")
    @ResponseBody
    public List<ResumeDto> findAllBySpecification(@RequestParam(value = "search", required = false) String search,
                                                  int page,
                                                  int size,
                                                  int coll) {
        List<?> results = searchService.getResumesResults(search, page, size, coll);
        return (List<ResumeDto>) results;
    }

    @GetMapping("/restApi/resume/{id}")
    @ResponseBody
    public ResponseEntity<ResumeDto> get(@PathVariable long id) {
        return ResponseEntity.ok(resumeService.findById(id));
    }

    @PostMapping("/restApi/resume")
    public void save(@RequestBody ResumeForm resumeForm, @CurrentUser User user) {
        resumeService.save(resumeForm, (Student) user);
    }

    @DeleteMapping("/restApi/resume/{id}")
    public void delete(@PathVariable Long id) {
        resumeService.delete(id);
    }

}
