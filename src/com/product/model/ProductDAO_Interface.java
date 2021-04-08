package com.product.model;



import java.util.List;

<<<<<<< HEAD


public interface ProductDAO_Interface {
	 public void insert(ProductVO productVO);
     public void update(ProductVO productVO);
 	public void updatep(ProductVO productVO);
     public void delete(Integer product_id);
     public ProductVO findByPrimaryKey(Integer product_id);
=======
public interface ProductDAO_Interface {
	 public void insert(ProductVO productVO);
     public void update(ProductVO productVO);
     public void delete(Integer PRODUCT_Id);
     public ProductVO findByPrimaryKey(Integer PRODUCT_Id);
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
     public List<ProductVO> getAll();
}
