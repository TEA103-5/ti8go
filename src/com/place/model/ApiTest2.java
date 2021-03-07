package com.place.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiTest2 {
	
	// 取得此網站json: https://data.gov.tw/dataset/7780
	// 網站資料太多,要想一下如何處理
	public static final String API_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json";
	
	
	
	public static void main(String[] args) throws IOException {
		URL url = new URL(API_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

//		HttpRequest request = HttpRequest.newBuilder()
//		HttpClient client = HttpClient.newBuilder();
		con.setRequestMethod("GET");
		con.setRequestProperty("accept", "application/json");
//		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		con.setRequestProperty("User-Agent", "rrrrr");
		StringBuilder sb = new StringBuilder();
		if (con.getResponseCode() == 200) {
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String data;
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}

			br.close();
			isr.close();
			is.close();
		} else {
			System.out.println("No data...");
		}
		System.out.println(sb);
	}

}
