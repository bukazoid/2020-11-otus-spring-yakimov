package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.BookComment;
import ru.yakimov.spring.db.repositories.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository dao;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	@Override
	public BookComment read(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public BookComment create(BookComment book) {
		return dao.save(book);
	}

	@Transactional(readOnly = true)
	@Override
	public List<BookComment> readAll() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Transactional
	@Override
	public void update(BookComment comment) {
		dao.save(comment);
	}

	@Override
	public List<BookComment> readByBook(long bookId) {
		return dao.findByBookId(bookId);
	}

}
