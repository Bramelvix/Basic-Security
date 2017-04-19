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
			PrintWriter writer = new PrintWriter("./hash.txt");
			byte[] encryptedMessage = RSAEncryptor.encryptPrivate(pathToPrivateKey2, MD5.getHash(message));
			for (byte b : encryptedMessage) {
				writer.println(b);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PNG_Encryptor.addMessageToPicture("./res/pic.png", message, "./res/pic1.png", pathToKey, pathToPublicKey1);
		String gekregenMessage = PNG_Encryptor.readMessageFromPicture("./res/pic1.png", pathToKey, pathToPrivateKey1);
		System.out.println(gekregenMessage);
		String gehashed = MD5.getHash(gekregenMessage);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./hash.txt")));
			byte[] uitkomst = new byte[128];
			for (int i = 0; i < uitkomst.length; i++) {
				uitkomst[i] = Byte.valueOf(reader.readLine());
			}
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
