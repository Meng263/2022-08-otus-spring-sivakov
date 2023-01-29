package ru.otus.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("возвращать ожидаемое количество пёрсонов в БД")
    @Test
    void shouldReturnExpectedPersonCount() {
        long actualPersonsCount = authorDao.count();
        assertThat(actualPersonsCount).isEqualTo(1);
    }
}
