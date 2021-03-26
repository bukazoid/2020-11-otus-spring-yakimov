package ru.yakimov.spring.mvc.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.NoArgsConstructor;

//@NamedEntityGraph(name = "book-author", attributeNodes = { @NamedAttributeNode("author"),
//		@NamedAttributeNode("genres") }); // also working but i don't like it, to get books, genres and authors in one request? - nothing good can go out from this

@NamedEntityGraph(name = "book-author", attributeNodes = { @NamedAttributeNode("author") })
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToMany(targetEntity = Genre.class, fetch = FetchType.EAGER)
	@JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Genre> genres;

	public Book(String title, Author author, List<Genre> genres) {
		this(null, title, author, genres);
	}

	/**
	 * to create stub for tests
	 * 
	 * @param id
	 */
	public Book(Long id) {
		this(id, null, null, null);
	}

	public Book(Long id, String title, Author author, List<Genre> genres) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genres = genres;
	}
}
