package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long count();

    Book insert(Book book);

    Book update(Book book);

    boolean deleteById(long id);

    Optional<Book> getById(long id);

    List<Book> getAll();
}
