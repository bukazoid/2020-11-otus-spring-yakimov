package ru.yakimov.spring.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import ru.yakimov.spring.mvc.domain.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT COUNT(b) FROM Book b")
	long count();

	// @EntityGraph(attributePaths = "author", type = EntityGraphType.LOAD) as an
	// option
	@EntityGraph(value = "book-author", type = EntityGraphType.LOAD) // ...
	List<Book> findAll();
}
