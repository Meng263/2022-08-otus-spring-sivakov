package ru.otus.jdbc.repository;

import ru.otus.jdbc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long count();

    Book save(Book book);

    Optional<Book> getById(long id);

    Optional<Book> getByName(String name);

    List<Book> getAll();

    boolean deleteById(long id);

    long deleteAll();
}
