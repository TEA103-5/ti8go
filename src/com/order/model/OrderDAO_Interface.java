package com.order.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


import com.product.model.ProductVO;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void insert2(OrderVO orderVO,HashMap rs);

	public void update(OrderVO orderVO);

	public void delete(Integer order_Id);

	public OrderVO findByPrimaryKey(Integer order_Id);

	public List<OrderVO> getAll();
	
	public Set<OrderVO> getUsersByorder(Integer users_id); 
	
	public OrderVO findByTime(OrderVO ovo);
	
	 public Set<OrderVO>getOrderByUsersId(Integer u_id);
}
