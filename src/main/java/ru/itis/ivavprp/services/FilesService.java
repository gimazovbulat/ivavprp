package ru.itis.ivavprp.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FilesService {
    void save(MultipartFile file, Long userId, int userType);

    void downloadFile(HttpServletResponse response, String fileName) throws IOException;
}
