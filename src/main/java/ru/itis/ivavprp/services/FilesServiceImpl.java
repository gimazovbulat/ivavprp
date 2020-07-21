package ru.itis.ivavprp.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.ivavprp.dto.*;
import ru.itis.ivavprp.models.FileInfo;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.FilesRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.apache.commons.io.IOUtils.copy;

@Service
public class FilesServiceImpl implements FilesService {
    private final CompaniesService companiesService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final FilesRepository filesRepository;

    @Value("${storage.dir}")
    private String storageDir;

    public FilesServiceImpl(CompaniesService companiesService,
                            StudentService studentService,
                            TeacherService teacherService,
                            FilesRepository filesRepository) {
        this.companiesService = companiesService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.filesRepository = filesRepository;
    }

    /*
    userType:
    1 - student,
    2 - company,
    3 - teacher
    */
    @Transactional
    @Override
    public void save(MultipartFile file, Long userId, int userType) {
        String fileOrigName = file.getOriginalFilename();
        String storageName = UUID.randomUUID() + "." + FilenameUtils.getExtension(fileOrigName);
        Path path = Paths.get(storageDir + storageName);

        switch (userType) {
            case 1: {
                StudentInfoDto info = new StudentInfoDto();
                info.setPhoto(storageName);
                studentService.update(userId, info);
                break;
            }
            case 2: {
                CompanyInfoDto info = new CompanyInfoDto();
                info.setPhoto(storageName);
                System.out.println(info);
                companiesService.update(userId, info);
                break;
            }
            case 3: {
                TeacherInfoDto info = new TeacherInfoDto();
                info.setPhoto(storageName);
                System.out.println(info);
                teacherService.update(userId, info);
                break;
            }
        }
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(fileOrigName)
                .storageFileName(storageName)
                .type(file.getContentType())
                .url("/files/" + storageName)
                .build();

        filesRepository.save(fileInfo);
    }

    @Override
    public void downloadFile(HttpServletResponse response, String fileName) throws IOException {
        FileInfo fileInfo = filesRepository.findByStorageFileName(fileName);
        System.out.println(fileName);
        System.out.println(fileInfo);
        System.out.println(response);
        response.setContentType(fileInfo.getType());
        InputStream inputStream =
                new FileInputStream(new File(storageDir + fileInfo.getStorageFileName()));
        copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
