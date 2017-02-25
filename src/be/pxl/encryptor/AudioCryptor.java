package be.pxl.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioCryptor {

	private static byte[] getByteArrayFromAudioFile(String path) {
		File file = new File(path);
		byte[] data = new byte[(int) file.length()];
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private void writeAudioFile(byte[] data, String path) {
		try {
			FileOutputStream out = new FileOutputStream(new File(path + ".wav"));
			out.write(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
