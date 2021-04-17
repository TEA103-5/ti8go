package com.users.model;

import java.util.*;

import com.applicant.model.ApplicantVO;
import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip.model.TripVO;

public interface UsersDAO_interface {
	public void insert(UsersVO usersVO)throws Exception;
	public UsersVO insert_new(UsersVO usersVO)throws Exception;
	public void update(UsersVO usersVO)throws Exception;
	public void delete(Integer users_id);
	public UsersVO findByPK(Integer users_id);
	public List<UsersVO> getAll();
	
	
	public Set<Group_activitiesVO> getActivitiesByUsers(Integer users_id);
    public Set<TeamVO> getTeamByUsers(Integer users_id);
    public Set<ApplicantVO> getApplicantByUsers(Integer users_id);

	public Set<TripVO> getTripByUsers(Integer users_id);
	
}
