package com.orderDetail.model;

import java.util.List;
import java.util.Set;




public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);

	public void update(OrderDetailVO orderDetailVO);
	public void updates(Integer id) ;
	public void delete(Integer Order_Detail_Id);

	public OrderDetailVO findByPrimaryKey(Integer Order_Detail_Id);

	public List<OrderDetailVO> getAll();

	
	public Set<OrderDetailVO> getOrderByOrderDetail(Integer Order_Id); 

}
