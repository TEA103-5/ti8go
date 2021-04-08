package com.product.model;

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
	
}
