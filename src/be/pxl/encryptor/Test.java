package be.pxl.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Test {
	private final static String pathToKey = "./key.txt";
	private final static String pathToPrivateKey1 = "./private1.key";
	private final static String pathToPublicKey1 = "./public1.key";
	private final static String pathToPrivateKey2 = "./private2.key";
	private final static String pathToPublicKey2 = "./public2.key";
	private final static String pathToFile1 = "./file1.txt";
	private final static String pathToFile2 = "./file2.txt";
	private final static String pathToFile3 = "./file3.txt";

	public static void main(String[] args) {
		// Programma genereert 1 symmetrische key (DES of AES) TODO?

		RSAEncryptor.generateKeyPair(pathToPrivateKey1, pathToPublicKey1);// WORKS
		RSAEncryptor.generateKeyPair(pathToPrivateKey2, pathToPublicKey2);// WORKS
		String message = "#abcdefghijklmnopqrstuvwxyz#";

		// UITBREIDING
		PNG_Encryptor.addMessageToPicture("./res/pic.png", message, "./res/pic1.png", pathToKey, pathToPublicKey2);// WORKS

		saveMessageWithSymmetricKey_IntoFile1(message);
		saveEncryptedSymmetricKeyWithPublicKey2_IntoFile2();
		saveMessageAsEncryptedHashedMessage_IntoFile3(createHashFromOriginalMessage());

		//ONDERSTAANDE IS OM NOG TE VERPLAATSEN OF AAN TE PASSEN
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("./key.txt")))) {
			try (PrintWriter writer = new PrintWriter("./file2.txt")) {
				byte[] AES = new byte[128];
				for (int i = 0; i < AES.length; i++) {
					AES[i] = Byte.valueOf(reader.readLine());
				}
				byte[] encryptedAES = RSAEncryptor.encryptPublic(pathToPublicKey2, AES);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// BufferedReader reader = new BufferedReader(new FileReader(new
		// File("./file2.txt")));
		// byte[] uitkomst = new byte[128];
		// for (int i = 0; i < uitkomst.length; i++) {
		// uitkomst[i] = (Byte.valueOf(reader.readLine()));
		// }
		// reader.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// BufferedReader reader = new BufferedReader(new FileReader(new
		// File("./file2.txt")));
		// byte[] key = new byte[128];
		// for (int i = 0; i < key.length; i++) {
		// key[i] = Byte.valueOf(reader.readLine());
		// }
		// byte[] echtekey = RSAEncryptor.decryptPrivate(pathToPrivateKey2,
		// key);
		// reader.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

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

	// Programma gebruikt de symmetric key om de boodschap te encrypteren,
	// en saved het resultaat in een file (File_1)
	private static void saveMessageWithSymmetricKey_IntoFile1(String message) {

	}

	// Programma encrypteert de symmetric key met de public key van Bob, en
	// saved het resultaat in een file (File_2)
	private static void saveEncryptedSymmetricKeyWithPublicKey2_IntoFile2() {

	}

	// Programma maakt een hash van de oorspronkelijke boodschap
	private static String createHashFromOriginalMessage() {
		String message = PNG_Encryptor.readMessageFromPicture("./res/pic1.png", pathToKey, pathToPrivateKey2);
		String messageAsHash = MD5.getHash(message);
		System.out.println("----Output from createHashFromOriginalMessage()----\n" + message + "\n" + messageAsHash);
		return messageAsHash;
	}

	// Programma encrypteert die hash met de private key van Alice, en saved
	// het resultaat in een file (File_3)
	private static void saveMessageAsEncryptedHashedMessage_IntoFile3(String message) {
		try (PrintWriter writer = new PrintWriter(pathToFile3)) {
			byte[] encryptedHashedMessage = RSAEncryptor.encryptPrivate(pathToPrivateKey1,
					MD5.getHash(message).getBytes());
			for (byte b : encryptedHashedMessage) {
				writer.println(b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
