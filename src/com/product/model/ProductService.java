package com.product.model;

import java.sql.Timestamp;
import java.util.List;

import com.orderDetail.model.OrderDetailDAO;
import com.orderDetail.model.OrderDetailDAO_interface;
import com.orderDetail.model.OrderDetailService;

public class ProductService {
	private ProductDAO_Interface dao;
	//public OrderDetailDAO_interface dao2;
	//public OrderDetailService OrdSvc;
	
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
}
