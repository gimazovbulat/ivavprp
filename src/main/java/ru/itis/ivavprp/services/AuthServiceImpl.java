package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.LoginDto;
import ru.itis.ivavprp.dto.TokenDto;
import ru.itis.ivavprp.models.Token;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.repositories.TokensRepository;
import ru.itis.ivavprp.repositories.UserRepository;
import ru.itis.ivavprp.security.authentication.TokenAuthentication;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository usersRepository;

    private Integer expiredSecondsForToken = 360000;

    @Override
    public TokenDto login(LoginDto loginData) {
        Optional<User> userCandidate = usersRepository.findByEmail(loginData.getEmail());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (encoder.matches(loginData.getPassword(), user.getPassword())) {
                String value = UUID.randomUUID().toString();
                Token token = Token.builder()
                        .createdAt(LocalDateTime.now())
                        .expiredDateTime(LocalDateTime.now().plusSeconds(expiredSecondsForToken))
                        .value(value)
                        .user(user)
                        .build();
                tokensRepository.save(token);
                TokenAuthentication authentication = new TokenAuthentication();
                authentication.setToken(token.getValue());
                authentication.setAuthenticated(true);
                authentication.setUserDetails(user);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return TokenDto.from(value);
            }
        }

        throw new BadCredentialsException("Incorrect login or password");
    }


    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return;
    }


}
