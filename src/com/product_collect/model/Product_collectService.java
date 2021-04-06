package com.product_collect.model;

import java.util.List;


public class Product_collectService {

	private Product_collectDAO_interface dao;

	public Product_collectService() {
		dao = new Product_collectDAO();
	}

	public Product_collectVO addProduct_collect(Integer product_id, Integer users_id) {

		Product_collectVO product_collectVO = new Product_collectVO();

		product_collectVO.setProduct_id(product_id);
		product_collectVO.setUsers_id(users_id);
		dao.insert(product_collectVO);

		return product_collectVO;
	}

	public void deleteProduct_collect(Integer product_id, Integer users_id) {
		dao.delete(product_id, users_id);
	}

	public Product_collectVO getOneProduct_collect(Integer product_id, Integer users_id) {
		return dao.findByPrimaryKey(product_id, users_id);
	}

	public List<Product_collectVO> getAll() {
		return dao.getAll();
	}

	public List<Product_collectVO> getAllByPrimaryKey(Integer users_id) {
		return dao.getAllByPrimaryKey(users_id);
	}

}
