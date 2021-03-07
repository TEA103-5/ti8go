package com.place.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlaceTest {
	public static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
			+ "location=25.05320,121.54447&" + "radius=500&" + "types=food&" + "name=牛排&" + "language=zh-TW&"
			+ "key=AIzaSyA-Pkxc4v0F1wdpbv0Uvo9fM20x7oGY87k";

	public static void main(String[] args) throws IOException, JSONException {
		URL url = new URL(GOOGLE_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
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

		// Parse JSON
		String jsonStr = sb.toString();
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray resultArray = jsonObj.getJSONArray("results");
		JSONObject restaurant = null;
		JSONObject restaurantLocation = null;
		JSONObject OpeningHour = null;
		Boolean IfOpenNow = null;

		String[] str = JSONObject.getNames(resultArray.getJSONObject(0));
		for (String str1 : str) {
			System.out.println(str1);
		}

//		// 要注意get類方法,如沒有對應的key會拋出例外
//		for(int i = 0 ; i < resultArray.length() ; i++) {
//			
//			restaurant = resultArray.getJSONObject(i);
//			if(restaurant.has("geometry") && restaurant.getJSONObject("geometry").has("location")) {
//				restaurantLocation = restaurant.getJSONObject("geometry").getJSONObject("location");
//			}
//			if(restaurant.has("name")) {
//				System.out.println("餐廳名稱 :" + restaurant.getString("name"));
//			}
//			if(restaurantLocation.has("lng")) {
//				System.out.println("經度為" + restaurantLocation.getDouble("lng"));				
//			}
//			if(restaurantLocation.has("lat")) {
//				System.out.println("緯度為" + restaurantLocation.getDouble("lat"));
//			}
//			if(restaurant.has("rating")) {
//				System.out.println("評分為" + restaurant.getDouble("rating"));
//			}
//			if(restaurant.has("opening_hours")) {
//				OpeningHour = restaurant.getJSONObject("opening_hours") ;
//				IfOpenNow = OpeningHour.getBoolean("open_now");
//				if(IfOpenNow == true) {
//					System.out.println("目前是否營業中: 是" );
//				}else {
//					System.out.println("目前是否營業中::否" );
//				}
//				
//			}
//			if(restaurant.has("vicinity")) {
//				System.out.println("地址:" + restaurant.getString("vicinity") );
//			}
//			System.out.println("-----------------------");
//			
//		}

	}

}
