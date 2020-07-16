package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.services.TeacherServiceImpl;

@RestController
@RequestMapping(value = "/teacher/")
public class TeacherController {


    private final TeacherServiceImpl teacherService;


    public TeacherController(TeacherServiceImpl teacherService) {

        this.teacherService = teacherService;

    }


}
