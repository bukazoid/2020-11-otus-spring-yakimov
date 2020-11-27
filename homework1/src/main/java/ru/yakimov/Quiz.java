package ru.yakimov;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.Shell;

@Slf4j
public class Quiz {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

		QuizReader qReader = context.getBean(QuizReader.class);
		Shell shell = context.getBean(Shell.class);
		QuestionProcessor processor = context.getBean(QuestionProcessor.class);

		// intro
		shell.printLine("#", 20);
		shell.printLine("Welcome to our quiz!");
		shell.printLine("#", 20);
		shell.printLine();
		shell.printText("To continue, Please enter your name: ");
		String name = shell.readInput();
		shell.printLine();

		// say hello
		shell.printLine(String.format("Hello, student '%s'! Be ready to check your knowledge and suitability.", name));
		shell.printLine();

		int correct = 0;
		List<QuizQuestion> questions = qReader.readQuestions();
		for (QuizQuestion question : questions) {
			if (processor.processQuestion(question)) {
				correct++;
			}
			shell.printLine();
		}

		shell.printLine();
		shell.printLine(String.format("Your result is %s out of %s.\n\tYou you have %.2f%% of correct answers", correct,
				questions.size(), 100f * correct / questions.size()));

		context.close();
	}

}
