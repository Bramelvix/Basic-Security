package be.pxl.encryptor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

/**
 * Deze klasse encrypteert text in PNG afbeeldingen en kan ook tekst uit
 * afbeeldingen lezen.
 * 
 * @author Bram Swinnen
 *
 */
public abstract class FotoCryptor {
	/**
	 * Hoofdmethode om een bericht aan een foto toe te voegen.
	 * 
	 * @param pathToPicture
	 *            path naar de foto
	 * @param message
	 *            bericht om in de foto te encrypteren
	 * @param destination
	 *            de locatie en de naam van de output foto
	 */
	public static void addMessageToPicture(String pathToPicture, String message, String destination) {
		BufferedImage picture = loadPicture(pathToPicture);
		int[] pixels = getPixels(picture);
		int w = picture.getWidth();
		int h = picture.getHeight();
		writeData(pixels, getByteArrayFromString(message));
		writeImg(getImageFromArray(pixels, w, h), destination);
	}

	/**
	 * Methode om een BufferedImage in te lezen.
	 * 
	 * @param pathToPicture
	 *            path naar de foto
	 * @return het BufferedImage object
	 */
	private static BufferedImage loadPicture(String pathToPicture) {
		if (pathToPicture.endsWith(".png")) {
			try {
				return ImageIO.read(new File(pathToPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Haalt de pixels array uit een BufferedImage.
	 * 
	 * @param pic
	 *            het BufferedImage object
	 * @return een int array met daarin de pixels
	 */
	private static int[] getPixels(BufferedImage pic) {
		int width = pic.getWidth();
		int height = pic.getHeight();
		int[] pixels = new int[width * height];
		pic.getRGB(0, 0, width, height, pixels, 0, width);
		return pixels;
	}

	/**
	 * Schrijft het bericht in de pixels array.
	 * 
	 * @param pixels
	 *            de pixels array
	 * @param data
	 *            de tekst als een byte array
	 */
	private static void writeData(int[] pixels, byte[] data) {
		int pixelCounter = 0;
		int kleurCounter = 0;
		for (int i = 0; i < data.length; i++) {
			for (int bit = 0; bit < 7; bit++) {
				pixels[pixelCounter] = setIntOn(pixels[pixelCounter], kleurCounter, getBit(data[i], bit));
				kleurCounter++;
				if (kleurCounter == 3) {
					kleurCounter = 0;
					pixelCounter++;
				}
			}
		}
	}

	/**
	 * Zet de laatste bit van de R, G of B van een pixel op 1 of 0
	 * 
	 * @param pixel
	 *            de waarde van de meegegeven pixel
	 * @param kleur
	 *            de kleur die je wilt veranderen (0 = rood, 1 = groen, 2 =
	 *            blauw)
	 * @param waarde
	 *            de waarde die je wilt setten (1 of 0)
	 * @return
	 */
	private static int setIntOn(int pixel, int kleur, int waarde) {
		int red = getRed(pixel);
		int green = getGreen(pixel);
		int blue = getBlue(pixel);
		if (kleur == 0) {
			red = setLastBitTo(red, waarde);
		} else if (kleur == 1) {
			green = setLastBitTo(green, waarde);
		} else if (kleur == 2) {
			blue = setLastBitTo(blue, waarde);
		}

		int newPixel = red;
		newPixel = (newPixel << 8) + green;
		newPixel = (newPixel << 8) + blue;
		return newPixel;
	}

	/**
	 * Zet de laatse bit van 'original' op 1 of 0
	 * 
	 * @param original
	 *            de int die je wilt veranderen
	 * @param value
	 *            de waarde (0 of 1)
	 * @return
	 */
	private static int setLastBitTo(int original, int value) {
		if (value == 1) {
			return (original | (1 << 0));
		} else {
			return (original & ~(1 << 0));
		}
	}

	/**
	 * Returnt de nde bit van 'nummer'.
	 * 
	 * @param nummer
	 * @param n
	 * @return
	 */
	private static int getBit(int nummer, int n) {
		return ((nummer >> n) & 1);
	}

	/**
	 * Returnt het rode gedeelte van de RGB van 'pixel'.
	 * 
	 * @param pixel
	 * @return
	 */
	private static int getRed(int pixel) {
		return ((pixel >> 16) & 0xFF);
	}

	/**
	 * Returnt het groene gedeelte van de RGB van 'pixel'.
	 * 
	 * @param pixel
	 * @return
	 */
	private static int getGreen(int pixel) {
		return ((pixel >> 8) & 0xFF);
	}

	/**
	 * Returnt het blauwe gedeelte van de RGB van 'pixel'.
	 * 
	 * @param pixel
	 * @return
	 */
	private static int getBlue(int pixel) {
		return (pixel & 0xFF);
	}

	/**
	 * Haalt de geëncrypteerde bytes uit de pixels array.
	 * 
	 * @param pixels
	 * @return
	 */
	private static byte[] getBytesFromPixels(int[] pixels) {
		byte[] bits = new byte[pixels.length * 3];
		int messagecounter = 0;
		for (int i = 0; i < pixels.length; i++) {
			bits[messagecounter] = (byte) getBit(getRed(pixels[i]), 0);
			messagecounter++;
			bits[messagecounter] = (byte) getBit(getGreen(pixels[i]), 0);
			messagecounter++;
			bits[messagecounter] = (byte) getBit(getBlue(pixels[i]), 0);
			messagecounter++;
		}
		byte[] bytes = new byte[bits.length / 7];
		int bytecounter = 0;
		for (int i = 0; i < bytes.length - 7; i += 7) {
			String stuk = "" + bits[i] + bits[i + 1] + bits[i + 2] + bits[i + 3] + bits[i + 4] + bits[i + 5]
					+ bits[i + 6];

			bytes[bytecounter] = Byte.parseByte(new StringBuilder(stuk).reverse().toString(), 2);
			bytecounter++;
		}
		return bytes;
	}

	/**
	 * Leest de eerste en laatste regel tekst uit een foto.
	 * 
	 * @param pathToPicture
	 * @return
	 */
	public static String readFirstAndLastLineFromPicture(String pathToPicture) {
		String[] outputStrings = getCompleteMessageAsStringArrayFromPicture(pathToPicture);
		return outputStrings[0] + "\n" + outputStrings[outputStrings.length - 1];
	}

	/**
	 * Leest alle tekst uit een foto als string array.
	 * 
	 * @param pathToPicture
	 * @return
	 */
	private static String[] getCompleteMessageAsStringArrayFromPicture(String pathToPicture) {
		BufferedImage picture = loadPicture(pathToPicture);
		int[] pixels = getPixels(picture);
		byte[] bytes = getBytesFromPixels(pixels);
		String completeString = getStringFromByteArray(bytes);
		int total = completeString.length();
		String[] outputStrings = new String[(int) (Math.floor(total / 100) + 2)];
		for (int i = 0; i * 100 < total + 100; i += 1) {
			outputStrings[i] = completeString.substring(i, i + 100);
			// DEBUG LINE System.out.println(i + "---" + total / 100 + "---" +
			// total);
		}
		return outputStrings;
	}

	/**
	 * Leest alle tekst uit een foto.
	 * 
	 * @param pathToPicture
	 * @return
	 */
	public static String readCompleteMessageFromPicture(String pathToPicture) {
		String[] outputStrings = getCompleteMessageAsStringArrayFromPicture(pathToPicture);
		String completeOutput = "";
		for (int i = 0; i < outputStrings.length; i++) {
			completeOutput += outputStrings[i] + "\n";
		}
		return completeOutput;
	}

	/**
	 * Converteert een string array naar een byte array.
	 * 
	 * @param s
	 * @return
	 */
	private static byte[] getByteArrayFromString(String s) {
		try {
			return s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converteert een byte array naar een string.
	 * 
	 * @param array
	 * @return
	 */
	private static String getStringFromByteArray(byte[] array) {
		try {
			return new String(array, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Zet een array pixels om tot een BufferedImage.
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @return
	 */
	private static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				image.setRGB(x, y, pixels[x + y * width]);
			}
		}
		return image;
	}

	/**
	 * Schrijft de BufferedImage naar de destination path
	 * 
	 * @param image
	 * @param name
	 */
	private static void writeImg(BufferedImage image, String destination) {
		try {
			ImageIO.write(image, "png", new File(destination + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
