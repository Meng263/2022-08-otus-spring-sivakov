package ru.otus.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ResourceQuestionProvider implements QuestionsProvider {
    public final String resourceFilePath;

    public ResourceQuestionProvider(String resourceFilePath) {
        this.resourceFilePath = resourceFilePath;
    }

    @Override
    public List<Question> getAllQuestions() {
        Iterable<CSVRecord> records = readCsv();
        return parseQuestions(records);
    }

    private Iterable<CSVRecord> readCsv() {
        InputStream questionsStream = ResourceQuestionProvider.class.getClassLoader()
                .getResourceAsStream(resourceFilePath);

        Iterable<CSVRecord> records = Collections.EMPTY_LIST;
        try {
            records = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .parse(new BufferedReader(new InputStreamReader(questionsStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    private List<Question> parseQuestions(Iterable<CSVRecord> records) {
        return StreamSupport.stream(records.spliterator(), false)
                .map(record -> {
                    List<String> elems = record.toList();
                    return new Question(
                            elems.get(0),
                            elems.subList(1, elems.size() - 1).stream()
                                    .map(Answer::new)
                                    .collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());
    }
}
