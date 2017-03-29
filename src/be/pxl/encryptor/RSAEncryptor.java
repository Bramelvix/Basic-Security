package be.pxl.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public abstract class RSAEncryptor {

	private static void generateKey(String prkeypath, String pukeypath) {
		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);
			final KeyPair key = keyGen.generateKeyPair();

			File privateKeyFile = new File(prkeypath);
			File publicKeyFile = new File(pukeypath);

			// Create files to store public and private key
			if (privateKeyFile.getParentFile() != null) {
				privateKeyFile.getParentFile().mkdirs();
			}
			privateKeyFile.createNewFile();

			if (publicKeyFile.getParentFile() != null) {
				publicKeyFile.getParentFile().mkdirs();
			}
			publicKeyFile.createNewFile();

			// Saving the Public key in a file
			ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
			publicKeyOS.writeObject(key.getPublic());
			publicKeyOS.close();

			// Saving the Private key in a file
			ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
			privateKeyOS.writeObject(key.getPrivate());
			privateKeyOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static boolean areKeysPresent(String prkey, String pukey) {
		return (isKeyPresent(prkey) && isKeyPresent(pukey));
	}

	private static boolean isKeyPresent(String key) {
		return new File(key).exists();
	}

	private static byte[] encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	private static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

	public static byte[] encrypt(String prKeyPath, String puKeyPath, String message) {
		try {
			if (!areKeysPresent(prKeyPath, puKeyPath)) {
				generateKey(prKeyPath, puKeyPath);
			}
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(puKeyPath));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			inputStream.close();
			final byte[] cipherText = encrypt(message, publicKey);
			return cipherText;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String prKeyPath, byte[] encrypted) {
		try {
			if (!isKeyPresent(prKeyPath)) {
				return null;
			}
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(prKeyPath));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			inputStream.close();
			return decrypt(encrypted, privateKey);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
