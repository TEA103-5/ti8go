package com.order.model;

import java.util.List;



public class OrderService {
	public OrderDAO_Interface dao;

	public OrderService() {
		dao = new OrderJNDIDAO();
	}

	public OrderVO addOrder(OrderVO orderVO) {
		OrderVO ord = new OrderVO();
		ord.setOrder_status(orderVO.getOrder_status());
		ord.setOrder_rate(orderVO.getOrder_rate());
		ord.setCard_number(orderVO.getCard_number());
		// ord.setUser_id(orderVO.getUser_id());
		dao.insert(orderVO);
		return ord;
	}

	public OrderVO updateOrder(OrderVO orderVO) {

		OrderVO ord = new OrderVO();
		ord.setOrder_id(orderVO.getOrder_id());
		ord.setOrder_status(orderVO.getOrder_status());
		ord.setOrder_rate(orderVO.getOrder_rate());
		ord.setCard_number(orderVO.getCard_number());
		// ord.setUser_id(orderVO.getUser_id());
		dao.insert(orderVO);
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
}
