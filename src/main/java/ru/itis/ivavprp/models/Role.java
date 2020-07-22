package ru.itis.ivavprp.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;

public enum Role implements GrantedAuthority {
    USER, ADMIN, TEACHER, COMPANY, STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}