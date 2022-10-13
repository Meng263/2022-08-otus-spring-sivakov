package ru.otus.service;

import ru.otus.model.Question;
import ru.otus.model.RawQuestionData;

import java.util.Collection;
import java.util.List;

public interface QuestionsParser {
    List<Question> parseQuestions(Collection<RawQuestionData> records);
}
