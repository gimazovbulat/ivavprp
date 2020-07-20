package ru.itis.ivavprp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ivavprp.models.FileInfo;

public interface FilesRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByStorageFileName(String storageFileName);
}
