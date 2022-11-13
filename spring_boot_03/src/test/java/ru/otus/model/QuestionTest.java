package ru.otus.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.otus.fixtures.RawKeyMessageSource;

import java.util.List;
import java.util.Locale;


class QuestionTest {
    @Test
    public void whenHasThreeAnswersGenerateStringCorrect() {
        MessageSource messageSource = new RawKeyMessageSource();
        String expected = "How many oceans are there in the world?"
                + System.lineSeparator()
                + "1) 3"
                + System.lineSeparator()
                + "2) 4"
                + System.lineSeparator()
                + "3) 5"
                + System.lineSeparator();

        String result = new Question("How many oceans are there in the world?",
                2,
                List.of(new Answer("3"),
                        new Answer("4"),
                        new Answer("5")
                )
        ).buildContentWithAnswers(messageSource, Locale.ENGLISH);

        Assertions.assertEquals(expected, result);
    }
}
