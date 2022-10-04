package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.InterfaceService;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        context.refresh();

        InterfaceService interfaceService = context.getBean("InterfaceService", InterfaceService.class);
        interfaceService.showQuestions();
    }
}
