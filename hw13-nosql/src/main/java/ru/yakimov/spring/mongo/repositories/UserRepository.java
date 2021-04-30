package ru.yakimov.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.yakimov.spring.mongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
//
//	@Query("SELECT COUNT(u) FROM User u")
//	long count();

	User findByLogin(String login);
}
