package ru.yakimov.spring.db.repositories;

import java.util.List;

import ru.yakimov.spring.db.domain.BookComment;

public interface CommentRepository {

	long count();

	BookComment read(Long id);

	BookComment create(BookComment comment);

	void delete(Long id);

	void update(BookComment comment);

	List<BookComment> readAll();

}
