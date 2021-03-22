package ru.yakimov.spring.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yakimov.spring.mvc.domain.BookComment;

public interface CommentRepository extends JpaRepository<BookComment, Long> {

	@Query("SELECT COUNT(a) FROM BookComment a")
	long count();

	@Query("SELECT b FROM BookComment b WHERE b.book.id=:bookId")
	List<BookComment> findByBookId(@Param("bookId") Long bookId);

}
