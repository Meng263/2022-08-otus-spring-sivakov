package ru.otus.config;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.*;

@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
    @Value("${questions.path}")
    String path;

    @Bean
    public QuestionsProvider questionProvider() {
        return new ResourceQuestionProvider(path, parser());
    }

    @Bean
    public QuestionsParser parser() {
        return new SCVQuestionParser();
    }

    @Bean
    public InterfaceService interfaceService() {
        return new ConsoleInterfaceService(questionProvider());
    }

}
