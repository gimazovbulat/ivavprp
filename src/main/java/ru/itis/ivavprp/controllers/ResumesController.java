package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.search.SearchService;
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

    @GetMapping("/resume/{id}")
    @ResponseBody
    public ResumeDto findById(@PathVariable Long id) {
        ResumeDto resumeDto = resumeService.findById(id);
        return resumeDto;
    }
}
