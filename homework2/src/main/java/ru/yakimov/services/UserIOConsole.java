package ru.yakimov.services;

import java.util.Scanner;

public class UserIOConsole implements UserIO {

	final Scanner scanner;

	public UserIOConsole() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void printLine(String format, Object... params) {
		System.out.println(String.format(format, (Object[]) params));
	}

	@Override
	public void printLine() {
		printLine("");
	}

	@Override
	public void printText(String text) {
		System.out.print(text);
	}

	@Override
	public String readInput() {
		return scanner.nextLine();
	}
}
