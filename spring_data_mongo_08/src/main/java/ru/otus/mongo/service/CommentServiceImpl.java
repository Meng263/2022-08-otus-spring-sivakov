package ru.otus.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.BookComment;
import ru.otus.mongo.repository.BookRepository;
import ru.otus.mongo.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookComment addComment(String bookId, String commentText) {
        Book book = Book.builder().id(bookId).build();
        BookComment bookComment = BookComment.builder().text(commentText).book(book).build();
        return commentRepository.save(bookComment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> getAllBookComments(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return commentRepository.findByBook(book);
    }
}
