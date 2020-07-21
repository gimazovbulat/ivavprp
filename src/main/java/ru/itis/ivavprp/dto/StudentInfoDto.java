package ru.itis.ivavprp.dto;

import lombok.*;
import ru.itis.ivavprp.models.Resume;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDto {

    private String firstName;
    private String lastName;
    private String photo;
    private Integer course;
    private Integer rating;
    private List<Resume> resumes;
}
