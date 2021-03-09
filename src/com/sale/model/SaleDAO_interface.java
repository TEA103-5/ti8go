package com.sale.model;

import java.util.List;



public interface SaleDAO_interface {
	public void insert(SaleVO saleVO);
	public void update(SaleVO saleVO);
	public void delete(Integer sale_id);
	public SaleVO findByPrimaryKey(Integer sale_id);
	public List<SaleVO> getAll();
}
