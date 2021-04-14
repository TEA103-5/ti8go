package com.sale.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;



public interface SaleDAO_interface {
	public void insert(SaleVO saleVO);
	public Integer insertq(SaleVO saleVO);
	public void update(SaleVO saleVO);
	public void updatep(SaleVO saleVO);
	public void delete(Integer sale_id);
	public SaleVO findByPrimaryKey(Integer sale_id);
	public List<SaleVO> getAll();
	public Set<ProductVO> getProdsBySaleid(Integer sale_id); 
}
