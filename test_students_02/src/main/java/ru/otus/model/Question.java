package ru.otus.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Question {
    private final String content;
    private final List<Answer> answers;

    private final int rightAnswer;

    public Question(String content,int rightAnswer, List<Answer> answers) {
        this.content = content;
        this.rightAnswer = rightAnswer;
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public String buildContentWithAnswers() {
        StringBuilder builder = new StringBuilder(content);
        IntStream.range(0, answers.size())
                .forEach(index -> {
                    builder.append(System.lineSeparator());
                    builder.append(index + 1).append(") ").append(answers.get(index).getContent());
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
