package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.mongo.model.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Optional<Book> findByName(String name);
}
