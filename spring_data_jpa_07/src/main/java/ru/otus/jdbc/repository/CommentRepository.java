package ru.otus.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.BookComment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<BookComment, Long>, CommentRepositoryCustom {
}
