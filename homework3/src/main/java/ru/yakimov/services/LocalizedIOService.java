package ru.yakimov.services;

/**
 * advanced io with methods for localized messages
 * 
 * @author pavel
 *
 */
public interface LocalizedIOService {
	String readInput();

	void printText(String text);

	void printLine();

	void printLine(String format, Object... params);

	/*
	 * couple methods-helpers with localization support
	 */
	
	void printLocalizedText(String text);
	
	void printLocalizedLine(String format, Object... params);
}
