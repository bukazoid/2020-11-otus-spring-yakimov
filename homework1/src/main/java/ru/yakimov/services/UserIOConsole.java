package ru.yakimov.services;

public class UserIOConsole implements UserIO {
	/**
	 * predefined answer till we have proper input system
	 */
	final static String answer = "1";

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
