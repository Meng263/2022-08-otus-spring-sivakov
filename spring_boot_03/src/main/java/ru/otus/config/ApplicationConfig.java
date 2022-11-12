package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "questions")
public class ApplicationConfig {
    private String path;
    private Answers answers;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public static class Answers {
        private int right;
        private int all;

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    public static ApplicationConfig getDefault() {
        ApplicationConfig config = new ApplicationConfig();
        config.setPath("test_questions.csv");
        Answers answers = new Answers();
        answers.setAll(5);
        answers.setRight(3);
        config.setAnswers(answers);
        return config;
    }
}
