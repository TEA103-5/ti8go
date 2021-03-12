package com.sale.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SaleJDBCDAO implements SaleDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	//,sale_audit_pic
	private static final String INSERT_STMT = 
			"insert into sale(sale_email,sale_pwd,sale_name,sale_phone,sale_nickname,sale_rate,sale_audit_pic) values (?,?,?,?,?,?,?)";
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
	public void insert(SaleVO saleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//sale_email,sale_pwd,sale_name,sale_audit_pic,sale_phone,sale_nickname,sale_rate
			pstmt.setString(1, saleVO.getSale_email());
			pstmt.setString(2, saleVO.getSale_pwd());
			pstmt.setString(3, saleVO.getSale_name());
			pstmt.setString(4, saleVO.getSale_phone());
			pstmt.setString(5, saleVO.getSale_nickname());
			pstmt.setFloat(6,  saleVO.getSale_rate());		
			pstmt.setBytes(7,  saleVO.getSale_audit_pic());
//			pstmt.setBytes(7,pic);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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

	};
	@Override
	public void updatep(SaleVO saleVO) {
		
	}
	@Override
	public void update(SaleVO saleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, saleVO.getSale_pwd());
			pstmt.setInt(2, saleVO.getSale_audit_status());
			pstmt.setBytes(3, saleVO.getSale_audit_pic());
			pstmt.setString(4, saleVO.getSale_name());
			pstmt.setInt(5, saleVO.getSale_status());
			pstmt.setString(6, saleVO.getSale_phone());
			pstmt.setString(7, saleVO.getSale_nickname());
			pstmt.setFloat(8, saleVO.getSale_rate());
//			pstmt.setTimestamp(9, saleVO.getSale_time_create());
			pstmt.setInt(9, saleVO.getSale_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	};
	@Override
	public void delete(Integer sale_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sale_id);


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	};
	@Override
	public SaleVO findByPrimaryKey(Integer sale_id) {
		SaleVO saleVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				saleVO.setSale_audit_pic(rs.getBytes("sale_audit_pic"));				
				saleVO.setSale_status(rs.getInt("sale_status"));
				saleVO.setSale_phone(rs.getString("sale_phone"));
				saleVO.setSale_nickname(rs.getString("sale_nickname"));
				saleVO.setSale_rate(rs.getFloat("sale_rate"));
				saleVO.setSale_time_create(rs.getTimestamp("sale_time_create"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	};
	@Override
	public List<SaleVO> getAll(){
		List<SaleVO> list = new ArrayList<SaleVO>();
		SaleVO saleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				saleVO.setSale_audit_pic(rs.getBytes("sale_audit_pic"));				
				saleVO.setSale_status(rs.getInt("sale_status"));
				saleVO.setSale_phone(rs.getString("sale_phone"));
				saleVO.setSale_nickname(rs.getString("sale_nickname"));
				saleVO.setSale_rate(rs.getFloat("sale_rate"));
				saleVO.setSale_time_create(rs.getTimestamp("sale_time_create"));
				list.add(saleVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	};
	
	public static void main(String[] args) {

		SaleJDBCDAO dao = new SaleJDBCDAO();

		// 新增
		SaleVO empVO1 = new SaleVO();
		empVO1.setSale_email("123@YYYY.com");
		empVO1.setSale_pwd("123456");
		empVO1.setSale_name("中11老師");
		empVO1.setSale_nickname("1大中大");
		empVO1.setSale_phone("0999987541");
		empVO1.setSale_rate(5.0f);
		try {
			byte[] pic = getPictureByteArray();
			empVO1.setSale_audit_pic(pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.insert(empVO1);

		// 刪除
//		dao.delete(3);

//		// 查詢1筆
		SaleVO empVO3 = dao.findByPrimaryKey(2);
		System.out.print(empVO3.getSale_id() + ",");
		System.out.print(empVO3.getSale_nickname() + ",");
		System.out.print(empVO3.getSale_time_create() + ",");
		System.out.print(empVO3.getSale_email()+ ",");
		System.out.print(empVO3.getSale_audit_pic()+ ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
		System.out.println();
		System.out.println("--------------");
//
		
		// 修改
//		SaleVO empVO3 = new SaleVO();
		empVO3.setSale_pwd("22345");
		empVO3.setSale_audit_status(1);
//		empVO3.setSale_id(2);
//		empVO2.setSa
		try {
			byte[] pic = getPictureByteArray("items/a2.jpg");
			empVO3.setSale_audit_pic(pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.update(empVO3);
//
//		// 查詢全部
		List<SaleVO> list = dao.getAll();
		for (SaleVO aEmp : list) {
//			SaleVO empVO4 = dao.findByPrimaryKey(1);
			System.out.print( aEmp.getSale_id() + ",");
			System.out.print( aEmp.getSale_name() + ",");
			System.out.print( aEmp.getSale_nickname() + ",");
			System.out.print( aEmp.getSale_time_create() + ",");
			System.out.print( aEmp.getSale_email()+ ",");
			System.out.print( aEmp.getSale_audit_pic()+ ",");
			System.out.println("----");
			System.out.println();
		}
	}
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	public static byte[] getPictureByteArray() throws IOException {
		FileInputStream fis = new FileInputStream("items/a1.jpg");
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
