package ru.otus.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class QuestionTest {
    @Test
    public void whenHasThreeAnswersGenerateStringCorrect() {
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
        ).buildContentWithAnswers();

        Assertions.assertEquals(expected, result);
    }
}
