package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.ConsoleTestService;

@ShellComponent
public class Console {
    private final ConsoleTestService service;

    public Console(ConsoleTestService service) {
        this.service = service;
    }

    @ShellMethod(key = "test", value = "register student and start test")
    public void registerAndTest() {
        service.testStudent();
    }
}
