package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.model.Student;

import java.io.*;
import java.util.Locale;

@Service
public class ConsoleStudentRegisterService implements StudentRegisterService {
    private final BufferedReader input;
    private final PrintStream printStream;
    private final MessageSource messageSource;
    private final ApplicationConfig config;

    public ConsoleStudentRegisterService(InputStream input,
                                         PrintStream printStream,
                                         MessageSource messageSource,
                                         ApplicationConfig config) {
        this.input = new BufferedReader(new InputStreamReader(input));
        this.printStream = printStream;
        this.messageSource = messageSource;
        this.config = config;
    }

    @Autowired
    public ConsoleStudentRegisterService(MessageSource messageSource, ApplicationConfig config) {
        this.messageSource = messageSource;
        this.config = config;
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.printStream = System.out;
    }

    @Override
    public Student registerStudent() {
        String firstName = "";
        String secondName = "";
        try {
            Locale locale = config.getLocale();
            printStream.println(messageSource.getMessage("register.invite.first_name", null, locale));
            firstName = input.readLine();
            printStream.println(messageSource.getMessage("register.invite.second_name", null, locale));
            secondName = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student(firstName, secondName);
    }
}
