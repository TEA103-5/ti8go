package com.orderDetail.model;

import java.util.List;

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

		dao.insert(orderDetailVO);
		return ord;
	}

	public OrderDetailVO updateDetail(OrderDetailVO orderDetailVO) {
		OrderDetailVO ord = new OrderDetailVO();

		ord.setOrder_detail_count(orderDetailVO.getOrder_detail_count());
		ord.setOrder_id(orderDetailVO.getOrder_id());
		ord.setProduct_id(orderDetailVO.getProduct_id());

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

}
