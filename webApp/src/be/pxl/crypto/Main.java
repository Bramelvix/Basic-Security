package be.pxl.crypto;

public class Main {

	private final static String pathToInputPicture = "./inputPicture.png";
	private final static String pathToOutputPicture = "./outputPicture.png";
	private final static String pathToInputSound = "./Scatman.wav";
	private final static String pathToOutputSound = "./hidden.wav";

	public static void main(String[] args) {
		String message = "#abcdefghijklmnopqrstuvwxyz#";
		try {
			Encryptor.encrypt(message, pathToInputSound, pathToOutputSound);
			System.out.println(Decryptor.decrypt(pathToOutputSound));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(Decryptor.checkIfHashIsCorrect(pathToOutputPicture));

		// Decryptor.writeOutputMessage(pathToOutputPicture);

		// MESSAGE HIDING INTO WAV IS DONE BELOW
		// WAV_Encryptor.hideMessage("./res/scatman.wav",
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		// WAV_Encryptor.showMessage("./res/hidden.wav");

	}
}