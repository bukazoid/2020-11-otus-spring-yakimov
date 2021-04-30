package ru.yakimov.spring.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	@Id
	private String id;

	private String name;

	public Author(String name) {
		super();
		this.name = name;
	}

}
