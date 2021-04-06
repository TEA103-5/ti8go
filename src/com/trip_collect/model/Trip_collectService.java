package com.trip_collect.model;

import java.util.List;


public class Trip_collectService {

	private Trip_collectDAO_interface dao;

	public Trip_collectService() {
		dao = new Trip_collectDAO();
	}

	public Trip_collectVO addTrip_collect(Integer trip_id, Integer users_id) {

		Trip_collectVO trip_collectVO = new Trip_collectVO();

		trip_collectVO.setTrip_id(trip_id);
		trip_collectVO.setUsers_id(users_id);
		dao.insert(trip_collectVO);

		return trip_collectVO;
	}

	public void deleteTrip_collect(Integer trip_id, Integer users_id) {
		dao.delete(trip_id, users_id);
	}

	public Trip_collectVO getOneTrip_collect(Integer trip_id, Integer users_id) {
		return dao.findByPrimaryKey(trip_id, users_id);
	}

	public List<Trip_collectVO> getAll() {
		return dao.getAll();
	}

	public List<Trip_collectVO> getAllByPrimaryKey(Integer users_id) {
		return dao.getAllByPrimaryKey(users_id);
	}

}
