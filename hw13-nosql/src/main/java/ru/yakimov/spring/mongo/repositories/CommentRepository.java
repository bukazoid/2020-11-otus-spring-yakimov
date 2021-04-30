package ru.yakimov.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.yakimov.spring.mongo.domain.BookComment;

public interface CommentRepository extends MongoRepository<BookComment, String> {
//
//	@Query("SELECT COUNT(a) FROM BookComment a")
//	long count();
//
//	@Query("SELECT b FROM BookComment b WHERE b.book.id=:bookId")
//	List<BookComment> findByBookId(@Param("bookId") Long bookId);

}
