package ru.yakimov.services;

public interface IOService {

	String readInput();

	void printText(String text);

	void printLine();

	void printLine(String format, Object... params);

}