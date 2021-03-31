package com.product_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_collectDAO implements Product_collectDAO_interface  {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product_collect (product_id , users_id) VALUES (? , ?)";
	private static final String GET_ALL_STMT = "SELECT product_id , users_id , product_collect_time FROM product_collect order by users_id";
	private static final String GET_ONE_STMT = "SELECT product_id , users_id , product_collect_time FROM product_collect where product_id = ? and users_id = ?";
	private static final String DELETE = "DELETE FROM product_collect where product_id=? and users_id = ?";
	// 收藏不會用更新的方式去變更 , 所以更新指令只是練習用的,條件寫死在update方法裡
//	private static final String UPDATE = "UPDATE product_collect set product_id=? , users_id=? where product_id=? and users_id = ?";

	private static final String GET_ALL_BY_ID_STMT = "SELECT product_id , users_id , product_collect_time FROM product_collect where users_id = ?";
	
	@Override
	public void insert(Product_collectVO product_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, product_collectVO.getProduct_id(), Types.INTEGER);
			pstmt.setObject(2, product_collectVO.getUsers_id(), Types.INTEGER);

			pstmt.executeUpdate();

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

//	@Override
//	public void update(Product_collectVO product_collectVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setObject(1, product_collectVO.getProduct_id(), Types.INTEGER);
//			pstmt.setObject(2, product_collectVO.getUsers_id(), Types.INTEGER);
//			pstmt.setObject(3, 1, Types.INTEGER);
//			pstmt.setObject(4, 1, Types.INTEGER);
//			
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	@Override
	public void delete(Integer product_id , Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setObject(1, product_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			pstmt.executeUpdate();
			
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
	public Product_collectVO findByPrimaryKey(Integer product_id , Integer users_id) {
		Product_collectVO product_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, product_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product_collectVO = new Product_collectVO();
				
				product_collectVO.setProduct_id(rs.getInt("product_id"));
				product_collectVO.setUsers_id(rs.getInt("users_id"));
				product_collectVO.setProduct_collect_time(rs.getTimestamp("product_collect_time"));
				
				
			}
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
			con = ds.getConnection();			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product_collectVO = new Product_collectVO();
				
				product_collectVO.setProduct_id(rs.getInt("product_id"));
				product_collectVO.setUsers_id(rs.getInt("users_id"));
				product_collectVO.setProduct_collect_time(rs.getTimestamp("product_collect_time"));
				
				list.add(product_collectVO);
				
			}
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
	
	
	@Override
	public List<Product_collectVO> getAllByPrimaryKey(Integer users_id) {
		List<Product_collectVO> list = new ArrayList<Product_collectVO>();
		Product_collectVO product_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_ID_STMT);
			pstmt.setObject(1, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_collectVO = new Product_collectVO();
				
				product_collectVO.setProduct_id(rs.getInt("product_id"));
				product_collectVO.setUsers_id(rs.getInt("users_id"));
				product_collectVO.setProduct_collect_time(rs.getTimestamp("product_collect_time"));
				
				list.add(product_collectVO);

			}
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
	
	
	
	
	
	
	
}
