package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.otus.config.ApplicationConfig;
import ru.otus.fixtures.RawKeyMessageSource;
import ru.otus.model.Student;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class ConsoleStudentRegisterServiceTest {
    @Test
    public void CorrectResolveStudentFromInput() {
        MessageSource messageSource = new RawKeyMessageSource();
        ApplicationConfig config = ApplicationConfig.getDefault();
        String firstName = "Ivan";
        String secondName = "Ivanov";
        InputStream inputStream = new ByteArrayInputStream((firstName + System.lineSeparator() + secondName).getBytes());
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());

        StudentRegisterService registerService = new ConsoleStudentRegisterService(inputStream, printStream, messageSource, config);
        Student student = registerService.registerStudent();

        Assertions.assertEquals(student, new Student(firstName, secondName));
    }
}
