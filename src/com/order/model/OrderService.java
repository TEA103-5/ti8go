package com.order.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;
import com.users.model.UsersDAO;
import com.users.model.UsersDAO_interface;
import com.users.model.UsersService;
import com.users.model.UsersVO;



public class OrderService {
	public OrderDAO_Interface dao;
	public UsersDAO_interface dao1;
	public UsersService usSvc;

	public OrderService() {
		dao = new OrderJNDIDAO();
		dao1 = new UsersDAO();
		usSvc = new UsersService();
	}

	public OrderVO addOrder(OrderVO orderVO) {
//		OrderVO ord = new OrderVO();
//		ord.setOrder_status(orderVO.getOrder_status());
//		ord.setCard_number(orderVO.getCard_number());
//		ord.setUsers_id(orderVO.getUsers_id());
		dao.insert(orderVO);
		
		return dao.findByTime(orderVO);
	}
	public OrderVO addOrder2(OrderVO orderVO,HashMap ovomap) {
//		OrderVO ord = new OrderVO();
//		ord.setOrder_status(orderVO.getOrder_status());
//		ord.setCard_number(orderVO.getCard_number());
//		ord.setUsers_id(orderVO.getUsers_id());
		dao.insert2(orderVO,ovomap);
		
		return dao.findByTime(orderVO);
	}

	public OrderVO updateOrder(OrderVO orderVO) {

		OrderVO ord = new OrderVO();
		ord.setOrder_id(orderVO.getOrder_id());
		ord.setOrder_status(orderVO.getOrder_status());
		ord.setCard_number(orderVO.getCard_number());
		ord.setUsers_id(orderVO.getUsers_id());
		dao.update(orderVO);
		return ord;

	}

	public List<OrderVO> getAll() {
		return dao.getAll();
	}

	public OrderVO getOneOrder(Integer Order_id) {
		return dao.findByPrimaryKey(Order_id);
	}

	public void deleteOrder(Integer Order_id) {
		dao.delete(Order_id);
	}
	public Set<OrderVO> getUsersByorder(Integer users_id){
		return dao.getUsersByorder(users_id);
	}
	public Set<OrderVO> getOrderByUsersId(Integer users_id){
		return dao.getOrderByUsersId(users_id);
	}
}
