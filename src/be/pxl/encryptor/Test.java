package be.pxl.encryptor;

public class Test {

	public static void main(String[] args) {
		//String key = RandomStringGenerator.getString(32);
		//PNG_Encryptor.addMessageToPicture("./res/pic.png", "#abcdefghijklmnopqrstuvwxyz#", "./res/pic1.png",
		//		key);
		//System.out.println(PNG_Encryptor.readMessageFromPicture("./res/pic1.png", key));
		//WAV_Encryptor.encryptWAV("./res/scatman.wav", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		//WAV_Encryptor.printMessage("./res/hidden.wav");
		System.out.println(LoginChecker.isLoginValid("branco", "wachtwoord"));
		System.out.println(LoginChecker.isLoginValid("hanne", "wachtwoord"));
		System.out.println(LoginChecker.isLoginValid("bram", "wachtwoord"));

	}

}
