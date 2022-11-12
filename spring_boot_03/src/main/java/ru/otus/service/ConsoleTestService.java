package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ConsoleTestService implements TestService {
    private final QuestionsProvider questionsProvider;
    private final StudentRegisterService studentRegisterService;
    private final ApplicationConfig questionsSettings;
    private final BufferedReader bufferedReader;

    public ConsoleTestService(QuestionsProvider questionsProvider,
                              StudentRegisterService studentRegisterService,
                              ApplicationConfig questionsSettings, InputStream inputStream) {
        this.questionsProvider = questionsProvider;
        this.studentRegisterService = studentRegisterService;
        this.questionsSettings = questionsSettings;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Autowired
    public ConsoleTestService(@Autowired QuestionsProvider questionsProvider,
                              @Autowired StudentRegisterService studentRegisterService,
                              @Autowired ApplicationConfig questionsSettings) {
        this.questionsProvider = questionsProvider;
        this.studentRegisterService = studentRegisterService;
        this.questionsSettings = questionsSettings;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void testStudent() {
        Student student = studentRegisterService.registerStudent();
        int allQuestionsCount = questionsSettings.getAnswers().getAll();
        List<Question> questions = questionsProvider.getQuestions(allQuestionsCount);

        long count = questions.stream().filter(this::checkAnswer).count();
        boolean isTestPassed = count >= questionsSettings.getAnswers().getRight();

        System.out.println(student.getFullName() + " your result is " + count + " right answers from "
                + allQuestionsCount);
        System.out.println("test pass: " + isTestPassed);
    }

    private boolean checkAnswer(Question question) {
        System.out.println(question.buildContentWithAnswers());
        int answer = -1;
        try {
            answer = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer == question.getRightAnswer() + 1;
    }
}
