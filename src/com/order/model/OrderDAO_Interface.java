package com.order.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.emp.model.EmpVO;
import com.orderDetail.model.OrderDetailJNDIDAO;
import com.orderDetail.model.OrderDetailVO;
import com.product.model.ProductVO;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);

	public void update(OrderVO orderVO);

	public void delete(Integer order_Id);

	public OrderVO findByPrimaryKey(Integer order_Id);

	public List<OrderVO> getAll();
	
	public Set<OrderVO> getUsersByorder(Integer users_id); 
	
	
	 public Set<OrderVO>getOrderByUsersId(Integer u_id);
	 
	  public List<OrderVO> getAll(Map<String, String[]> map);
	 
	 public List insertWithDetail(OrderVO orderVO, List<OrderDetailVO> list);
	 
	 
}
