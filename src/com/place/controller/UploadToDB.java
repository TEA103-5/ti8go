package com.place.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

public class UploadToDB {

	public static Map<String, byte[]> getByteArrayFromPart(Collection<Part> parts) throws IOException {
		
		Map<String, byte[]> byteArrayMap = new HashMap<String, byte[]>();
		
		for (Part part : parts) {
			String filename = getFileNameFromPart(part);
			if (filename != null && part.getContentType() != null) {
				String name = part.getName();
				String ContentType = part.getContentType();
				long size = part.getSize();
				
				if( !ContentType.contains("image")) {
					continue; // 如果該part不是圖片則跳過此次處理
				}

				System.out.println("name: " + name);
				System.out.println("filename: " + filename);
				System.out.println("ContentType: " + ContentType);
				System.out.println("size: " + size);


				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				System.out.println("buffer length: " + buf.length);
				
				byteArrayMap.put(name, buf);

			}
		}
		return byteArrayMap;
	}

// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public static String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
