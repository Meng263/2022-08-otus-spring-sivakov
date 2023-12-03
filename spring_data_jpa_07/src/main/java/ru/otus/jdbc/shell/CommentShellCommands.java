package ru.otus.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.jdbc.model.BookComment;
import ru.otus.jdbc.service.CommentService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellCommands {

    private final CommentService commentService;

    @ShellMethod(key = {"add-comment"})
    public String addNewComment(long bookId, String commentText) {
        return commentService.addComment(bookId, commentText).toString();
    }

    @ShellMethod(key = {"get-all-comments"})
    public String getAllComments() {
        return commentService.getAllComments().stream()
                .map(BookComment::toShortString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(key = {"get-all-comments-for-book"})
    public String getAllBookComments(long bookId) {
        return commentService.getAllBookComments(bookId).stream()
                .map(BookComment::toShortString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
