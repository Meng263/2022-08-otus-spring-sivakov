package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.jdbc.dao.BookDao;

@ShellComponent
@RequiredArgsConstructor
public class BookShellCommand {

    private final BookDao bookDao;

    @ShellMethod(key = "books-count", value = "Возвращает количество всех книг в БД")
    public String getAllAuthorsCount() {
        var count = bookDao.count();
        return String.format("Количество книг в БД: %d", count);
    }

}
