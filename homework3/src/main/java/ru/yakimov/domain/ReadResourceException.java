package ru.yakimov.domain;

public class ReadResourceException extends Exception {
	public ReadResourceException(String resource) {
		super("can't read resource " + resource);
	}
}
