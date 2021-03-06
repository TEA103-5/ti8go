package com.product_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Product_collectDAOJDBC implements Product_collectDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO product_collect (product_id , users_id) VALUES (? , ?)";
	private static final String GET_ALL_STMT = "SELECT product_collect_id , product_id , users_id , product_collect_time FROM product_collect order by product_collect_id";
	private static final String GET_ONE_STMT = "SELECT product_collect_id , product_id , users_id , product_collect_time FROM product_collect where product_collect_id = ?";
	private static final String DELETE = "DELETE FROM product_collect where product_collect_id=?";
	private static final String UPDATE = "UPDATE product_collect set product_id=? , users_id=? where product_collect_id=?";

	@Override
	public void insert(Product_collectVO product_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, product_collectVO.getProduct_id(), Types.INTEGER);
			pstmt.setObject(2, product_collectVO.getUsers_id(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Product_collectVO product_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setObject(1, product_collectVO.getProduct_id(), Types.INTEGER);
			pstmt.setObject(2, product_collectVO.getUsers_id(), Types.INTEGER);
			pstmt.setObject(3, product_collectVO.getProduct_collect_id(), Types.INTEGER);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer product_collect_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setObject(1, product_collect_id, Types.INTEGER);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Product_collectVO findByPrimaryKey(Integer product_collect_id) {
		Product_collectVO product_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, product_collect_id, Types.INTEGER);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product_collectVO = new Product_collectVO();
				
				product_collectVO.setProduct_collect_id(rs.getInt("product_collect_id"));
				product_collectVO.setProduct_id(rs.getInt("product_id"));
				product_collectVO.setUsers_id(rs.getInt("users_id"));
				product_collectVO.setProduct_collect_time(rs.getTimestamp("product_collect_time"));
				
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
		return product_collectVO;
	}

	@Override
	public List<Product_collectVO> getAll() {
		List<Product_collectVO> list = new ArrayList<Product_collectVO>();
		Product_collectVO product_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product_collectVO = new Product_collectVO();
				
				product_collectVO.setProduct_collect_id(rs.getInt("product_collect_id"));
				product_collectVO.setProduct_id(rs.getInt("product_id"));
				product_collectVO.setUsers_id(rs.getInt("users_id"));
				product_collectVO.setProduct_collect_time(rs.getTimestamp("product_collect_time"));
				
				list.add(product_collectVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	public static void main(String args[]) {
		Product_collectDAOJDBC dao = new Product_collectDAOJDBC();

//		// 新增
//		Product_collectVO product_collectVO1 = new Product_collectVO();
//		product_collectVO1.setProduct_id(1);
//		product_collectVO1.setUsers_id(1);
//		dao.insert(product_collectVO1);
//
//		// 修改
//		Product_collectVO product_collectVO2 = new Product_collectVO();
//		product_collectVO2.setProduct_id(1);
//		product_collectVO2.setUsers_id(2);
//		product_collectVO2.setProduct_collect_id(1);
//		dao.update(product_collectVO2);
//		
//		// 刪除
//		dao.delete(1);
		
		// 查詢一筆
		Product_collectVO product_collectVO3 = dao.findByPrimaryKey(2);
		
		System.out.print(product_collectVO3.getProduct_collect_id() + ",");
		System.out.print(product_collectVO3.getProduct_id() + ",");
		System.out.print(product_collectVO3.getUsers_id() + ",");
		System.out.println(product_collectVO3.getProduct_collect_time() );
		System.out.println("---------------------");
		
		// 查詢全部
		List<Product_collectVO> list = dao.getAll();
		
		for(Product_collectVO aProduct_collect : list) {
			
			System.out.print(product_collectVO3.getProduct_collect_id() + ",");
			System.out.print(product_collectVO3.getProduct_id() + ",");
			System.out.print(product_collectVO3.getUsers_id() + ",");
			System.out.print(product_collectVO3.getProduct_collect_time() );
			System.out.println();
		}
		
	}

}
