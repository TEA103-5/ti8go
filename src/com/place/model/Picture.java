package com.place.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Picture {
	
	// 從本機路徑讀入圖片
	public InputStream getStreamFromLocal(String path) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		return fis ;
	}
	
	// 從資料庫將圖片寫入本機
	public void readPicture(InputStream is) throws IOException{
		FileOutputStream fos = new FileOutputStream("Output/4.png");
		int i ;
		while((i=is.read()) != -1 ) {
			fos.write(i);
		}
		fos.flush();
		fos.close();
	}
	
	
}
