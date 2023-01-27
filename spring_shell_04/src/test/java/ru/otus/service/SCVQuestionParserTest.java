package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.RawQuestionData;

import java.util.List;

class SCVQuestionParserTest {

    @Test
    public void correctParseQuestion() {
        RawQuestionData rawQuestionData = new RawQuestionData(
                List.of("How many oceans are there in the world?",
                        "2",
                        "3",
                        "4",
                        "5")
        );
        List<Question> result = new SCVQuestionParser().parseQuestions(List.of(rawQuestionData));
        List<Question> expected = List.of(new Question("How many oceans are there in the world?",
                2,
                List.of(new Answer("3"),
                        new Answer("4"),
                        new Answer("5"))
        ));
        Assertions.assertEquals(expected, result);
    }
}
