package be.pxl.encryptor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

public class FotoCryptor {
	private int[] pixels;

	public static void main(String[] args) {
		FotoCryptor cryptor = new FotoCryptor();
		cryptor.addMessageToPicture("./res/pic.png", "#abcdefghijklmnopqrstuvwxyz#");
		//System.out.println(cryptor.readCompleteMessageFromPicture("./res/pic1.png"));
		System.out.println(cryptor.readFirstAndLastLineFromPicture("./res/pic1.png"));
	}

	private void addMessageToPicture(String pathToPicture, String message) {
		BufferedImage picture = loadPicture(pathToPicture);
		int[] pixels = getPixels(picture);
		int w = picture.getWidth();
		int h = picture.getHeight();
		writeData(pixels, getByteArrayFromString(message));
		this.pixels = pixels;
		writeImg(getImageFromArray(this.pixels, w, h), "pic1");
	}

	private BufferedImage loadPicture(String pathToPicture) {
		BufferedImage picture = null;
		if (pathToPicture.endsWith(".png")) {
			try {
				picture = ImageIO.read(new File(pathToPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return picture;
	}

	private int[] getPixels(BufferedImage pic) {
		int width = pic.getWidth();
		int height = pic.getHeight();
		int[] pixels = new int[width * height];
		pic.getRGB(0, 0, width, height, pixels, 0, width);
		return pixels;
	}

	private void writeData(int[] pixels, byte[] data) {
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

	private int setIntOn(int pixel, int kleur, int waarde) {
		int red = getRed(pixel);
		int green = getGreen(pixel);
		int blue = getBlue(pixel);
		if (kleur == 0) {
			red = setIntTo(red, waarde);
		} else if (kleur == 1) {
			green = setIntTo(green, waarde);
		} else if (kleur == 2) {
			blue = setIntTo(blue, waarde);
		}

		int newPixel = red;
		newPixel = (newPixel << 8) + green;
		newPixel = (newPixel << 8) + blue;
		return newPixel;
	}

	private int setIntTo(int original, int value) {
		if (value == 1) {
			return (original | (1 << 0));
		} else {
			return (original & ~(1 << 0));
		}
	}

	private int getBit(int nummer, int pos) {
		return ((nummer >> pos) & 1);
	}

	private int getRed(int pixel) {
		return ((pixel >> 16) & 0xFF);
	}

	private int getGreen(int pixel) {
		return ((pixel >> 8) & 0xFF);
	}

	private int getBlue(int pixel) {
		return (pixel & 0xFF);
	}

	private byte[] getBytesFromPixels(int[] pixels) {
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

	private String readFirstAndLastLineFromPicture(String pathToPicture) {
		String[] outputStrings = getCompleteMessageAsStringArrayFromPicture(pathToPicture);
		return outputStrings[0] + "\n" + outputStrings[outputStrings.length - 1];
	}

	private String[] getCompleteMessageAsStringArrayFromPicture(String pathToPicture) {
		BufferedImage picture = loadPicture(pathToPicture);
		int[] pixels = getPixels(picture);
		byte[] bytes = getBytesFromPixels(pixels);
		String completeString = getStringFromByteArray(bytes);
		int total = completeString.length();
		String[] outputStrings = new String[(int) (Math.floor(total / 100) + 2)];
		for (int i = 0; i * 100 < total + 100; i += 1) {
			outputStrings[i] = completeString.substring(i, i + 100);
			//DEBUG LINE System.out.println(i + "---" + total / 100 + "---" + total);
		}
		return outputStrings;
	}

	private String readCompleteMessageFromPicture(String pathToPicture) {
		String[] outputStrings = getCompleteMessageAsStringArrayFromPicture(pathToPicture);
		String completeOutput="";
		for(int i=0;i<outputStrings.length;i++){
			completeOutput+=outputStrings[i]+"\n";
		}
		return completeOutput;
	}

	private byte[] getByteArrayFromString(String s) {
		try {
			return s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getStringFromByteArray(byte[] array) {
		try {
			return new String(array, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				image.setRGB(x, y, pixels[x + y * width]);
			}
		}
		return image;
	}

	private void writeImg(BufferedImage image, String name) {
		try {
			ImageIO.write(image, "png", new File("./res/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
