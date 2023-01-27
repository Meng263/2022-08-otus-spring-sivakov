package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.config.ApplicationConfig;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.util.List;

class ResourceQuestionProviderTest {
    @Test
    public void correctParseQuestionsFromResourceFile() {
        ResourceQuestionProvider qp = new ResourceQuestionProvider(ApplicationConfig.getDefault(), new SCVQuestionParser());
        List<Question> result = qp.getAllQuestions();
        List<Question> expected = List.of(new Question("How many oceans are there in the world?",
                2,
                List.of(new Answer("3"), new Answer("4"), new Answer("5"))));
        Assertions.assertEquals(expected, result);
    }
}
