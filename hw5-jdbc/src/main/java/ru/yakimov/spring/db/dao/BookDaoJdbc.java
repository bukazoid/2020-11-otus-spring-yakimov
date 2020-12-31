package ru.yakimov.spring.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.Genre;

@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
	private final NamedParameterJdbcOperations jdbc;

	@Override
	public long count() {
		return jdbc.getJdbcOperations().queryForObject("SELECT COUNT(*) FROM books", Long.class);
	}

	@Override
	public Book read(Long id) {
		return jdbc.queryForObject(
				"SELECT book_id, name, author_id, genre_id, a.name, g.name FROM books b JOIN authors a USING(author_id) JOIN genres g USING(genre_id)  JOIN WHERE book_id = :id",
				Map.of("id", id), new BookMapper());
	}

	@Override
	public void delete(Long id) {
		jdbc.update("DELETE books WHERE book_id=:id", Map.of("id", id));
	}

	@Override
	public Book create(Book book) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource(Map.of("name", book.getTitle(), "author_id",
				book.getAuthor().getId(), "genre_id", book.getGenre().getId()));

		jdbc.update("INSERT INTO books (name, author_id, genre_id) VALUES (:name, :author_id, :genre_id)", params,
				keyHolder);
		long id = keyHolder.getKey().longValue();
		return new Book(id, book.getTitle(), book.getAuthor(), book.getGenre());
	}

	@Override
	public List<Book> readAll() {
		return null;
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet resultSet, int i) throws SQLException {

			long id = resultSet.getLong("book_id");
			String title = resultSet.getString("title");

			long author = resultSet.getLong("author_id");
			String authorName = resultSet.getString("a.name");

			long genre = resultSet.getLong("genre_id");
			String genreName = resultSet.getString("g.name");

			return new Book(id, title, new Author(author, authorName), new Genre(genre, genreName));
		}

	}

}
