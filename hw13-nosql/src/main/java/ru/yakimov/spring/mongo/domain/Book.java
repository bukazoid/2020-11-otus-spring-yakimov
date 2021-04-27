package ru.yakimov.spring.mongo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "books")
@Data
@NoArgsConstructor
public class Book {
	@Id
//	@Column(name = "book_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String title;

	@DBRef
	private Author author;

	private List<Genre> genres;

	public Book(String title, Author author, List<Genre> genres) {
		this(null, title, author, genres);
	}

	/**
	 * to create stub for tests
	 * 
	 * @param id
	 */
	public Book(String id) {
		this(id, null, null, null);
	}

	public Book(String id, String title, Author author, List<Genre> genres) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genres = genres;
	}
}
