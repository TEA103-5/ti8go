package com.place.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Picture {
	
	// 從本機路徑讀入圖片(輸入流)
	public InputStream getStreamFromLocal(String path) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		return fis ;
	}
	
	// 從資料庫將圖片寫入本機(輸出流)
	public void readPicture(InputStream is) throws IOException{
		FileOutputStream fos = new FileOutputStream("Output/4.png");
		int i ;
		while((i=is.read()) != -1 ) {
			fos.write(i);
		}
		fos.flush();
		fos.close();
	}
	
	
	// 從本機路徑讀入圖片(byte陣列)
	public byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	// 從資料庫將圖片寫入本機(byte陣列)
	public void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("WebContent/images/10.jpg");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	
	
}
