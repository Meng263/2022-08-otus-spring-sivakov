package ru.otus.jdbc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {
    Optional<Author> findByName(String name);
}
