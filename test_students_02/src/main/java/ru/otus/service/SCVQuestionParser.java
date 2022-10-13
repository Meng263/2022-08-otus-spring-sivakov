package ru.otus.service;

import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.RawQuestionData;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SCVQuestionParser implements QuestionsParser {

    @Override
    public List<Question> parseQuestions(Collection<RawQuestionData> records) {
        return records.stream()
                .map(record -> {
                    List<String> elems = record.getData();
                    return new Question(
                            elems.get(0),
                            Integer.parseInt(elems.get(1)),
                            elems.subList(2, elems.size()).stream()
                                    .map(Answer::new)
                                    .collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());
    }
}
