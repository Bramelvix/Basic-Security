package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public abstract class AESStringEncryptor {
	public static String decrypt(String encrypted, String pathToKey, String prkey) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(pathToKey)));
			byte[] key = new byte[128];
			for (int i = 0; i < key.length; i++) {
				key[i] = Byte.valueOf(br.readLine());
			}
			br.close();
			byte[] echtekey = RSAEncryptor.decryptPrivate(prkey, key);
			String keyString = new String(echtekey);
			IvParameterSpec iv = new IvParameterSpec(keyString.substring(16, 32).getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(keyString.substring(0, 16).getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static String maakAESKey() {
		try {
			String key = RandomStringGenerator.getString(32);
			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void encrypteerKeyEnSchrijfWeg(String key, String pathToKey, String pukey) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(pathToKey);
			byte[] keyEncrypted = RSAEncryptor.encryptPublic(pukey, key.getBytes());
			for(int i = 0;i<keyEncrypted.length-1;i++){
				writer.println(keyEncrypted[i]);
			}
			writer.print(keyEncrypted[keyEncrypted.length-1]);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public static String encrypt(String value, String pathToKey, String pukey) {
		try {
			String key = maakAESKey();
			encrypteerKeyEnSchrijfWeg(key, pathToKey, pukey);
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
