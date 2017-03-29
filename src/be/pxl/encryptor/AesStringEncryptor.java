package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public abstract class AesStringEncryptor {
	public static String decrypt(String encrypted, String pathToKey, String prkey) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(pathToKey)));
			List<Byte> byteArray = new ArrayList<Byte>();
			String line = br.readLine();
			while (line != null) {
				byteArray.add(Byte.valueOf(line));
				line = br.readLine();
			}
			byte[] result = new byte[byteArray.size()];
			for (int i = 0; i < byteArray.size(); i++) {
				result[i] = byteArray.get(i).byteValue();
			}
			br.close();
			String key = RSAEncryptor.decrypt(prkey, result);
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

	public static String encrypt(String value, String pathToKey, String prkey, String pukey) {
		try {
			String key = RandomStringGenerator.getString(32);
			File keyFile = new File(pathToKey);
			PrintWriter writer = new PrintWriter(keyFile);
			byte[] keyEncrypted = RSAEncryptor.encrypt(prkey, pukey, key);
			for (byte b : keyEncrypted) {
				writer.println(b);
			}
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
