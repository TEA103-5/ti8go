package com.applicant.model;

import java.sql.Timestamp;
import java.util.List;

public class ApplicantService {
	private ApplicantDAO_interface dao;

	public ApplicantService() {
		dao = new ApplicantDAO();
	}

	public ApplicantVO addEmp(Integer activities_id, Integer users_id) {

		ApplicantVO applicantVO = new ApplicantVO();

		applicantVO.setActivities_id(activities_id);
		applicantVO.setUsers_id(users_id);
		
		dao.insert(applicantVO);

		return applicantVO;
	}

	public ApplicantVO updateEmp(Integer activities_id, Integer users_id, Integer users_id1) {

		ApplicantVO applicantVO = new ApplicantVO();

		applicantVO.setActivities_id(activities_id);
		applicantVO.setUsers_id(users_id1);
		
		dao.update(activities_id, users_id, users_id1);

		return applicantVO;
	}

	public void deleteEmp(Integer activities_id, Integer users_id) {
		dao.delete(activities_id, users_id);
	}

	public ApplicantVO getOneEmp(Integer activities_id, Integer users_id) {
		return dao.findByPrimaryKey(activities_id ,  users_id);
	}

	public List<ApplicantVO> getAll() {
		return dao.getAll();
	}
}
