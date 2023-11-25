package ru.otus.jdbc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.jdbc.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
    Optional<Genre> findByName(String name);
}
