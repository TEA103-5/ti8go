package com.sale.model;

import java.sql.Timestamp;
import java.util.List;


public class SaleService {
	private SaleDAO_interface dao;
	public SaleService(){
		dao=new SaleDAO();
	}
	
	//新增,Byte[] sale_audit_pic
	public SaleVO addSale(String sale_email,String sale_pwd,String sale_name,String sale_phone,String sale_nickname,Float sale_rate) {
		SaleVO saleVO=new SaleVO();
		

		saleVO.setSale_email(sale_email);
		saleVO.setSale_pwd(sale_pwd);
		saleVO.setSale_name(sale_name);
//		saleVO.setSale_audit_pic(sale_audit_pic);
		saleVO.setSale_phone(sale_phone);
		saleVO.setSale_nickname(sale_nickname);
		saleVO.setSale_rate(sale_rate);

		
		dao.insert(saleVO);
		return saleVO;
	}
	//修改 沒放Byte[] sale_audit_pic
	public SaleVO updateSale(String sale_pwd,Integer sale_audit_status,String sale_name,Integer sale_status,String sale_phone,String sale_nickname,Float sale_rate,Integer sale_id) {
		SaleVO saleVO=new SaleVO();
		
		saleVO.setSale_pwd(sale_pwd);
		saleVO.setSale_audit_status(sale_audit_status);
//		saleVO.setSale_audit_pic(sale_audit_pic);
		saleVO.setSale_name(sale_name);
		saleVO.setSale_status(sale_status);
		saleVO.setSale_phone(sale_phone);
		saleVO.setSale_nickname(sale_nickname);
		saleVO.setSale_rate(sale_rate);
//		saleVO.setSale_time_create(sale_time_create);
		saleVO.setSale_id(sale_id);
		
		dao.update(saleVO);
		return saleVO;
	}
	//刪除
	public void deleteSale(Integer sale_id) {
		dao.delete(sale_id);
	}
	//查詢一筆
	public SaleVO getOneSale(Integer sale_id) {
		return dao.findByPrimaryKey(sale_id);
	}
	//查詢全部
	public List<SaleVO> getAll() {
		return dao.getAll();
	}
}
