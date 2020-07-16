package ru.itis.ivavprp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itis.ivavprp.security.filters.TokenAuthenticationFilter;
import ru.itis.ivavprp.security.providers.TokenAuthenticationProvider;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@ComponentScan("ru.itis.ivavprp")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // подключем провайдер, который мы написали
    @Autowired
    private TokenAuthenticationProvider provider;

    // конфигурируем AuthenticationManager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // прикручиваем наш провайдер
        auth.authenticationProvider(provider);
    }

    // конфигурирем саму безопасность
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // отключаем csrf
        http.csrf().disable();
        // отключаем сессии
        http.sessionManagement().disable();
        // добавляем наш фильтр
        http.addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class);
        // говорим, что разрешаем Swagger
        http.authorizeRequests().antMatchers("/").permitAll();
    }
}
