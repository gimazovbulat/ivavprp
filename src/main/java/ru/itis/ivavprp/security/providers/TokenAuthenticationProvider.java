package ru.itis.ivavprp.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.ivavprp.models.User;
import ru.itis.ivavprp.security.authentication.TokenAuthentication;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Qualifier("userService")
    @Autowired
    private UserDetailsService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication
                = (TokenAuthentication) authentication;

        User userDetails = (User) service.loadUserByUsername(tokenAuthentication.getName());
        if (userDetails != null && userDetails.getCurrentToken().isNotExpired()) {
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);

        } else {
            throw new BadCredentialsException("Incorrect Token");
        }
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
