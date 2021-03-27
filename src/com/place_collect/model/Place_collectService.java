package com.place_collect.model;

import java.util.List;

import com.emp.model.EmpDAO_interface;

public class Place_collectService {

	private Place_collectDAO_interface dao;

	public Place_collectService() {
		dao = new Place_collectDAO();
	}

	public Place_collectVO addPlace_collect(Integer place_id, Integer users_id) {

		Place_collectVO place_collectVO = new Place_collectVO();

		place_collectVO.setPlace_id(place_id);
		place_collectVO.setUsers_id(users_id);
		dao.insert(place_collectVO);

		return place_collectVO;
	}
	
	public void deletePlace_collect(Integer place_id, Integer users_id) {
		dao.delete(place_id, users_id);
	}
	
	public Place_collectVO getOnePlace_collect(Integer place_id, Integer users_id) {
		return dao.findByPrimaryKey(place_id , users_id);
	}
	
	public List<Place_collectVO> getAll(){
		return dao.getAll();
	}
	
	public List<Place_collectVO> getAllByPrimaryKey(Integer users_id){
		return dao.getAllByPrimaryKey(users_id);
	}
	
}
