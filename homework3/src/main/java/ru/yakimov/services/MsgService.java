package ru.yakimov.services;

/**
 * helper for localization
 * 
 * @author pavel
 *
 */
public interface MsgService {
	String get(String key);

	String get(String key, Object... params);
}
