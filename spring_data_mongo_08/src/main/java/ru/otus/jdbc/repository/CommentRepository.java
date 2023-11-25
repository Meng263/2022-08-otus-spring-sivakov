package ru.otus.jdbc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.BookComment;

import java.util.List;

public interface CommentRepository extends MongoRepository<BookComment, String>, CommentRepositoryCustom {

    List<BookComment> findByBook(Book book);

}
