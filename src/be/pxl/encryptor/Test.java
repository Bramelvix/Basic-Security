package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) {
		String pathToKey = "./key.txt";
		String pathToPrivateKey1 = "./private1.key";
		String pathToPublicKey1 = "./public1.key";
		String pathToPrivateKey2 = "./private2.key";
		String pathToPublicKey2 = "./public2.key";
		String message = "#abcdefghijklmnopqrstuvwxyz#";
		try {
			PrintWriter writer = new PrintWriter("./file3.txt");
			byte[] encrypedHashedMessage = RSAEncryptor.encryptPrivate(pathToPrivateKey1, MD5.getHash(message).getBytes());
			for (byte b : encrypedHashedMessage) {
				writer.println(b);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PNG_Encryptor.addMessageToPicture("./res/pic.png", message, "./res/pic1.png", pathToKey, pathToPublicKey2);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./key.txt")));
			PrintWriter writer = new PrintWriter("./file2.txt");
			byte[] AES = new byte[128];
			for (int i = 0; i < 128; i++) {
				AES[i] = Byte.valueOf(reader.readLine());
			}
			byte[] encryptedAES = RSAEncryptor.encryptPublic(pathToPublicKey2, AES);
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String gekregenMessage = PNG_Encryptor.readMessageFromPicture("./res/pic1.png", pathToKey, pathToPrivateKey2);
		System.out.println(gekregenMessage);
		String gehashed = MD5.getHash(gekregenMessage);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./file2.txt")));
			byte[] uitkomst = new byte[128];
			for (int i = 0; i < uitkomst.length; i++) {
				uitkomst[i] = (Byte.valueOf(reader.readLine()));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./file2.txt")));
			byte[] key = new byte[128];
			for (int i = 0; i < 128; i++) {
				key[i] = Byte.valueOf(reader.readLine());
			}
			byte[] echtekey = RSAEncryptor.decryptPrivate(pathToPrivateKey2, key);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// WAV_Encryptor.encryptWAV("./res/scatman.wav",
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		// WAV_Encryptor.printMessage("./res/hidden.wav");
		// System.out.println(LoginChecker.isLoginValid("branco",
		// "wachtwoord"));
		// System.out.println(LoginChecker.isLoginValid("hanne", "wachtwoord"));
		// System.out.println(LoginChecker.isLoginValid("bram", "wachtwoord"));
		// String message = "message";
		// System.out.println(
		// RSAEncryptor.decrypt("./private.key",
		// RSAEncryptor.encrypt("./private.key", "./public.key", message)));

	}

}
