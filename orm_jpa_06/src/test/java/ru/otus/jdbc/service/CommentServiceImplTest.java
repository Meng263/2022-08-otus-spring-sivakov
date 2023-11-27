package ru.otus.jdbc.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.BookComment;
import ru.otus.jdbc.repository.BookRepositoryJpa;
import ru.otus.jdbc.repository.CommentRepositoryJpa;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({CommentServiceImpl.class, CommentRepositoryJpa.class, BookRepositoryJpa.class})
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @DisplayName("должен возвращать все комментарии к книге")
    @Test
    void shouldReturnAllCommentsForBook() {
        List<BookComment> bookComments = commentService.getAllBookComments(20).stream()
                .sorted(Comparator.comparingLong(BookComment::getId)).toList();
        assertThat(bookComments.size()).isEqualTo(3);
        BookComment firstComment = bookComments.get(0);
        BookComment secondComment = bookComments.get(1);
        BookComment thirdComment = bookComments.get(2);
        assertEquals("COMMENT2", firstComment.getText());
        assertEquals("COMMENT3", secondComment.getText());
        assertEquals("COMMENT4", thirdComment.getText());
    }
}
