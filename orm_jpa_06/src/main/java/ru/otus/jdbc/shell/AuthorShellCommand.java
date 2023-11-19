package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.jdbc.repository.AuthorRepository;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellCommand {
    private final AuthorRepository authorDao;

    @ShellMethod(key = "authors-count", value = "Получить количество всех авторов в БД")
    public String getAllAuthorsCount() {
        var count = authorDao.count();
        return String.format("Количество авторов в БД: %d", count);
    }
}
