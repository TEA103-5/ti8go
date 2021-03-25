package com.team.model;

import java.util.List;


public class TeamService {
	private TeamDAO_interface dao;

	public TeamService() {
		dao = new TeamDAO();
	}

	public TeamVO addEmp(Integer trip_id, Integer users_id) {

		TeamVO teamVO = new TeamVO();

		teamVO.setTrip_id(trip_id);
		teamVO.setUsers_id(users_id);
		
		dao.insert(teamVO);

		return teamVO;
	}

	public TeamVO updateEmp(Integer trip_id, Integer users_id , Integer users_id1) {

		TeamVO teamVO = new TeamVO();

		teamVO.setTrip_id(trip_id);
		teamVO.setUsers_id(users_id1);
		
		dao.update(trip_id,users_id ,users_id1);

		return teamVO;
	}

	public void deleteEmp(Integer trip_id, Integer users_id) {
		dao.delete(trip_id, users_id);
	}

	public TeamVO getOneEmp(Integer trip_id, Integer users_id) {
		return dao.findByPrimaryKey(trip_id ,  users_id);
	}

	public List<TeamVO> getAll() {
		return dao.getAll();
	}
}
