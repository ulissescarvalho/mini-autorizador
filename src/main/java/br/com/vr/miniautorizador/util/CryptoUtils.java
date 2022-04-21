package br.com.vr.miniautorizador.util;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoUtils {

	public static String decryptPassword(String passwordFromConfigFile) {
		StrongTextEncryptor aesEncryptor = new StrongTextEncryptor();
		aesEncryptor.setPassword("pass");
		String decryptedPassword = aesEncryptor.decrypt(passwordFromConfigFile);
		return decryptedPassword;
	}

	public static String encryptPassword(String password) {
		StrongTextEncryptor aesEncryptor = new StrongTextEncryptor();
		aesEncryptor.setPassword("pass");
		String myEncryptedPassword = aesEncryptor.encrypt(password);
		return myEncryptedPassword;
	}
}
