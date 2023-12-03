package ru.otus.jdbc.service;

import ru.otus.jdbc.model.BookComment;

import java.util.List;

public interface CommentService {
    BookComment addComment(long bookId, String commentText);


    List<BookComment> getAllComments();

    List<BookComment> getAllBookComments(long bookId);
}
