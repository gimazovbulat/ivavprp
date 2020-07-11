package ru.itis.ivavprp.ivavprp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.ivavprp.config.TestApplicationConfig;
import ru.itis.ivavprp.services.SkillsService;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillsServiceTest {
    @Autowired
    SkillsService skillsService;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        skillsService = context.getBean(SkillsService.class);
    }

    @Test
    public void save() {
        assertThrows(IllegalStateException.class, () -> {
            skillsService.save(SkillDto.builder().name("asfdd").points(-1).build());
        });
    }



}
