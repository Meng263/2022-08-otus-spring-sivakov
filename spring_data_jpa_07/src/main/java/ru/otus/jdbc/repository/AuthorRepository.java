package ru.otus.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbc.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {
    Optional<Author> findByName(String name);
}
