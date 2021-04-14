package com.group_activities.model;

import java.util.List;
import java.util.Set;

import com.applicant.model.ApplicantVO;

public interface Group_activitiesDAO_interface {
	public void insert(Group_activitiesVO group_activitiesVO);
    public void update(Group_activitiesVO  group_activitiesVO);
    public void delete(Integer	activities_id);
    public Group_activitiesVO findByPrimaryKey(Integer	activities_id);
    public List<Group_activitiesVO> getAll();
    public Set<ApplicantVO> getApplicantByTrip(Integer activities_id);
}
