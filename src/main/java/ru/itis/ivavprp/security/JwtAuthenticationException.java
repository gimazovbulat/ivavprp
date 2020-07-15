package ru.itis.ivavprp.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}