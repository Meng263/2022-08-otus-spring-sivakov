package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.model.QuestionsSettings;
import ru.otus.service.*;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
    @Value("${questions.path}")
    String cvsPath;
    @Value("${questions.answers.right}")
    int rightQuestionsCount;
    @Value("${questions.answers.all}")
    int allQuestionsCount;

    InputStream inputStream = System.in;

    PrintStream printStream = System.out;

    @Bean
    public QuestionsProvider questionProvider() {
        return new ResourceQuestionProvider(questionsSettings(), parser());
    }

    @Bean
    public QuestionsParser parser() {
        return new SCVQuestionParser();
    }

    @Bean
    public StudentRegisterService studentRegisterService() {
        return new ConsoleStudentRegisterService(inputStream, printStream);
    }

    @Bean
    public QuestionsSettings questionsSettings() {
        return new QuestionsSettings(rightQuestionsCount, allQuestionsCount, cvsPath);
    }

    @Bean
    public TestService testService() {
        return new ConsoleTestService(questionProvider(), studentRegisterService(), questionsSettings(), inputStream);
    }
}
