package ru.itis.ivavprp.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, TEACHER, COMPANY, STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}