package ru.itis.ivavprp.security.filters;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.ivavprp.security.authentication.TokenAuthentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class TokenAuthenticationFilter implements Filter {
    private final static String AUTH_HEADER = "AUTH";
    private final UserDetailsService service;

    public TokenAuthenticationFilter(@Qualifier("userService") UserDetailsService service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tokenValue = request.getHeader(AUTH_HEADER);
        if (tokenValue != null) {
            TokenAuthentication authentication = new TokenAuthentication();
            authentication.setToken(tokenValue);
            authentication.setAuthenticated(true);
            authentication.setUserDetails(service.loadUserByUsername(tokenValue));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
