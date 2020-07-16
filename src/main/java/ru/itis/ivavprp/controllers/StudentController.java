package ru.itis.ivavprp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.services.StudentServiceImpl;

@RestController
@RequestMapping(value = "/student/")
public class StudentController {


    private final StudentServiceImpl studentService;


    public StudentController(StudentServiceImpl studentService) {

        this.studentService = studentService;

    }


}
