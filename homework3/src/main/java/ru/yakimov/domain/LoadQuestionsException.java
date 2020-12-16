package ru.yakimov.domain;

public class LoadQuestionsException extends Exception {
	public LoadQuestionsException(String resource, Exception cause) {
		super("can't read resource " + resource, cause);
	}
}
