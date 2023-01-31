package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    long count();

    Author insert(Author author);

    Author update(Author author);

    boolean deleteById(long id);

    Optional<Author> getById(long id);

    List<Author> getAll();
}
