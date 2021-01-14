package ru.yakimov.spring.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	private final Long id;

	private final String title;
	private final Author author;
	private final Genre genre;
}
