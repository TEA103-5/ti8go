package com.product.model;



import java.util.List;

public interface ProductDAO_Interface {
	 public void insert(ProductVO productVO);
     public void update(ProductVO productVO);
     public void delete(Integer PRODUCT_Id);
     public ProductVO findByPrimaryKey(Integer PRODUCT_Id);
     public List<ProductVO> getAll();
}
