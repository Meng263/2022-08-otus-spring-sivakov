package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.config.ApplicationConfig;
import ru.otus.service.InterfaceService;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        InterfaceService interfaceService = context.getBean(InterfaceService.class);
        interfaceService.doTest();
    }
}
