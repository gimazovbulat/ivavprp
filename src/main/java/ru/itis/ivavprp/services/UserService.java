package ru.itis.ivavprp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.UserDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Token;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.repositories.TokensRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokensRepository tokensRepository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Optional<Token> authenticationCandidate = tokensRepository.findFirstByValue(value);
        if (authenticationCandidate.isPresent()) {
            Token token = authenticationCandidate.get();
            User user = token.getUser();
            user.setCurrentToken(token);
            return user;
        } else throw new UsernameNotFoundException("Email not found");
    }

    public boolean save(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return false;
        }
        User newUser = User.fromUserDto(userDto);
        userRepository.save(newUser);
        return true;
    }

    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email).get();
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }


}
