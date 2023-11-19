package ru.otus.jdbc.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;

@ShellComponent
public class ConsoleH2 {

    @ShellMethod(key = "start-h2", value = "register student and start test")
    public void startH2(@ShellOption(defaultValue = "-browser") String[] args) throws SQLException {
        Console.main(args);
    }
}
