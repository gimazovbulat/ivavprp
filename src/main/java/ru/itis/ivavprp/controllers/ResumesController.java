package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.search.SearchService;

import java.util.List;

@RestController
public class ResumesController {
    private final SearchService searchService;

    public ResumesController(SearchService searchService) {
        this.searchService = searchService;
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
}
