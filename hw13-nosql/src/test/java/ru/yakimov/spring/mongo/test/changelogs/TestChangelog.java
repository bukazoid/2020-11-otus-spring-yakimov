package ru.yakimov.spring.mongo.test.changelogs;

import java.util.Arrays;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import lombok.Data;
import ru.yakimov.spring.mongo.domain.Author;
import ru.yakimov.spring.mongo.domain.Book;
import ru.yakimov.spring.mongo.domain.Genre;
import ru.yakimov.spring.mongo.domain.User;
import ru.yakimov.spring.mongo.repositories.AuthorRepository;
import ru.yakimov.spring.mongo.repositories.BookRepository;
import ru.yakimov.spring.mongo.repositories.GenreRepository;
import ru.yakimov.spring.mongo.repositories.UserRepository;

@Data
@ChangeLog
public class TestChangelog {

	@ChangeSet(order = "001", id = "dropDb", author = "frozen", runAlways = true)
	public void dropDb(MongoDatabase db) {
		db.drop();
	}

	@ChangeSet(order = "001", id = "insertAdmin", author = "bukazoid")
	public void insertPushkin(UserRepository repository) {
		repository.save(new User("admin", "admin", "ROLE_ADMIN"));
	}

	@ChangeSet(order = "002", id = "insertAuthors", author = "bukazoid")
	public void insertAuthors(AuthorRepository repository) {
		repository.save(new Author("1", "Homer"));
		repository.save(new Author("2", "Shakespeare"));
		repository.save(new Author("3", "Lermontov"));
	}

	@ChangeSet(order = "003", id = "insertGenres", author = "bukazoid")
	public void insertAuthors(GenreRepository repository) {
		repository.save(new Genre("1", "Drama"));
		repository.save(new Genre("2", "Science Fiction"));
		repository.save(new Genre("3", "Fantasy"));
		repository.save(new Genre("4", "Detective"));
		repository.save(new Genre("5", "History"));
	}

	@ChangeSet(order = "004", id = "insertBooks", author = "bukazoid")
	public void insertBooks(GenreRepository gRepo, AuthorRepository aRepo, BookRepository bRepo) {

		Author aHomer = aRepo.findByName("Homer");
		Genre drama = gRepo.findByName("Drama");
		Genre history = gRepo.findByName("History");
		Book illiad = new Book("1", "Illiad", aHomer, Arrays.asList(drama, history));
		bRepo.save(illiad);

		Author aShakespeare = aRepo.findByName("Shakespeare");
		Book hamlet = new Book("2", "Hamlet", aShakespeare, Arrays.asList(drama));
		bRepo.save(hamlet);

	}
}
