package com.orderDetail.model;

import java.util.List;

import java.util.Set;

import com.order.model.OrderVO;


public class OrderDetailService {
	private OrderDetailDAO_interface dao;

	public OrderDetailService() {
		dao = new OrderDetailJNDIDAO();
	}

	public OrderDetailVO addDetail(OrderDetailVO orderDetailVO) {
		OrderDetailVO ord = new OrderDetailVO();

		ord.setOrder_detail_count(orderDetailVO.getOrder_detail_count());
		ord.setOrder_id(orderDetailVO.getOrder_id());
		ord.setProduct_id(orderDetailVO.getProduct_id());

		ord.setOrder_detail_status(orderDetailVO.getOrder_detail_status());


		dao.insert(orderDetailVO);
		return ord;
	}

	public void updates(Integer id) {
		dao.updates(id);
	}
	public OrderDetailVO updateDetail(OrderDetailVO orderDetailVO) {
		OrderDetailVO ord = new OrderDetailVO();

		ord.setOrder_detail_count(orderDetailVO.getOrder_detail_count());
		ord.setOrder_id(orderDetailVO.getOrder_id());
		ord.setProduct_id(orderDetailVO.getProduct_id());

		ord.setOrder_detail_status(orderDetailVO.getOrder_detail_status());

		dao.update(orderDetailVO);
		return ord;
	}

	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}

	public OrderDetailVO getOneOrderDetail(Integer order_detail_id) {
		return dao.findByPrimaryKey(order_detail_id);
	}

	public void deleteOrderDetail(Integer order_detail_id) {
		dao.delete(order_detail_id);
	}

	public Set<OrderDetailVO> getOrderByOrderDetail(Integer Order_Id){
		return dao.getOrderByOrderDetail(Order_Id);
	}

}
