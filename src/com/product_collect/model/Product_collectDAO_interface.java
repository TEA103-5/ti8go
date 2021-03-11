package com.product_collect.model;

import java.util.List;

public interface Product_collectDAO_interface {
	public void insert(Product_collectVO product_collectVO);
	public void update(Product_collectVO product_collectVO);
	public void delete(Integer product_id , Integer users_id);
	public Product_collectVO findByPrimaryKey(Integer users_id);
	public List<Product_collectVO> getAll();
}
