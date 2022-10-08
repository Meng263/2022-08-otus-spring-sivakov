package ru.otus.model;

import java.util.Objects;

public class Answer {
    private final String content;

    public String getContent() {
        return content;
    }

    public Answer(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(content, answer.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "content='" + content + '\'' +
                '}';
    }
}
