package com.orderDetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);

	public void update(OrderDetailVO orderDetailVO);

	public void delete(Integer Order_Detail_Id);

	public OrderDetailVO findByPrimaryKey(Integer Order_Detail_Id);

	public List<OrderDetailVO> getAll();
}
