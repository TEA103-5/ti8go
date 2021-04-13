package com.trip_detail.model;


import java.sql.Time;
import java.util.List;

public class Trip_detailService {

	private Trip_detailDAO_interface dao;

	public Trip_detailService() {
		dao = new Trip_detailDAO();
	}

//	Integer trip_id, Integer trip_day, Integer trip_sort,
//	String trip_detail_type, String trip_content, Time trip_start_time, Time trip_end_time,
//	String trip_remarks, Integer trip_cost
	public Trip_detailVO addEmp(Trip_detailVO trip_detailVO) {

//		Trip_detailVO trip_detailVO = new Trip_detailVO();
//
//		 trip_detailVO.setTrip_id(trip_id);
//		 trip_detailVO.setTrip_day(trip_day);
//		 trip_detailVO.setTrip_sort(trip_sort);
//		 trip_detailVO.setTrip_detail_type(trip_detail_type);
//		 trip_detailVO.setTrip_content(trip_content);
////		 trip_detailVO.setPlace_id(place_id);
//		 trip_detailVO.setTrip_start_time(trip_start_time);
//		 trip_detailVO.setTrip_end_time(trip_end_time);
//		 trip_detailVO.setTrip_remarks(trip_remarks);
//		 trip_detailVO.setTrip_cost(trip_cost);

		dao.insert(trip_detailVO);

		return trip_detailVO;
	}

	public Trip_detailVO updateEmp(Integer trip_detail_id,Integer trip_id, Integer trip_day, Integer trip_sort,
			String trip_detail_type, String trip_content, Time trip_start_time, Time trip_end_time,
			String trip_remarks, Integer trip_cost) {

		Trip_detailVO trip_detailVO = new Trip_detailVO();
		
		trip_detailVO.setTrip_detail_id(trip_detail_id);
		 trip_detailVO.setTrip_id(trip_id);
		 trip_detailVO.setTrip_day(trip_day);
		 trip_detailVO.setTrip_sort(trip_sort);
		 trip_detailVO.setTrip_detail_type(trip_detail_type);
		 trip_detailVO.setTrip_content(trip_content);
//		 trip_detailVO.setPlace_id(place_id);
		 trip_detailVO.setTrip_start_time(trip_start_time);
		 trip_detailVO.setTrip_end_time(trip_end_time);
		 trip_detailVO.setTrip_remarks(trip_remarks);
		 trip_detailVO.setTrip_cost(trip_cost);
		
		 dao.update(trip_detailVO);

		return trip_detailVO;
	}

	public void deleteEmp(Integer trip_detail_id) {
		dao.delete(trip_detail_id);
	}

	public Trip_detailVO getOneEmp(Integer trip_detail_id) {
		return dao.findByPrimaryKey(trip_detail_id);
	}

	public List<Trip_detailVO> getAll() {
		return dao.getAll();
	}
}
