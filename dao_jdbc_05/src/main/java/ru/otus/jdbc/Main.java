package ru.otus.jdbc;

import org.h2.tools.Console;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        Console.main(args);
    }
}
