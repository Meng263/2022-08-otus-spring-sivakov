package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.config.ApplicationConfig;
import ru.otus.service.TestService;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TestService testService = context.getBean(TestService.class);
        testService.testStudent();
    }
}
