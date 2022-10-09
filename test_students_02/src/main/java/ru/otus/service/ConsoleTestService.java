package ru.otus.service;

import ru.otus.model.Question;
import ru.otus.model.QuestionsSettings;
import ru.otus.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleTestService implements TestService {
    private final QuestionsProvider questionsProvider;
    private final StudentRegisterService studentRegisterService;
    private final QuestionsSettings questionsSettings;
    private final BufferedReader bufferedReader;

    public ConsoleTestService(QuestionsProvider questionsProvider,
                              StudentRegisterService studentRegisterService,
                              QuestionsSettings questionsSettings, InputStream inputStream) {
        this.questionsProvider = questionsProvider;
        this.studentRegisterService = studentRegisterService;
        this.questionsSettings = questionsSettings;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void testStudent() {
        Student student = studentRegisterService.registerStudent();
        List<Question> questions = questionsProvider.getQuestions(questionsSettings.getAllQuestionsCount());

        long count = questions.stream().filter(this::checkAnswer).count();
        boolean isTestPassed = count >= questionsSettings.getRightQuestionsCount();

        System.out.println(student.getFullName() + " your result is " + count + " right answers from "
                + questionsSettings.getAllQuestionsCount());
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
