package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Test {
	private static String pathToFile2 = "./file2.txt";
	private final static String pathToPrivateKey1 = "./private1.key";
	private final static String pathToPublicKey1 = "./public1.key";
	private final static String pathToPrivateKey2 = "./private2.key";
	private final static String pathToPublicKey2 = "./public2.key";

	private final static String pathToFile3 = "./file3.txt";

	public static void main(String[] args) {

		RSAEncryptor.generateKeyPair(pathToPrivateKey1, pathToPublicKey1);// WORKS
		RSAEncryptor.generateKeyPair(pathToPrivateKey2, pathToPublicKey2);// WORKS
		String message = "#abcdefghijklmnopqrstuvwxyz#";

		PNG_Encryptor.addMessageToPicture("./pic.png", message, "./pic1.png", pathToFile2, pathToPublicKey2);// WORKS
		saveHash_IntoFile3(message);
		String ontvangen = PNG_Encryptor.readMessageFromPicture("./pic1.png", pathToFile2, pathToPrivateKey2);
		System.out.println(ontvangen);
		System.out.println("Is de HASH correct? : " + checkIfHashIsCorrect(
				leesDecrypteerEnPrintHash(pathToFile3, pathToPublicKey1), (MD5.getHash(ontvangen))));
		// WAV_Encryptor.encryptWAV("./res/scatman.wav",
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		// WAV_Encryptor.printMessage("./res/hidden.wav");

	}

	// Programma encrypteert die hash met de private key van Alice, en saved
	// het resultaat in een file (File_3)
	private static void saveHash_IntoFile3(String message) {
		try (PrintWriter writer = new PrintWriter(new File(pathToFile3))) {
			byte[] encryptedHashedMessage = RSAEncryptor.encryptPrivate(pathToPrivateKey1,
					MD5.getHash(message).getBytes());
			for (int i = 0; i < encryptedHashedMessage.length - 1; i++) {
				writer.println(encryptedHashedMessage[i]);
			}
			writer.print(encryptedHashedMessage[encryptedHashedMessage.length - 1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static String leesDecrypteerEnPrintHash(String fileToHash, String puKey1) {
		try {
			byte[] hash = new byte[128];
			BufferedReader br = new BufferedReader(new FileReader(new File(fileToHash)));
			for (int i = 0; i < hash.length; i++) {
				hash[i] = Byte.valueOf(br.readLine());
			}
			br.close();
			byte[] echteHash = RSAEncryptor.decryptPublic(puKey1, hash);
			return new String(echteHash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean checkIfHashIsCorrect(String hash, String hash2) {
		return hash.equals(hash2);
	}

}
