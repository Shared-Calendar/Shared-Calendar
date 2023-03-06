package study.sharedcalendar.service;

public interface EncryptionService {
	String encrypt(String password);

	boolean isMatch(String password, String hashed);
}