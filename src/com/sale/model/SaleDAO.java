package com.sale.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class SaleDAO implements SaleDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"insert into sale(sale_email,sale_pwd,sale_name,sale_audit_pic,sale_phone,sale_nickname,sale_rate) values (?,?,?,?,?,?,?)";
	//7個問號
	private static final String DELETE = 
			"DELETE FROM sale where sale_id = ?";
	private static final String UPDATE = 
			"UPDATE sale set sale_pwd=?,sale_audit_status=?,sale_audit_pic=?,sale_name=?,sale_status=?,sale_phone=?,sale_nickname=?,sale_rate=? where sale_id = ?";
	//8+1個問號
	private static final String GET_ONE_STMT = 
			"SELECT sale_id,sale_email,sale_pwd,sale_name,sale_audit_status,sale_audit_pic,sale_status,sale_phone,sale_nickname,sale_rate,sale_time_create FROM sale where sale_id = ?";
	//11個欄位 
	private static final String GET_ALL_STMT = 
			"SELECT sale_id,sale_email,sale_pwd,sale_name,sale_audit_status,sale_audit_pic,sale_status,sale_phone,sale_nickname,sale_rate,sale_time_create FROM sale order by sale_id";
		

	@Override
	public void insert(SaleVO saleVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			//sale_email,sale_pwd,sale_name,sale_audit_pic,sale_phone,sale_nickname,sale_rate
			pstmt.setString(1, saleVO.getSale_email());
			pstmt.setString(2, saleVO.getSale_pwd());
			pstmt.setString(3, saleVO.getSale_name());
//			pstmt.setBytes(4, saleVO.getSale_audit_pic());
			pstmt.setString(5, saleVO.getSale_phone());
			pstmt.setString(6, saleVO.getSale_nickname());
			pstmt.setFloat(7, saleVO.getSale_rate());
			
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(SaleVO saleVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, saleVO.getSale_pwd());
			pstmt.setInt(2, saleVO.getSale_audit_status());
//			pstmt.setBytes(3, saleVO.getSale_audit_pic());
			pstmt.setString(4, saleVO.getSale_name());
			pstmt.setInt(5, saleVO.getSale_status());
			pstmt.setString(6, saleVO.getSale_phone());
			pstmt.setString(7, saleVO.getSale_nickname());
			pstmt.setFloat(8, saleVO.getSale_rate());
//			pstmt.setTimestamp(9, saleVO.getSale_time_create());
			pstmt.setInt(10, saleVO.getSale_id());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer sale_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, sale_id);

			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public SaleVO findByPrimaryKey(Integer sale_id) {
		SaleVO saleVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, sale_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				saleVO = new SaleVO();
				saleVO.setSale_id(rs.getInt("sale_id"));
				saleVO.setSale_email(rs.getString("sale_email"));
				saleVO.setSale_pwd(rs.getString("sale_pwd"));
				saleVO.setSale_name(rs.getString("sale_name"));
				saleVO.setSale_audit_status(rs.getInt("sale_audit_status"));
//				saleVO.setSale_audit_pic(rs.getBytes("sale_audit_pic"));				
				saleVO.setSale_status(rs.getInt("sale_status"));
				saleVO.setSale_phone(rs.getString("sale_phone"));
				saleVO.setSale_nickname(rs.getString("sale_nickname"));
				saleVO.setSale_rate(rs.getFloat("sale_rate"));
				saleVO.setSale_time_create(rs.getTimestamp("sale_time_create"));
				
			
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return saleVO;
	}
	
	@Override
	public List<SaleVO> getAll() {
		List<SaleVO> list = new ArrayList<SaleVO>();
		SaleVO saleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				saleVO = new SaleVO();
				saleVO.setSale_id(rs.getInt("sale_id"));
				saleVO.setSale_email(rs.getString("sale_email"));
				saleVO.setSale_pwd(rs.getString("sale_pwd"));
				saleVO.setSale_name(rs.getString("sale_name"));
				saleVO.setSale_audit_status(rs.getInt("sale_audit_status"));
//				saleVO.setSale_audit_pic(rs.getBytes("sale_audit_pic"));				
				saleVO.setSale_status(rs.getInt("sale_status"));
				saleVO.setSale_phone(rs.getString("sale_phone"));
				saleVO.setSale_nickname(rs.getString("sale_nickname"));
				saleVO.setSale_rate(rs.getFloat("sale_rate"));
				saleVO.setSale_time_create(rs.getTimestamp("sale_time_create"));
				list.add(saleVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
}
