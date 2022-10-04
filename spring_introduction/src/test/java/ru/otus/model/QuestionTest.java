package ru.otus.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class QuestionTest {
    @Test
    public void whenHasThreeAnswersGenerateStringCorrect() {
        String expected = "Сколько океанов существует в Мире (география)?"
                + System.lineSeparator()
                + "3"
                + System.lineSeparator()
                + "4"
                + System.lineSeparator()
                + "5"
                + System.lineSeparator();

        String result = new Question("Сколько океанов существует в Мире (география)?",
                List.of(new Answer("3"),
                        new Answer("4"),
                        new Answer("5")
                )
        ).buildConsoleString();

        Assertions.assertEquals(expected, result);
    }
}
