import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

import javax.crypto.SecretKey;

public class RSAEncryption {

	PublicKey publicKeyAlice;
	PrivateKey privateKeyAlice;

	PublicKey publicKeyBob;
	PrivateKey privateKeyBob;


	public RSAEncryption(){
		try {
			KeyPair kp;
			KeyPairGenerator kpg;
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			kp = kpg.generateKeyPair();
			
			//ALICA
			publicKeyAlice = kp.getPublic();
			privateKeyAlice = kp.getPrivate();

			kp = kpg.generateKeyPair();
			//BOB
			publicKeyBob = kp.getPublic();
			privateKeyBob = kp.getPrivate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("resource")
	public void RSAKeysInFile() {

		try {

			// ALICE
			FileOutputStream fos;
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
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("resource")
	public void RSAEncryptFile(SecretKey secretKey) {
		try {
			FileOutputStream fos;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.WRAP_MODE, publicKeyBob);
			byte[] encryptedSymmetricKey = cipher.wrap(secretKey);
			File file_2 = new File("File_2");
			if (!file_2.exists()) {
				file_2.createNewFile();
			}
			fos = new FileOutputStream(file_2);
			fos.write(encryptedSymmetricKey);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
