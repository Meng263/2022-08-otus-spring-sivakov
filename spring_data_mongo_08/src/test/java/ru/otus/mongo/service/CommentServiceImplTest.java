package ru.otus.mongo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.mongo.model.Author;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.BookComment;
import ru.otus.mongo.model.Genre;
import ru.otus.mongo.repository.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private BookRepository bookRepository;

    @DisplayName("должен возвращать все комментарии к книге")
    @Test
    void shouldReturnAllCommentsForBook() {
        Author authorHelper = new Author("10", "helper_auhtor");
        Genre genreHelper = new Genre("10", "helper_genre");
        Book bookWithId = Book.builder().id("20")
                .name("book name")
                .author(authorHelper)
                .genre(genreHelper)
                .comments(List.of(
                        BookComment.builder().id("10").text("COMMENT2").build(),
                        BookComment.builder().id("10").text("COMMENT3").build(),
                        BookComment.builder().id("10").text("COMMENT4").build()
                ))
                .build();

        given(bookRepository.findById(bookWithId.getId())).willReturn(Optional.of(bookWithId));

        List<BookComment> bookComments = commentService.getAllBookComments("20").stream()
                .sorted(Comparator.comparing(BookComment::getId)).toList();
        assertThat(bookComments.size()).isEqualTo(3);
        BookComment firstComment = bookComments.get(0);
        BookComment secondComment = bookComments.get(1);
        BookComment thirdComment = bookComments.get(2);
        assertEquals("COMMENT2", firstComment.getText());
        assertEquals("COMMENT3", secondComment.getText());
        assertEquals("COMMENT4", thirdComment.getText());
    }
}
