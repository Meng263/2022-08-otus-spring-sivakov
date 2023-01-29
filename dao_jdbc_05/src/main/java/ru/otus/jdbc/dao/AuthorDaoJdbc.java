package ru.otus.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations operations;

    @Override
    public long count() {
        return operations.getJdbcOperations().queryForObject("select count (*) from authors;", Long.class);
    }

    @Override
    public void insert(Author author) {
        operations.update("insert into authors name values :name", Map.of("name", author.getName()));
    }

    @Override
    public void deleteById(long id) {
        operations.update("delete from authors where id = :id", Map.of("id", id));
    }

    @Override
    public Author getById(long id) {
        return operations.queryForObject("select id, name from authors where id = :id",
                Map.of("id", id),
                new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return operations.query("select id, name from authors", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
