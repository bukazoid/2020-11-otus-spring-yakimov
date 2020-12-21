package ru.yakimov.services;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yakimov.props.QuizProps;

@RequiredArgsConstructor
@Service
public class MsgServiceSimple implements MsgService {
	private final static Object[] NO_PARAMS = new Object[0];
	private final QuizProps props;
	private final MessageSource source;

	@Override
	public String get(String key) {
		try {
			return source.getMessage(key, NO_PARAMS, props.getLocale());
		} catch (NoSuchMessageException nomessage) {
			return getNotFoundKey(key);
		}
	}

	@Override
	public String get(String key, Object... params) {
		try {
			return source.getMessage(key, params, props.getLocale());
		} catch (NoSuchMessageException nomessage) {
			return getNotFoundKey(key);
		}

	}

	private String getNotFoundKey(String key) {
		return String.format("[[%s]]", key);
	}

}
