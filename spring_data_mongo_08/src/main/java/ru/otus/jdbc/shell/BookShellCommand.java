package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.service.BookServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookShellCommand {

    private final BookServiceImpl bookService;

    @ShellMethod(key = "books-count", value = "Получить количество всех книг в БД")
    public String getAllAuthorsCount() {
        long count = bookService.getCount();
        return String.format("Количество книг в БД: %d", count);
    }

    @ShellMethod(key = "get-book", value = "Получить книгу по ID")
    public String getBookById(@ShellOption() String id) {
        Book book = bookService.findBookById(id);
        return "Книга " + book;
    }

    @ShellMethod(key = "get-all-books", value = "Получить все книги")
    public String getAllBooks() {
        List<Book> books = bookService.getAll();
        return books.stream()
                .map(book -> "Книга " + book + System.lineSeparator())
                .collect(Collectors.joining());
    }

    @ShellMethod(key = "delete-book", value = "Удалить книгу")
    public String deleteBook(@ShellOption() String id) {
        return bookService.deleteBookById(id) ? "книга успешно удалена" : "не найдена книга с id " + id;
    }

    @ShellMethod(key = "add-book", value = "Вставить книгу")
    public String insertBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String genreId) {
        return bookService.addBook(name, authorId, genreId).toString();
    }

    @ShellMethod(key = "change-book", value = "Обновить книгу")
    public String updateBook(@ShellOption() String id, @ShellOption String name, @ShellOption String authorId, @ShellOption String genreId) {
        return bookService.changeBook(id, name, authorId, genreId).toString();
    }
}
