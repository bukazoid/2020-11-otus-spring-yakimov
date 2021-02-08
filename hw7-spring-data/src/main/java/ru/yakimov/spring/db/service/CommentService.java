package ru.yakimov.spring.db.service;

import java.util.List;

import ru.yakimov.spring.db.domain.BookComment;

public interface CommentService {
	long count();

	BookComment read(Long id);

	void delete(Long id);

	void update(BookComment comment);

	BookComment create(BookComment book);

	List<BookComment> readAll();

	List<BookComment> readByBook(long bookId);
}