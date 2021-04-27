package ru.yakimov.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.yakimov.spring.mongo.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

//	@Query("SELECT COUNT(a) FROM Author a")
//	long count();
	Author findByName(String name);
}
