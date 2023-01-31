package ru.otus.jdbc.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Console {

    @ShellMethod(key = "test", value = "register student and start test")
    public void registerAndTest() {
        System.out.println("Hello world!");
    }

}
