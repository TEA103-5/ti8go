package com.place.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiToMySQL {

	//  適用:取得台北市景點api , https://www.travel.taipei/open-api/swagger/ui/index#/Attractions/Attractions_All
	//	public static String API_URL1 = "https://www.travel.taipei/open-api/zh-tw/Attractions/All?page=";

	// startPage: 從第幾頁開始取 , EndPage: 取到第幾頁結束 , API_URL: API的URL,不含最後頁數的參數
	// place_region: 此api是哪個縣市的
	public static void crawler(int startPage,int endPage, String place_region , String API_URL) throws IOException, JSONException {
		long start = System.currentTimeMillis();

		int k = 0;

		for (k = startPage; k <= endPage; k++) {

			PlaceDAOJDBC dao = new PlaceDAOJDBC();
			Picture picture = new Picture();
			PlaceVO placeVO1 = new PlaceVO();

			URL url = new URL(API_URL + k);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

//			HttpRequest request = HttpRequest.newBuilder()
//			HttpClient client = HttpClient.newBuilder();
			con.setRequestMethod("GET");
			con.setRequestProperty("accept", "application/json");
//			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
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

			if (jsonObj.getInt("total") == 0) {
				System.out.println("目前是第" + k + "頁,已無資料");
				break;
			}
			System.out.println("		============目前是第" + k + "頁============");
			
			
			JSONArray dataArray = jsonObj.getJSONArray("data");
			JSONObject place = null; // 用來放json陣列中取出的地點
			String name = null;
			String tel = null;
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
			String facebook = null;
			String url1 = null;
			JSONArray service = null;
			StringBuilder service_name = new StringBuilder();
			JSONArray category = null;

			for (int i = 0; i < dataArray.length(); i++) {

				place = dataArray.getJSONObject(i);

				name = place.getString("name");
				System.out.println("地點名稱為:" + name);
				placeVO1.setPlace_name(name);

				address = place.getString("address");
				System.out.println("地址:" + address);
				placeVO1.setPlace_address(address);

				elong = BigDecimal.valueOf(place.getDouble("elong"));
				System.out.println("經度為" + elong);
				placeVO1.setPlace_longitude(elong);

				nlat = BigDecimal.valueOf(place.getDouble("nlat"));
				System.out.println("緯度為:" + nlat);
				placeVO1.setPlace_latitude(nlat);

				tel = place.getString("tel");
				System.out.println("電話為:" + tel);
				placeVO1.setPlace_tel(tel);

				

				category = place.getJSONArray("category");
				placeVO1.setPlace_type(category.getJSONObject(0).getString("name"));
				// 這邊先只放分類的第一個
				for (int j = 0; j < category.length(); j++) {
					name = category.getJSONObject(j).getString("name");
					System.out.print(name + " ");
				}

				introduction = place.getString("introduction");
				introduction = introduction.replaceAll("\\r\\n", ""); // 換行元素如何消除??
				System.out.println("簡介:" + introduction);
				placeVO1.setPlace_index(introduction);

				images = place.getJSONArray("images");
				for (int j = 0; j < images.length(); j++) {

					if (j == 3) {
						break;
					}

					src = images.getJSONObject(j).getString("src");
					System.out.println(src);

					URL url_image = new URL(src);
					HttpURLConnection con_image = (HttpURLConnection) url_image.openConnection();

					con_image.setRequestMethod("GET");
					con_image.setRequestProperty("accept", "application/json");
					con_image.setRequestProperty("User-Agent", "rrrrr");

					InputStream is = con_image.getInputStream();
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();

					int nRead;
					byte[] data = new byte[16000];

					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}

					if (j == 0) {
						placeVO1.setPlace_pic1(buffer.toByteArray());
					}
					if (j == 1) {
						placeVO1.setPlace_pic2(buffer.toByteArray());
					}
					if (j == 2) {
						placeVO1.setPlace_pic3(buffer.toByteArray());
					}

				}
				
				// 爬回來的地點是有效的所以直接設為上架中
				placeVO1.setPlace_state(1);
				
				// 管理者上架的地點users_id不設值
//				placeVO1.setUsers_id(1);
				placeVO1.setBusiness_time(1);


//				placeVO1.setPlace_region(place_region); // 這邊是串接台北市的api
				
				distric = place.getString("distric");
				placeVO1.setPlace_region(distric); //place_region改放地區
				System.out.println("地區為:" + distric);

				open_time = place.getString("open_time"); // 營業時間
				System.out.println(open_time);

				open_status = place.getInt("open_status");
				System.out.println("營業狀態為:" + open_status);

				facebook = place.getString("facebook");
				System.out.println(facebook);

				url1 = place.getString("url");
				service = place.getJSONArray("service");
				for (int j = 0; j < service.length(); j++) {
					name = service.getJSONObject(j).getString("name");
					service_name.append(name + ",");
				}
				System.out.println("提供服務:" + service_name);
				service_name.delete(0, service_name.length());
				official_site = place.getString("official_site");
				System.out.println("官方網站為:" + official_site);

				System.out.println("");
				System.out.println("-------------------");

//				將該次VO insert進資料庫
				dao.insert(placeVO1);
			}
		}

		long end = System.currentTimeMillis();

		System.out.println("總共花費" + ((end - start) / 1000) + "秒");
//		System.out.println(dataArray);
	}

	public static void main(String[] args) throws IOException, JSONException {
		
	
				
				ApiToMySQL.crawler(1, 1, "台北市", "https://www.travel.taipei/open-api/zh-tw/Attractions/All?page=");

		
	}

}
