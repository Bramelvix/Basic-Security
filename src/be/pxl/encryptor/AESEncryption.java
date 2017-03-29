import java.io.File;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESEncryption {

	
	SecretKey secretKey;
	
	
	public AESEncryption(){
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			secretKey = keyGen.generateKey();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@SuppressWarnings("resource")
	public void AESEncryptMessageInFile(String boodschap) {
		try {
			FileOutputStream fos;
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] AESEncryptedTekst = cipher.doFinal(boodschap.getBytes());
			File file_1 = new File("File_1");
			if (!file_1.exists()) {
				file_1.createNewFile();
			}
			fos = new FileOutputStream(file_1);
			fos.write(AESEncryptedTekst);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
