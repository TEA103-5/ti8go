package com.admins.model;

import java.util.List;

public class AdminsService {
	
	private AdminsDAO_interface dao;
	
	public AdminsService() {
		dao = new AdminsDAO();
	}

	public AdminsVO addadmins( String admins_email, String admins_name,
			String admins_password, Integer admins_sex, String admins_authority, 
			String admins_position) throws Exception {
	
		AdminsVO adminsVO = new AdminsVO();
		
		adminsVO.setAdmins_email(admins_email);
		adminsVO.setAdmins_name(admins_name);
		adminsVO.setAdmins_password(admins_password);
		adminsVO.setAdmins_sex(admins_sex);
		adminsVO.setAdmins_authority(admins_authority);
		adminsVO.setAdmins_position(admins_position);
		
		dao.insert(adminsVO);
		
		
	return adminsVO;	
	}
	
	public AdminsVO updateadmins( Integer admins_id, String admins_email, String admins_name,
			String admins_password, Integer admins_sex, String admins_authority, 
			String admins_position) throws Exception {
		
		AdminsVO adminsVO = new AdminsVO();
		
		adminsVO.setAdmins_email(admins_email);
		adminsVO.setAdmins_name(admins_name);
		adminsVO.setAdmins_password(admins_password);
		adminsVO.setAdmins_sex(admins_sex);
		adminsVO.setAdmins_authority(admins_authority);
		adminsVO.setAdmins_position(admins_position);
		adminsVO.setAdmins_id(admins_id);
		
		dao.update(adminsVO);
	
	return adminsVO;	
	}
	
	public void deleteadmins(Integer admins_id) {
		dao.delete(admins_id);
	}
	
	public AdminsVO getOneadmins(Integer admins_id) {
		return dao.findByPK(admins_id);
	}
	
	public List<AdminsVO> getAll() {
		return dao.getAll();
	}
}
