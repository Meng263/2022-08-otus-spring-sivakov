package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.jdbc.dao.AuthorDao;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellCommand {
    private final AuthorDao authorDao;

    @ShellMethod(key = "authors-count", value = "Возвращает количество всех авторов в БД")
    public String getAllAuthorsCount() {
        var count = authorDao.count();
        return String.format("Количество авторов в БД: %d", count);
    }

}
