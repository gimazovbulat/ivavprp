package ru.itis.ivavprp.ivavprp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.ivavprp.config.TestApplicationConfig;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.repositories.StudentRepository;

@SpringBootTest
public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        studentRepository = context.getBean(StudentRepository.class);
    }

    @Transactional
    @Test
    void save() {
        Student student = studentRepository.save(Student.studentBuilder().email("qwerty1234").password("qwe").build());
        System.out.println(student);
    }
}
