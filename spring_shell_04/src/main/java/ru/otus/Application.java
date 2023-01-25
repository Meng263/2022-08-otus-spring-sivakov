package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.config.ApplicationConfig;
import ru.otus.service.ConsoleTestService;
import ru.otus.service.TestService;

@SpringBootApplication()
@EnableConfigurationProperties(ApplicationConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
