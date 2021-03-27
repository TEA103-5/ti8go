package com.product_collect.model;

import java.util.List;

import com.place_collect.model.Place_collectVO;

public interface Product_collectDAO_interface {
	public void insert(Product_collectVO product_collectVO);
//	public void update(Product_collectVO product_collectVO);
	public void delete(Integer product_id , Integer users_id);
	public Product_collectVO findByPrimaryKey(Integer product_id , Integer users_id);
	public List<Product_collectVO> getAll();
	
	public List<Product_collectVO> getAllByPrimaryKey(Integer users_id);
}
