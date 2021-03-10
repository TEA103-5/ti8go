package com.notec.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Picture {
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];  // available源頭的資料量(類似length長度)
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("Output/2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}
