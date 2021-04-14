package com.applicant.model;

import java.util.List;

public interface ApplicantDAO_interface {
	public void insert(ApplicantVO applicantVO);
    public void update(Integer activities_id, Integer users_id , Integer users_id1);
    public void delete(Integer activities_id, Integer users_id);
    public ApplicantVO findByPrimaryKey(Integer activities_id, Integer users_id);
    public List<ApplicantVO> getAll();
}
