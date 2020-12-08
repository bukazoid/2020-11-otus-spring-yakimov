package ru.yakimov.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserIOConsole implements UserIO {

	final private Scanner scanner;
	final private PrintStream out;

	public UserIOConsole(InputStream in, PrintStream out /* no symmetry :( */) {
		scanner = new Scanner(in);
		this.out = out;
	}

	@Override
	public void printLine(String format, Object... params) {
		out.println(String.format(format, params));
	}

	@Override
	public void printLine() {
		printLine("");
	}

	@Override
	public void printText(String text) {
		out.print(text);
	}

	@Override
	public String readInput() {
		return scanner.nextLine();
	}
}
