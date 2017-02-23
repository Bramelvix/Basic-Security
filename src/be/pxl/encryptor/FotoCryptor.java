package be.pxl.encryptor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

public class FotoCryptor {
	private int[] pixels;

	public void addMessageToPic(String pathToPhoto, String message) {
		BufferedImage pic = loadPhoto(pathToPhoto);
		int[] pixels = getPixels(pic);
		int w = pic.getWidth();
		int h = pic.getHeight();
		writeData(pixels, getByteArrayFromString(message));
		this.pixels = pixels;
		writeImg(getImageFromArray(this.pixels, w, h), "pic1");

	}

	private void writeData(int[] pixels, byte[] data) {
		int pixelcounter = 0;
		int kleurcounter = 0;
		for (int i = 0; i < data.length; i++) {
			for (int y = 0; y < 7; y++) {
				pixels[pixelcounter] = setIntOn(pixels[pixelcounter], kleurcounter, getBit(data[i], y));
				kleurcounter++;
				if (kleurcounter == 3) {
					kleurcounter %= 3;
					pixelcounter++;

				}
			}
		}

	}

	private int getBit(int nummer, int pos) {
		return ((nummer >> pos) & 1);
	}

	private int getRed(int pixel) {
		return ((pixel >> 16) & 0xFF);
	}

	private int getBlue(int pixel) {
		return ((pixel) & 0xFF);
	}

	private int getGreen(int pixel) {
		return ((pixel >> 8) & 0xFF);
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

	private String readMessageFromPic(String pathToPhoto) {
		BufferedImage pic = loadPhoto(pathToPhoto);
		int[] pixels = getPixels(pic);
		byte[] bytes = getBytesFromPixels(pixels);
		return getStringFromByteArray(bytes);
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

	public static void main(String[] args) {
		FotoCryptor cryp = new FotoCryptor();
		cryp.addMessageToPic("./res/pic.png", "lange zin branca kakak xddddd oo!!!");
		System.out.println(cryp.readMessageFromPic("./res/pic1.png"));
	}

	private int[] getPixels(BufferedImage pic) {
		int w = pic.getWidth();
		int h = pic.getHeight();
		int[] pix = new int[w * h];
		pic.getRGB(0, 0, w, h, pix, 0, w);
		return pix;
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

	private void writeImg(BufferedImage img, String name) {
		try {
			ImageIO.write(img, "png", new File("./res/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int setIntOn(int pixel, int kleur, int waarde) {
		int red = getRed(pixel);
		int green = getGreen(pixel);
		int blue = getBlue(pixel);
		if (kleur == 0) {
			red = setIntTo(red, waarde);
		} else {
			if (kleur == 1) {
				green = setIntTo(green, waarde);
			} else {
				if (kleur == 2) {
					blue = setIntTo(blue, waarde);
				}
			}
		}
		int newpixel = red;
		newpixel = (newpixel << 8) + green;
		newpixel = (newpixel << 8) + blue;
		return newpixel;
	}

	private int setIntTo(int original, int value) {
		if (value == 1) {
			return (original | (1 << 0));
		} else {
			return (original & ~(1 << 0));
		}

	}

	private BufferedImage loadPhoto(String path) {
		BufferedImage pic = null;
		if (path.endsWith(".png")) {
			try {
				pic = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pic;
	}

}
