package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.BookComment;

import java.util.List;

public interface CommentRepository extends MongoRepository<BookComment, String>, CommentRepositoryCustom {
}
