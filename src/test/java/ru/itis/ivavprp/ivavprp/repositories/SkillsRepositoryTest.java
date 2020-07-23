package ru.itis.ivavprp.ivavprp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.ivavprp.config.TestApplicationConfig;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.repositories.SkillsRepository;

@SpringBootTest
public class SkillsRepositoryTest {
    private SkillsRepository skillsRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        skillsRepository = context.getBean(SkillsRepository.class);
    }

    @Transactional
    @Test
    void save() {
        Skill savedSkill = skillsRepository.save(Skill.builder().points(5).name("HTML").build());
        System.out.println("created id: " + savedSkill);
    }

    @Transactional
    @Test
    void delete() {
        System.out.println(skillsRepository.findById(1L));
        skillsRepository.deleteById(1L);
        System.out.println(skillsRepository.findById(1L));
    }

    @Test
    void findByName() {
        System.out.println(skillsRepository.findByName("Spring"));
    }
}
