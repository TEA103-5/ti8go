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
	public static final String API_URL = "https://www.travel.taipei/open-api/zh-tw/Attractions/All?page=2";
	
	
	
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
		JSONArray images = null;
		String src = null;
		String facebook = null ;
		String url1 = null;
		JSONArray service = null;
		StringBuilder service_name = new StringBuilder();
		JSONArray category = null;
		
		
		
		
		
		
		for(int i = 0 ; i < dataArray.length() ; i++) {
			
			place = dataArray.getJSONObject(i);
			name = place.getString("name");
			System.out.println("地點名稱為:" + name);
//			distric = place.getString("distric");
//			System.out.println("地區為:" + distric);
//			nlat = BigDecimal.valueOf(place.getDouble("nlat"));
//			System.out.println("北緯為:" + nlat);
//			open_time = place.getString("open_time");   // 營業時間
//			System.out.println(open_time);
//			open_status = place.getInt("open_status");
//			System.out.println("營業狀態為:" + open_status);
//			introduction = place.getString("introduction");
//			introduction.replaceAll("\\r\\n", "");    // 換行元素如何消除??
//			System.out.println("簡介:" + introduction);
//			images = place.getJSONArray("images");
//			for(int j = 0 ; j < images.length() ; j++) {
//				src = images.getJSONObject(j).getString("src");
//				System.out.println(src);
//			}
//			address = place.getString("address");
//			System.out.println("地址:" + address);
//			elong = BigDecimal.valueOf(place.getDouble("elong"));
//			facebook = place.getString("facebook");
//			System.out.println(facebook);
//			url1 = place.getString("url");
//			service = place.getJSONArray("service");
//			for(int j = 0 ; j < service.length() ; j++) {
//				name = service.getJSONObject(j).getString("name");
//				service_name.append(name + ",");
//			}
//			System.out.println("提供服務:" + service_name);
//			service_name.delete(0, service_name.length());
//			official_site = place.getString("official_site");
//			System.out.println("官方網站為:" + official_site);
			category = place.getJSONArray("category");
			for(int j = 0 ; j < category.length() ; j++) {
				name = category.getJSONObject(j).getString("name");
				System.out.print(name + " ");
			}
			System.out.println("");
			System.out.println("-------------------");
			
		}
		
//		System.out.println(dataArray);
	}

}
