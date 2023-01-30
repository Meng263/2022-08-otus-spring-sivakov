package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author insert(Author author);

    Author update(Author author);

    boolean deleteById(long id);

    Author getById(long id);

    List<Author> getAll();
}
