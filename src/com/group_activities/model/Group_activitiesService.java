package com.group_activities.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Group_activitiesService {
	private Group_activitiesDAO_interface dao;

	public Group_activitiesService() {
		dao = new Group_activitiesDAO();
	}

	public Group_activitiesVO addEmp(Integer trip_id, Integer users_id,
			Integer activities_state, Date activities_deadline,
			Integer activities_max_users, Date activities_start, Date activities_end,
			String activities_name, String activities_description) {

		Group_activitiesVO group_activitiesVO = new Group_activitiesVO();

		group_activitiesVO.setTrip_id(trip_id);
		group_activitiesVO.setUsers_id(users_id);
		group_activitiesVO.setActivities_state(activities_state);
		group_activitiesVO.setActivities_deadline(activities_deadline);
		group_activitiesVO.setActivities_max_users(activities_max_users);
		group_activitiesVO.setActivities_start(activities_start);
		group_activitiesVO.setActivities_end(activities_end);
		group_activitiesVO.setActivities_name(activities_name);
		group_activitiesVO.setActivities_description(activities_description);
		dao.insert(group_activitiesVO);

		return group_activitiesVO;
	}

	public Group_activitiesVO updateEmp(Integer activities_id, Integer trip_id,
			Integer activities_state, Date activities_deadline,
			Integer activities_max_users, Integer activities_users_count, Date activities_start, Date activities_end,
			String activities_name, String activities_description) {

		Group_activitiesVO group_activitiesVO = new Group_activitiesVO();

		group_activitiesVO.setActivities_id(activities_id);
		group_activitiesVO.setTrip_id(trip_id);
		group_activitiesVO.setActivities_state(activities_state);
		group_activitiesVO.setActivities_deadline(activities_deadline);
		group_activitiesVO.setActivities_max_users(activities_max_users);
		group_activitiesVO.setActivities_users_count(activities_users_count);
		group_activitiesVO.setActivities_start(activities_start);
		group_activitiesVO.setActivities_end(activities_end);
		group_activitiesVO.setActivities_name(activities_name);
		group_activitiesVO.setActivities_description(activities_description);
		dao.update(group_activitiesVO);

		return group_activitiesVO;
	}

	public void deleteEmp(Integer activities_id) {
		dao.delete(activities_id);
	}

	public Group_activitiesVO getOneEmp(Integer activities_id) {
		return dao.findByPrimaryKey(activities_id);
	}

	public List<Group_activitiesVO> getAll() {
		return dao.getAll();
	}
}
