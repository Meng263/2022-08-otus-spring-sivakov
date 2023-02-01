package ru.otus.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations operations;

    @Override
    public long count() {
        Long count = operations.getJdbcOperations().queryForObject("select count (*) from books;", Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        operations.update("insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                getParamSource(book),
                keyHolder);
        long id = keyHolder.getKey().longValue();
        book.setId(id);
        return book;
    }

    @Override
    public Book update(Book book) {
        operations.update("update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id",
                getParamSource(book));
        return getById(book.getId()).orElseThrow();
    }

    @Override
    public boolean deleteById(long id) {
        return operations.update("delete from books where id = :id", Map.of("id", id)) == 1;
    }

    @Override
    public Optional<Book> getById(long id) {
        return operations.query("select b.id, b.name, b.author_id, b.genre_id, g.name as genre, a.name as author " +
                                "from books b " +
                                "left join genres g on b.genre_id = g.id " +
                                "left join authors a on b.author_id = a.id " +
                                "where b.id = :id",
                        Map.of("id", id),
                        new BookMapper())
                .stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return operations.query("select b.id, b.name, b.author_id, b.genre_id, g.name as genre, a.name as author " +
                "from books b " +
                "left join genres g on b.genre_id = g.id " +
                "left join authors a on b.author_id = a.id ", new BookMapper());
    }

    private MapSqlParameterSource getParamSource(Book book) {
        return new MapSqlParameterSource(Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId()
        ));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre"));
            Author author = new Author(rs.getLong("author_id"), rs.getString("author"));
            return new Book(rs.getLong("id"), rs.getString("name"), author, genre);
        }
    }
}
