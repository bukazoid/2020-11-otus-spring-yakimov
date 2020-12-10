package ru.yakimov.props;

import java.util.Locale;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ConfigurationProperties(prefix = "quiz")
@AllArgsConstructor
@NoArgsConstructor
public class QuizProps {
	private String file;
	private int requiredPercent;
	private Locale locale;
}
