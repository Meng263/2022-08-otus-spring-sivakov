package ru.otus.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbc.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    long count();

    Author save(Author author);

    Optional<Author> getById(long id);

    Optional<Author> getByName(String name);

    List<Author> getAll();

    boolean deleteById(long id);

    long deleteAll();
}
