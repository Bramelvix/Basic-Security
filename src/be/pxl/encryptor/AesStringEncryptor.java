package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public abstract class AesStringEncryptor {
	public static String decrypt(String encrypted, String pathToKey) {
		try {
			String key = "";
			BufferedReader br = new BufferedReader(new FileReader(new File(pathToKey)));
			String line = br.readLine();
			if (line != null) {
				key = line.trim();
			}
			br.close();
			IvParameterSpec iv = new IvParameterSpec(key.substring(16, 32).getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.substring(0, 16).getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static String encrypt(String value) {
		try {
			String key = RandomStringGenerator.getString(32);
			File keyFile = new File("key.txt");
			PrintWriter writer = new PrintWriter(keyFile);
			writer.write(key);
			writer.close();
			IvParameterSpec iv = new IvParameterSpec(key.substring(16, 32).getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.substring(0, 16).getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return DatatypeConverter.printBase64Binary(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
