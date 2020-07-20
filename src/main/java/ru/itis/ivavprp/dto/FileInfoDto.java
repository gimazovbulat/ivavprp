package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfoDto {
    private Long id;
    private String storageFileName;
    private String originalFileName;
    private String type;
    private String url;
}