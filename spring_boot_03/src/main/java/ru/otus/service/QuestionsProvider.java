package ru.otus.service;

import ru.otus.model.Question;

import java.util.List;

public interface QuestionsProvider {
    List<Question> getAllQuestions();

    List<Question> getQuestions(int count);
}
