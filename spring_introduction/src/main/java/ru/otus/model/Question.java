package ru.otus.model;

import java.util.List;
import java.util.Objects;

public class Question {
    private final String content;
    private final List<Answer> answers;

    public Question(String content, List<Answer> answers) {
        this.content = content;
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String buildConsoleString() {
        StringBuilder builder = new StringBuilder(content);
        answers.forEach(answer -> {
                    builder.append(System.lineSeparator());
                    builder.append(answer.getContent());
                }
        );
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(content, question.content) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", answers=" + answers +
                '}';
    }
}
