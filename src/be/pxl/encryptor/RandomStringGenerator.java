package be.pxl.encryptor;

import java.util.Random;

public class RandomStringGenerator {
	public static String getString(int lengte) {
		String uitkomst = "";
		Random rand = new Random();
		for (int i = 0; i < lengte; i++) {
			uitkomst = uitkomst + rand.nextInt(bound)
		}
		return uitkomst;
	}

}
