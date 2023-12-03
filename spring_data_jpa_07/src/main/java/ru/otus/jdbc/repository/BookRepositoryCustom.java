package ru.otus.jdbc.repository;

public interface BookRepositoryCustom {

    boolean deleteById(long id);

    long deleteAllWithCounter();
}
