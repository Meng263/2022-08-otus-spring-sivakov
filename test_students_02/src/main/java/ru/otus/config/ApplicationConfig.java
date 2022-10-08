package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.ConsoleInterfaceService;
import ru.otus.service.InterfaceService;
import ru.otus.service.QuestionsProvider;
import ru.otus.service.ResourceQuestionProvider;

@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
    @Value("${questions.path}")  String path;

    @Bean
    public QuestionsProvider questionProvider() {
        return new ResourceQuestionProvider(path);
    }

    @Bean
    public InterfaceService interfaceService() {
        return new ConsoleInterfaceService(questionProvider());
    }


}
