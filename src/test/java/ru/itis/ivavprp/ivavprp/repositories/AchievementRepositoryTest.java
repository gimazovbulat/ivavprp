package ru.itis.ivavprp.ivavprp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import ru.itis.ivavprp.ivavprp.config.TestApplicationConfig;
import ru.itis.ivavprp.models.Achievement;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.repositories.AchievementsRepository;
import ru.itis.ivavprp.repositories.SkillsRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AchievementRepositoryTest {
    /*private AchievementsRepository achievementsRepository;
    private SkillsRepository skillsRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        achievementsRepository = context.getBean(AchievementsRepository.class);
        skillsRepository = context.getBean(SkillsRepository.class);
    }

    @Test
    void save() {
        Skill skill = skillsRepository.getOne(5L);
        Skill skill2 = skillsRepository.getOne(6L);
        achievementsRepository.save(
                Achievement.builder()
                        .name("PHP junior")
                        .path("path3")
                        .skills(Arrays.asList(skill, skill2))
                        .build());

        System.out.println(achievementsRepository.getAll(PageRequest.of(0, 10)));
        assertEquals(3, achievementsRepository.getAll(PageRequest.of(0, 10)).size());
    }


    @Test
    void delete() {
        achievementsRepository.deleteById(2L);
        assertEquals(1, achievementsRepository.getAll(PageRequest.of(0, 10)).size());

    }*/
}
