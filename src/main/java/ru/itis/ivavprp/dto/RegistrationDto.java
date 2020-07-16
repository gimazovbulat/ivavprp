package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationDto {
    private String email;
    private String password;
    private String role;
}
