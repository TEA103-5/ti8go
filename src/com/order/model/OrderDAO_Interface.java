package com.order.model;

import java.util.List;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);

	public void update(OrderVO orderVO);

	public void delete(Integer order_Id);

	public OrderVO findByPrimaryKey(Integer order_Id);

	public List<OrderVO> getAll();
}
