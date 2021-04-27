package ru.yakimov.spring.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
	@Id
	private String id;

	private String name;

	public Genre(String name) {
		super();
		this.name = name;
	}

}
