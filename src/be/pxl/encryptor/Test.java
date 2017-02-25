package be.pxl.encryptor;

public class Test {

	public static void main(String[] args) {
		FotoCryptor.addMessageToPicture("./res/pic.png", "#abcdefghijklmnopqrstuvwxyz#", "./res/pic1");
		System.out.println(FotoCryptor.readMessageFromPicture("./res/pic1.png"));

	}

}
