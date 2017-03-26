import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class RSAenAES {

	// bron
	// https://www.flexiprovider.de/examples/ExampleRSA.html
	// https://www.flexiprovider.de/examples/ExampleCrypt.html

	static FileInputStream fis;
	static FileOutputStream fos;
	static CipherOutputStream cos;
	static byte[] block = new byte[32];
	static int i;

	static PublicKey publicKeyAlice;
	static PrivateKey privateKeyAlice;

	static PublicKey publicKeyBob;
	static PrivateKey privateKeyBob;

	static KeyPairGenerator kpg;
	static File aFile;
	static byte[] encryptedTekst;
	static KeyPair kp;
	static String boodschap;
	static SecretKey secretKey;

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("1) Encrypteren");
			System.out.println("2) Decrypteren");
			int keuze = keyboard.nextInt();
			keyboard.nextLine();
			if (keuze == 1) {
				System.out.println("Boodschap : ");
				boodschap = keyboard.nextLine();
				AESKeyGenereren();
				RSAKeysGenereren();
				RSAKeysInFile();
				AESEncryptFile();
				RSAEncryptFile();

				// aFile = new File(fileNaam);
				////
				// if (aFile.exists()) {
				// RSAKeys();
				// // Decrypt();
				// } else {
				// System.out.println("Bestand bestaat niet!");
				// }

			} else if (keuze == 2) {
				Decrypt();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void Decrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IOException, IllegalBlockSizeException, BadPaddingException {

		// Cipher cipher = Cipher.getInstance("RSA");
		// // initialize the cipher in decryption mode, using the private key
		// cipher.init(Cipher.DECRYPT_MODE, privateKey);
		//
		// byte[] descryptedData = null;
		// descryptedData = cipher.doFinal(encryptedTekst);
		// System.out.println("Decrypted Data: ");
		// System.out.println(new String(descryptedData));
	}

	public static String readFile() throws IOException {

		// BufferedReader br = new BufferedReader(new FileReader(fileNaam));
		// try {
		// StringBuilder sb = new StringBuilder();
		// String line = br.readLine();
		//
		// while (line != null) {
		// sb.append(line);
		// sb.append("\n");
		// line = br.readLine();
		// }
		// return sb.toString();
		// } finally {
		// br.close();
		// }
		return null;
	}

	public static void RSAKeysGenereren() throws NoSuchAlgorithmException {
		// RSA keys genereren
		kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		// KeyPair object genereren
		kp = kpg.generateKeyPair();
		// public key
		publicKeyAlice = kp.getPublic();
		// private key1
		privateKeyAlice = kp.getPrivate();

		kp = kpg.generateKeyPair();

		publicKeyBob = kp.getPublic();
		privateKeyBob = kp.getPrivate();
	}

	public static void RSAKeysInFile() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IOException, IllegalBlockSizeException, BadPaddingException {

		// ALICE

		File public_A = new File("Public_A");
		File private_A = new File("Private_A");

		if (!private_A.exists()) {
			private_A.createNewFile();
		}

		if (!public_A.exists()) {
			public_A.createNewFile();
		}

		fos = new FileOutputStream(public_A);
		fos.write(publicKeyAlice.toString().getBytes());

		fos = new FileOutputStream(private_A);
		fos.write(privateKeyAlice.toString().getBytes());

		// BOB
		File public_B = new File("Public_B");
		File private_B = new File("Private_B");

		if (!private_B.exists()) {
			private_B.createNewFile();
		}

		if (!public_B.exists()) {
			public_B.createNewFile();
		}

		fos = new FileOutputStream(public_B);
		fos.write(publicKeyBob.toString().getBytes());

		fos = new FileOutputStream(private_B);
		fos.write(privateKeyBob.toString().getBytes());

	}

	public static void AESKeyGenereren() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		secretKey = keyGen.generateKey();
	}

	public static void AESEncryptFile() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException {

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] AESEncryptedTekst = cipher.doFinal(boodschap.getBytes());
		File file_1 = new File("File_1");
		if (!file_1.exists()) {
			file_1.createNewFile();
		}
		fos = new FileOutputStream(file_1);
		fos.write(AESEncryptedTekst);

	}

	public static void RSAEncryptFile() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, IOException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.WRAP_MODE, publicKeyBob);
		byte[] encryptedSymmetricKey = cipher.wrap(secretKey);
		File file_2 = new File("File_2");
		if (!file_2.exists()) {
			file_2.createNewFile();
		}
		fos = new FileOutputStream(file_2);
		fos.write(encryptedSymmetricKey);
	}

}
