package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoDto {

    private String firstName;
    private String lastName;
    private String photo;
}
