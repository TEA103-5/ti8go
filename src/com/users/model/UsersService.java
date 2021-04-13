package com.users.model;

import java.util.List;
import java.util.Set;

import com.applicant.model.ApplicantVO;
import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip.model.TripVO;
import com.users.model.*;

public class UsersService {

	private UsersDAO_interface dao;
	
	public UsersService() {
		dao = new UsersDAO();
	}
	
//	public UsersVO addusers(Integer users_id, String users_mail, String users_pwd, 
//			Integer users_status, String users_nickname, String users_name, 
//			Integer users_sex, String users_birthday, String users_id_number, 
//			Byte[] users_users_pic, String user_phone) throws Exception {
//		
//	UsersVO usersVO = new UsersVO();
//	
////	usersVO.setusers_id(users_id);
//	usersVO.setUsers_id(users_id);
//	usersVO.setUsers_mail(users_mail);
//	usersVO.setUsers_pwd(users_pwd);
//	usersVO.setUsers_status(users_status);
//	usersVO.setUsers_nickname(users_nickname);
//	usersVO.setUsers_name(users_name);
//	usersVO.setUsers_sex(users_sex);
//	usersVO.setUsers_birthday(users_birthday);
//	usersVO.setUsers_id_number(users_id_number);
//	usersVO.setUsers_users_pic(users_users_pic);
//	usersVO.setUsers_phone(user_phone);
//	
//	dao.insert(usersVO);
//	
//	return usersVO;
//	}
	public UsersVO addusers(UsersVO usersVO) throws Exception {
		
//		UsersVO usersVO = new UsersVO();
//		
////	usersVO.setusers_id(users_id);
//		usersVO.setUsers_id(users_id);
//		usersVO.setUsers_mail(users_mail);
//		usersVO.setUsers_pwd(users_pwd);
//		usersVO.setUsers_status(users_status);
//		usersVO.setUsers_nickname(users_nickname);
//		usersVO.setUsers_name(users_name);
//		usersVO.setUsers_sex(users_sex);
//		usersVO.setUsers_birthday(users_birthday);
//		usersVO.setUsers_id_number(users_id_number);
//		usersVO.setUsers_users_pic(users_users_pic);
//		usersVO.setUsers_phone(user_phone);
		
		dao.insert(usersVO);
		
		return usersVO;
	}
	
//	public UsersVO updateusers(Integer users_id, String users_mail, String users_pwd, 
//			Integer users_status, String users_nickname, String users_name, 
//			Integer users_sex, String users_birthday, String users_id_number, 
//			Byte[] users_users_pic, String user_phone) throws Exception{
//		
//		UsersVO usersVO = new UsersVO();
//		
//		usersVO.setUsers_id(users_id);
//		usersVO.setUsers_mail(users_mail);
//		usersVO.setUsers_pwd(users_pwd);
//		usersVO.setUsers_status(users_status);
//		usersVO.setUsers_nickname(users_nickname);
//		usersVO.setUsers_name(users_name);
//		usersVO.setUsers_sex(users_sex);
//		usersVO.setUsers_birthday(users_birthday);
//		usersVO.setUsers_id_number(users_id_number);
//		usersVO.setUsers_users_pic(users_users_pic);
//		usersVO.setUsers_phone(user_phone);
//		
//		dao.update(usersVO);
//		
//		return usersVO;	
//	}	
	public UsersVO updateusers(UsersVO usersVO) throws Exception{
		
//		UsersVO usersVO = new UsersVO();
//		
//		usersVO.setUsers_id(users_id);
//		usersVO.setUsers_mail(users_mail);
//		usersVO.setUsers_pwd(users_pwd);
//		usersVO.setUsers_status(users_status);
//		usersVO.setUsers_nickname(users_nickname);
//		usersVO.setUsers_name(users_name);
//		usersVO.setUsers_sex(users_sex);
//		usersVO.setUsers_birthday(users_birthday);
//		usersVO.setUsers_id_number(users_id_number);
//		usersVO.setUsers_users_pic(users_users_pic);
//		usersVO.setUsers_phone(user_phone);
//		
		dao.update(usersVO);
		
		return usersVO;	
	}	
	
	public void deleteusers(Integer users_id) {
		dao.delete(users_id);
	}
	
	public UsersVO getOneusers(Integer users_id) {
		return dao.findByPK(users_id);
	}
	
	public List<UsersVO> getAll() {
		return dao.getAll();
	}
	
	
	public Set<Group_activitiesVO> getActivitiesByUsers(Integer users_id){
		return dao.getActivitiesByUsers(users_id);
	}
    public Set<TeamVO> getTeamByUsers(Integer users_id){
    	return dao.getTeamByUsers(users_id);
    }
    public Set<ApplicantVO> getApplicantByUsers(Integer users_id){
    	return dao.getApplicantByUsers(users_id);
    }
    public Set<TripVO> getTripByUsers(Integer users_id){
    	return dao.getTripByUsers(users_id);
    }

}
