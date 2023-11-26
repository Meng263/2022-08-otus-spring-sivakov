package ru.otus.mongo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.mongo.repository.AuthorRepository;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellCommand {
    private final AuthorRepository authorRepository;

    @ShellMethod(key = "authors-count", value = "Получить количество всех авторов в БД")
    public String getAllAuthorsCount() {
        var count = authorRepository.count();
        return String.format("Количество авторов в БД: %d", count);
    }
}
