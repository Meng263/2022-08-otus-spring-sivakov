package ru.otus.model;

import java.util.List;
import java.util.Objects;

public class RawQuestionData {
    private final List<String> data;


    public RawQuestionData(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawQuestionData that = (RawQuestionData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
