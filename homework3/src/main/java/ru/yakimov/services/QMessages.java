package ru.yakimov.services;

/**
 * helper for localization
 * 
 * @author pavel
 *
 */
public interface QMessages {
	public String get(String key);

	public String get(String key, Object... params);
}
