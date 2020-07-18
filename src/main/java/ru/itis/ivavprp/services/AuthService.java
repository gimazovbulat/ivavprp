package ru.itis.ivavprp.services;


import ru.itis.ivavprp.dto.LoginDto;
import ru.itis.ivavprp.dto.TokenDto;

public interface AuthService {
    TokenDto login(LoginDto loginData);

    void logout();
}
