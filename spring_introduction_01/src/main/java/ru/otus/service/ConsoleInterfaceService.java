package ru.otus.service;

public class ConsoleInterfaceService implements InterfaceService {
    private final QuestionsProvider questionsProvider;

    public ConsoleInterfaceService(QuestionsProvider questionsProvider) {
        this.questionsProvider = questionsProvider;
    }

    @Override
    public void showQuestions() {
        questionsProvider.getAllQuestions()
                .forEach(question -> System.out.println(question.buildConsoleString()));
    }
}
