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

	private static final String INPUT_PATH = "./res/Scatman.wav";
	private static final String OUTPUT_PATH = "./res/Hidden.wav";
	private static final String MESSAGE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int START_INDEX = 3700000;

	private static final int END_INDEX = START_INDEX + MESSAGE.length();

	public static void main(String[] args) {
		byte[] data = convertWAVFileToByteArray(INPUT_PATH);
		printByteValues(data, "INPUT");

		setHiddenMessageOnStartIndexTo(data, MESSAGE);
		printByteValues(data, "AFTER-CHANGE");
		createWAVFile(data);

		printMessageFromOutputWAVFile(OUTPUT_PATH);
		printByteValuesAsString(data);

		System.out.println("\nSize = " + data.length + "\n");
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

	private static void createWAVFile(byte[] data) {
		File newFile = new File(OUTPUT_PATH);
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
		System.out.println("OUTPUT-STRING");
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
