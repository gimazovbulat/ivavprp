package ru.itis.ivavprp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.ivavprp.models.Company;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.security.CurrentUser;
import ru.itis.ivavprp.services.FilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FilesController {
    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    /*
    userType:
    1 - student,
    2 - company,
    3 - teacher
    */
    @PostMapping(value = "/files")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam("userType") Integer userType,
                                     @CurrentUser UserDetails userDetails) {
        Long userId;
        switch (userType) {
            case 1: {
                Student student = (Student) userDetails;
                userId = student.getId();
                break;
            }
            case 2: {
                Company company = (Company) userDetails;
                userId = company.getId();
                break;
            }
            case 3: {
                Teacher teacher = (Teacher) userDetails;
                userId = teacher.getId();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + userType);
        }
        filesService.save(multipartFile, userId, userType);
        return ResponseEntity.ok().build();
    }

    // localhost:8080/files/123809183093qsdas09df8af.jpeg
    @GetMapping(value = "/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        try {
            filesService.downloadFile(response, fileName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}