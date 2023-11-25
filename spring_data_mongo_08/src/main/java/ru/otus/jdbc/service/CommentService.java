package ru.otus.jdbc.service;

import ru.otus.jdbc.model.BookComment;

import java.util.List;

public interface CommentService {
    BookComment addComment(String bookId, String commentText);


    List<BookComment> getAllComments();

    List<BookComment> getAllBookComments(String bookId);
}
