package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.dao.BookDao;
import ru.yakimov.spring.db.domain.Book;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookDao dao;

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public Book read(Long id) {
		return dao.read(id);
	}

	@Override
	public Book create(Book book) {
		return dao.create(book);
	}

	@Override
	public List<Book> readAll() {
		return dao.readAll();
	}

}
