package be.pxl.crypto;

public class Main {

	private final static String pathToInputPicture = "./inputPicture.png";
	private final static String pathToOutputPicture = "./outputPicture.png";

	public static void main(String[] args) {
		String message = "#abcdefghijklmnopqrstuvwxyz#";
		Encryptor.encrypt(message, pathToInputPicture, pathToOutputPicture);

		String receivedMessage = Decryptor.decrypt(pathToOutputPicture);
		System.out.println(Decryptor.checkIfHashIsCorrect(pathToOutputPicture));

		Decryptor.writeOutputMessage(pathToOutputPicture);

		// MESSAGE HIDING INTO WAV IS DONE BELOW
		// WAV_Encryptor.hideMessage("./res/scatman.wav",
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "./res/hidden.wav");
		// WAV_Encryptor.showMessage("./res/hidden.wav");

	}
}