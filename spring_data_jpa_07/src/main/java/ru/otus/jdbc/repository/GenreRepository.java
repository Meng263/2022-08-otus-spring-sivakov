package ru.otus.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbc.model.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {
    Optional<Genre> findByName(String name);
}
