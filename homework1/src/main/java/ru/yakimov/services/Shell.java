package ru.yakimov.services;

public class Shell {
	/**
	 * predefined answer till we have proper input system
	 */
	final String answer = "1";

	public void printLine(String line) {
		System.out.println(line);
	}

	public void printLine(String text, int mult) {
		for (int i = 0; i < mult; i++) {
			printText(text);
		}
		printLine();
	}

	public void printLine() {
		printLine("");
	}

	public void printText(String text) {
		System.out.print(text);
	}

	public String readInput() {
		try {
			// sleep 2-5 seconds to emulate user enter
			Thread.sleep(2000 + (int) (Math.random() * 3));
		} catch (Exception ex) {
			// do nothing
		}

		printLine(answer);// simulate user input
		return answer;
	}
}
