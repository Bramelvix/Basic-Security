package be.pxl.encryptor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.*;

public final class WAV_Encryptor {
	private static final int START_INDEX = 3700000;

	private static int END_INDEX;

	public static void encryptWAV(String input_path, String message, String output_path) {
		byte[] data = convertWAVFileToByteArray(input_path);
		calcEnd_index(message);
		setHiddenMessageOnStartIndexTo(data, message);
		createWAVFile(data, output_path);

	}

	private static void calcEnd_index(String message) {
		END_INDEX = START_INDEX + message.length();
	}

	public static void printMessage(String path) {
		printByteValuesAsString(convertWAVFileToByteArray(path));
	}

	private static void setHiddenMessageOnStartIndexTo(byte[] data, String message) {
		byte[] characters = message.getBytes();
		int characterIndex = 0;
		for (int i = START_INDEX; i < END_INDEX; i++) {
			data[i] = characters[characterIndex++];
		}
	}

	private static void printMessageFromOutputWAVFile(String outputPath) {
		byte[] outputData = convertWAVFileToByteArray(outputPath);
		printByteValues(outputData, "OUTPUT-BYTES");
	}

	private static void createWAVFile(byte[] data, String path) {
		File newFile = new File(path);
		InputStream byteArray = new ByteArrayInputStream(data);

		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(byteArray);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			AudioSystem.write(ais, AudioFileFormat.Type.WAVE, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printByteValues(byte[] data, String textToShow) {
		System.out.println(textToShow);
		int karakters = 0;
		for (int i = START_INDEX; i < END_INDEX; i++) {
			System.out.print(data[i] + ";");
			karakters++;
			if (karakters == 10) {
				karakters = 0;
				System.out.println();
			}
		}
		System.out.println("\n");
	}

	private static void printByteValuesAsString(byte[] data) {
		System.out.println(new String(data, START_INDEX, END_INDEX - START_INDEX));
	}

	private static byte[] convertWAVFileToByteArray(String pathToFile) {
		try {
			Path path = Paths.get(pathToFile);
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
