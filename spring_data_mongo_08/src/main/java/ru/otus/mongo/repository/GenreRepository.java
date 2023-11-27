package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mongo.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
    Optional<Genre> findByName(String name);
}
