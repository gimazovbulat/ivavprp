package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.LoginDto;
import ru.itis.ivavprp.dto.TokenDto;
import ru.itis.ivavprp.models.Token;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.repositories.TokensRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository usersRepository;

    @Value("${token.expired}")
    private Integer expiredSecondsForToken;

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
                return TokenDto.from(value);
            }
        }
        throw new BadCredentialsException("Incorrect login or password");
    }
}
