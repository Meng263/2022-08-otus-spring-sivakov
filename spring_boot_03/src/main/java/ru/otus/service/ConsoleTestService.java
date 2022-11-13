package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private final ApplicationConfig applicationConfig;
    private final BufferedReader bufferedReader;
    private final MessageSource messageSource;

    public ConsoleTestService(QuestionsProvider questionsProvider,
                              StudentRegisterService studentRegisterService,
                              ApplicationConfig applicationConfig, InputStream inputStream,
                              MessageSource messageSource) {
        this.questionsProvider = questionsProvider;
        this.studentRegisterService = studentRegisterService;
        this.applicationConfig = applicationConfig;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.messageSource = messageSource;
    }

    @Autowired
    public ConsoleTestService(QuestionsProvider questionsProvider,
                              StudentRegisterService studentRegisterService,
                              ApplicationConfig applicationConfig,
                              MessageSource messageSource) {
        this.questionsProvider = questionsProvider;
        this.studentRegisterService = studentRegisterService;
        this.applicationConfig = applicationConfig;
        this.messageSource = messageSource;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void testStudent() {
        Student student = studentRegisterService.registerStudent();
        int allQuestionsCount = applicationConfig.getAnswers().getAll();
        List<Question> questions = questionsProvider.getQuestions(allQuestionsCount);

        long count = questions.stream().filter(this::checkAnswer).count();
        boolean isTestPassed = count >= applicationConfig.getAnswers().getRight();

        System.out.println(messageSource.getMessage("test.summary",
                new Object[]{student.getFullName(), count, allQuestionsCount},
                applicationConfig.getLocale()));

        System.out.println(messageSource.getMessage("test.pass", new Object[]{isTestPassed}, applicationConfig.getLocale()));
    }

    private boolean checkAnswer(Question question) {
        System.out.println(question.buildContentWithAnswers(messageSource, applicationConfig.getLocale()));
        int answer = -1;
        try {
            answer = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer == question.getRightAnswer() + 1;
    }
}
