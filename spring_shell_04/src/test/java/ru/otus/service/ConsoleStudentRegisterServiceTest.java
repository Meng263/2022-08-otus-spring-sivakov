package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import ru.otus.config.ApplicationConfig;
import ru.otus.fixtures.RawKeyMessageSource;
import ru.otus.model.Student;

import java.io.*;

import static org.mockito.Mockito.when;

class ConsoleStudentRegisterServiceTest {
    @Test
    public void CorrectResolveStudentFromInput() throws IOException {
        MessageSource messageSource = new RawKeyMessageSource();
        ApplicationConfig config = ApplicationConfig.getDefault();
        String firstName = "Ivan";
        String secondName = "Ivanov";
        PrintStream printStream = Mockito.mock(PrintStream.class);
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        when(bufferedReader.readLine()).then(invocation -> firstName).then(invocation -> secondName);

        StudentRegisterService registerService = new ConsoleStudentRegisterService(bufferedReader, printStream, messageSource, config);
        Student student = registerService.registerStudent();

        Assertions.assertEquals(student, new Student(firstName, secondName));
    }
}
