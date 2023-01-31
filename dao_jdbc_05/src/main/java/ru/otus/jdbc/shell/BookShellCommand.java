package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.jdbc.dao.BookDao;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookShellCommand {

    private final BookDao bookDao;

    @ShellMethod(key = "books-count", value = "Получить количество всех книг в БД")
    public String getAllAuthorsCount() {
        long count = bookDao.count();
        return String.format("Количество книг в БД: %d", count);
    }

    @ShellMethod(key = "get-book", value = "Получить книгу по ID")
    public String getBookById(@ShellOption() long id) {
        Book book = bookDao.getById(id).orElseThrow();
        return "Книга " + book;
    }

    @ShellMethod(key = "get-all-books", value = "Получить все книги")
    public String getAllBooks() {
        List<Book> books = bookDao.getAll();
        return books.stream()
                .map(book -> "Книга " + book + System.lineSeparator())
                .collect(Collectors.joining());
    }

    @ShellMethod(key = "delete-book", value = "Удалить книгу")
    public String deleteBook(@ShellOption() long id) {
        return bookDao.deleteById(id) ? "книга успешно удалена" : "не найдена книга с id " + id;
    }

    @ShellMethod(key = "insert-book", value = "Вставить книгу")
    public String insertBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        return bookDao.insert(new Book(name, new Author(authorId), new Genre(genreId))).toString();
    }

    @ShellMethod(key = "update-book", value = "Обновить книгу")
    public String updateBook(@ShellOption() long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        return bookDao.update(new Book(id, name, new Author(authorId), new Genre(genreId))).toString();
    }
}
