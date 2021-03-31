package com.login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;
import com.sale.model.SaleVO;

public class LoginDAO implements Login_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String createView= 
			"CREATE OR Replace VIEW view_name1 AS select distinct users_mail  account_number,'users' as 'type',users_pwd as pwd,users_id as id from users union select admins_email account_number,'admins' as 'type',admins_password as pwd,admins_id as id from admins union select sale_email account_number, 'sale' as 'type', sale_pwd as pwd,sale_id as id from sale order by type";
	
			private static final String checkType= 
					"SELECT type,pwd,id FROM view_name1 WHERE account_number like (?)";
	//確認帳號的同時順便確認密碼
	@Override
	public SaleVO loginType(String account,String pass) {
		SaleVO uVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();

	
			pstmt = con.prepareStatement(createView);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(checkType);
			pstmt.setString(1, account);
			
		//	pstmt.setInt(1, trip_id);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				uVO=new SaleVO();
				uVO.setSale_name(rs.getString("type"));
				uVO.setSale_pwd(rs.getString("pwd"));
				uVO.setSale_id(rs.getInt("id"));

			}
			
			if(uVO!=null&&!pass.equals(uVO.getSale_pwd())) {
				uVO=null;
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return uVO;
	}
	//下面這個只是確認帳號有無存在
	public String idcheck(String account) {
		String type=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			
			
			pstmt = con.prepareStatement(createView);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(checkType);
			pstmt.setString(1, account);
			
			//	pstmt.setInt(1, trip_id);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				type=rs.getString("type");
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return type;
	}
}
