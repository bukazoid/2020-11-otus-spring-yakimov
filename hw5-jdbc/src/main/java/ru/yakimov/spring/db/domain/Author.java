package ru.yakimov.spring.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
	private final Long id;

	private final String name;
}
