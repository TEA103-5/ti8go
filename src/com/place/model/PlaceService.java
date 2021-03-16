package com.place.model;

import java.math.BigDecimal;
import java.util.List;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDAO_interface;
import com.emp.model.EmpService;

public class PlaceService {

	private PlaceDAO_interface dao;

	public PlaceService() {
		dao = new PlaceDAO();
	}

	public PlaceVO addPlace(String place_name,String place_address,BigDecimal place_longitude,BigDecimal place_latitude,
			String place_tel,String place_region,String place_type,String place_index,byte[] place_pic1,
			byte[] place_pic2,byte[] place_pic3,Integer place_state,Integer users_id,Integer business_time) {
		
		PlaceVO placeVO = new PlaceVO();
		
		placeVO.setPlace_name(place_name);
		placeVO.setPlace_address(place_address);
		placeVO.setPlace_longitude(place_longitude);
		placeVO.setPlace_latitude(place_latitude);
		placeVO.setPlace_tel(place_tel);
		placeVO.setPlace_region(place_region);
		placeVO.setPlace_type(place_type);
		placeVO.setPlace_index(place_index);

		placeVO.setPlace_pic1( place_pic1);
		placeVO.setPlace_pic2( place_pic2);
		placeVO.setPlace_pic3( place_pic3);
		placeVO.setPlace_state(place_state);
		placeVO.setUsers_id(users_id);
		placeVO.setBusiness_time(business_time);
		
		
		return placeVO;
	}
	
	public PlaceVO updatePlace(Integer place_id , String place_name,String place_address,BigDecimal place_longitude,BigDecimal place_latitude,
			String place_tel,String place_region,String place_type,String place_index,byte[] place_pic1,
			byte[] place_pic2,byte[] place_pic3,Integer place_state,Integer users_id,Integer business_time) {
		
		PlaceVO placeVO = new PlaceVO();
		
		placeVO.setPlace_id(place_id);
		placeVO.setPlace_name(place_name);
		placeVO.setPlace_address(place_address);
		placeVO.setPlace_longitude(place_longitude);
		placeVO.setPlace_latitude(place_latitude);
		placeVO.setPlace_tel(place_tel);
		placeVO.setPlace_region(place_region);
		placeVO.setPlace_type(place_type);
		placeVO.setPlace_index(place_index);

		placeVO.setPlace_pic1( place_pic1);
		placeVO.setPlace_pic2( place_pic2);
		placeVO.setPlace_pic3( place_pic3);
		placeVO.setPlace_state(place_state);
		placeVO.setUsers_id(users_id);
		placeVO.setBusiness_time(business_time);
		
		
		return placeVO;
	}
	
	public void deletePlace(Integer place_id) {
		dao.delete(place_id);
	}
	
	public PlaceVO getOnePlace(Integer place_id) {
		return dao.findByPrimaryKey(place_id);
	}
	
	public List<PlaceVO> getAll(){
		return dao.getAll();
	}
	
	
	
}
