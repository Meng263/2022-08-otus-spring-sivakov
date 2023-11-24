package ru.otus.jdbc.repository;

import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.BookComment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    long count();

    BookComment save(BookComment comment);

    Optional<BookComment> getById(long id);

    List<BookComment> getAll();

    boolean deleteById(long id);

    long deleteAll();
}
