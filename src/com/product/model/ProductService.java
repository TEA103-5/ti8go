package com.product.model;

<<<<<<< HEAD
import java.util.List;





public class ProductService {
	private ProductDAO_Interface dao;
	
	public ProductService(){
		dao=new ProductDAO();
	}
	
	
//	public ProductVO addProduct(String product_name,Integer product_status,String product_content,String product_description
//			,String product_categories,Integer product_price,Integer product_stock
//			,Integer sale_id) {
//		ProductVO productVO=new ProductVO();
//		
//
//		productVO.setProduct_name(product_name);
//		productVO.setProduct_status(product_status);
//		productVO.setProduct_content(product_content);
//		productVO.setProduct_description(product_description);
//		productVO.setProduct_categories(product_categories);
//		productVO.setProduct_price(product_price);
//		productVO.setProduct_stock(product_stock);
//		productVO.setSale_id(sale_id);
//
//
//		
//		dao.insert(productVO);
//		return productVO;
//	}
	public ProductVO updateP(Integer sale_id,byte[] sale_audit_pic) {
		ProductVO saleVO=new ProductVO();
		
		saleVO.setProduct_id(sale_id);
		saleVO.setProduct_pic(sale_audit_pic);
		
		dao.updatep(saleVO);
		return saleVO;
	}
	public ProductVO addProduct(ProductVO productVO) {
		dao.insert(productVO);
		return productVO;
	}
	
	
	public ProductVO updateProduct(String product_name,Integer product_status,String product_content,String product_description
			,String product_categories,Integer product_price,Integer product_stock
			,Integer product_id) {
		ProductVO productVO=new ProductVO();
		productVO.setProduct_name(product_name);
		productVO.setProduct_status(product_status);
		productVO.setProduct_content(product_content);
		productVO.setProduct_description(product_description);
		productVO.setProduct_categories(product_categories);
		productVO.setProduct_price(product_price);
		productVO.setProduct_stock(product_stock);
		productVO.setProduct_id(product_id);
		
		dao.update(productVO);
		return productVO;
	}
	//刪除
	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}
	//查詢一筆
	public ProductVO getOneProduct(Integer product_id) {
		return dao.findByPrimaryKey(product_id);
	}
	//查詢全部
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
=======
import java.sql.Timestamp;
import java.util.List;

import com.orderDetail.model.OrderDetailDAO;
import com.orderDetail.model.OrderDetailDAO_interface;
import com.orderDetail.model.OrderDetailService;

public class ProductService {
	private ProductDAO_Interface dao;
	//private OrderDetailDAO_interface dao2;
	//private OrderDetailService OrdSvc;
	
	public ProductService() {
		dao = new ProductJNDIDAO();
		//dao2 = new OrderDetailDAO();
		//OrdSvc = new OrderDetailService();
	}

	public ProductVO addProduct(ProductVO productVO) {
		ProductVO pro = new ProductVO();
		
		pro.setProduct_name(productVO.getProduct_name());
		pro.setProduct_status(productVO.getProduct_status());
		pro.setProduct_update_time(productVO.getProduct_update_time());
		pro.setProduct_content(productVO.getProduct_content());
		pro.setProduct_description(productVO.getProduct_description());
		pro.setProduct_categories(productVO.getProduct_categories());
		pro.setProduct_price(productVO.getProduct_price());
		pro.setProduct_stock(productVO.getProduct_stock());
		pro.setProduct_pic(productVO.getProduct_pic());
	//	pro.setSale_id(productVO.getSale_id());
		dao.insert(productVO);
		return pro;
	}

	public ProductVO updateProduct(ProductVO productVO) {
		ProductVO pro = new ProductVO();

		pro.setProduct_id(productVO.getProduct_id());
		pro.setProduct_name(productVO.getProduct_name());
		pro.setProduct_status(productVO.getProduct_status());
//		pro.setProduct_update_time(productVO.getProduct_update_time());
		pro.setProduct_content(productVO.getProduct_content());
		pro.setProduct_description(productVO.getProduct_description());
		pro.setProduct_categories(productVO.getProduct_categories());
		pro.setProduct_price(productVO.getProduct_price());
		pro.setProduct_stock(productVO.getProduct_stock());
		pro.setProduct_pic(productVO.getProduct_pic());
	//	pro.setSale_id(productVO.getSale_id());
		dao.update(pro);

		return pro;
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public ProductVO getOneProduct(Integer PRODUCT_Id) {
		return dao.findByPrimaryKey(PRODUCT_Id);
	}

	public void deleteProduct(Integer PRODUCT_Id) {
		dao.delete(PRODUCT_Id);
	}
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
}
