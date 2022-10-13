package ru.otus.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.model.Question;
import ru.otus.model.QuestionsSettings;
import ru.otus.model.RawQuestionData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ResourceQuestionProvider implements QuestionsProvider {
    private final QuestionsSettings settings;

    private final QuestionsParser parser;

    public ResourceQuestionProvider(QuestionsSettings settings, QuestionsParser parser) {
        this.settings = settings;
        this.parser = parser;
    }

    @Override
    public List<Question> getAllQuestions() {
        Collection<RawQuestionData> records = readCsv();
        return parser.parseQuestions(records);
    }

    @Override
    public List<Question> getQuestions(int count) {
        List<Question> allQuestions = getAllQuestions();
        assert count <= allQuestions.size();
        return allQuestions.subList(0, count);
    }

    private Collection<RawQuestionData> readCsv() {
        InputStream questionsStream = ResourceQuestionProvider.class.getClassLoader()
                .getResourceAsStream(settings.getCsvFilePath());

        Iterable<CSVRecord> records = Collections.EMPTY_LIST;
        try {
            records = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .parse(new BufferedReader(new InputStreamReader(questionsStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StreamSupport.stream(records.spliterator(), false)
                .map(record -> new RawQuestionData(record.toList()))
                .collect(Collectors.toList());
    }
}
