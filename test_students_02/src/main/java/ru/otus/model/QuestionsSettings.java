package ru.otus.model;

public class QuestionsSettings {
    private final int rightQuestionsCount;
    private final int allQuestionsCount;
    private final String csvFilePath;

    public QuestionsSettings(int rightQuestionsCount, int allQuestionsCount, String csvFilePath) {
        this.rightQuestionsCount = rightQuestionsCount;
        this.allQuestionsCount = allQuestionsCount;
        this.csvFilePath = csvFilePath;
    }

    public int getRightQuestionsCount() {
        return rightQuestionsCount;
    }

    public int getAllQuestionsCount() {
        return allQuestionsCount;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public static QuestionsSettings getDefault() {
        return new QuestionsSettings(4, 5, "test_questions.csv");
    }
}
