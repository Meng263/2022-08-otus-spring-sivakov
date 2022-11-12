package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.model.Student;

import java.io.*;

@Service
public class ConsoleStudentRegisterService implements StudentRegisterService {
    private final BufferedReader input;
    private final PrintStream  printStream;

    private final static String inviteFirstName = "Please, enter your first name:";
    private final static String inviteSecondName = "Please, enter your second name:";

    public ConsoleStudentRegisterService(InputStream input, PrintStream  printStream) {
        this.input = new BufferedReader(new InputStreamReader(input));
        this.printStream = printStream;
    }

    public ConsoleStudentRegisterService() {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.printStream = System.out;
    }

    @Override
    public Student registerStudent() {
        String firstName = "";
        String secondName = "";
        try {
            printStream.println(inviteFirstName);
            firstName = input.readLine();
            printStream.println(inviteSecondName);
            secondName = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student(firstName, secondName);
    }
}
