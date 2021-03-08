package com.place.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiTest {
	
	// 取得https://www.travel.taipei/open-api/swagger/ui/index#/Attractions/Attractions_All  的資料
	public static final String API_URL = "https://www.travel.taipei/open-api/zh-tw/Attractions/All?page=1";
	
	
	
	public static void main(String[] args) throws IOException, JSONException {
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
		String jsonstr = sb.toString();
		JSONObject jsonObj = new JSONObject(jsonstr);
		JSONArray dataArray = jsonObj.getJSONArray("data");
		JSONObject place = null ; // 用來放json陣列中取出的地點
		String name = null;  
		Integer open_status = null;
		String introduction = null;
		String open_time = null;
		String distric = null;
		String address = null;
		BigDecimal nlat = null;
		BigDecimal elong = null;
		String official_site = null;
		
		
		
		for(int i = 0 ; i < dataArray.length() ; i++) {
			
			place = dataArray.getJSONObject(i);
			name = place.getString("name");
			open_status = place.getInt("open_status");
			
			System.out.println(name + open_status);
		}
		
//		System.out.println(dataArray);
	}

}
