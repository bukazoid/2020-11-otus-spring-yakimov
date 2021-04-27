package ru.yakimov.spring.mongo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mongo.domain.Book;
import ru.yakimov.spring.mongo.repositories.BookRepository;

@Slf4j
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
	public Book read(String id) {
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
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public Book update(Book book) {
		log.info("book to update: {}", book);
		return dao.save(book);
	}

}
