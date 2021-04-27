package ru.yakimov.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.yakimov.spring.mongo.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
	boolean existsByAuthorId(String authorId);

}
