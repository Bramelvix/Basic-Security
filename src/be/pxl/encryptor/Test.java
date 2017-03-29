package be.pxl.encryptor;

public class Test {

	public static void main(String[] args) {

		// PNG_Encryptor.addMessageToPicture("./res/pic.png",
		// "#abcdefghijklmnopqrstuvwxyz#", "./res/pic1.png");
		// System.out.println(PNG_Encryptor.readMessageFromPicture("./res/pic1.png",
		// "./key.txt"));
		// WAV_Encryptor.encryptWAV("./res/scatman.wav",
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		// WAV_Encryptor.printMessage("./res/hidden.wav");
		// System.out.println(LoginChecker.isLoginValid("branco",
		// "wachtwoord"));
		// System.out.println(LoginChecker.isLoginValid("hanne", "wachtwoord"));
		// System.out.println(LoginChecker.isLoginValid("bram", "wachtwoord"));
		String message = "message";
		System.out.println(RSAEncryptor.decrypt("./private.key", "./public.key",
				RSAEncryptor.encrypt("./private.key", "./public.key", message)));

	}

}
