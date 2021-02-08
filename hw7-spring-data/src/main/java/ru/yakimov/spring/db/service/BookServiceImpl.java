package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.repositories.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository dao;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	@Override
	public Book read(Long id) {
		return dao.findById(id).get();
	}

	@Transactional
	@Override
	public Book create(Book book) {
		return dao.save(book);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> readAll() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
