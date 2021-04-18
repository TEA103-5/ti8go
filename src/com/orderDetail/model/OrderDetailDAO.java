//package com.orderDetail.model;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderDetailDAO implements OrderDetailDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL (Order_Detail_Count,PRODUCT_Id ,Order_Id) VALUES (?,?,?)";
//	private static final String UPDATE = "UPDATE ORDER_DETAIL set Order_Detail_Count=?,product_id=?,order_id=? where Order_Detail_Id = ?";
//	private static final String DELETE_ODI = "DELETE FROM ORDER_DETAIL where Order_Detail_Id = ?";
//	private static final String GET_ONE_STMT = "SELECT Order_Detail_Id,Order_Detail_Count,Product_id,order_id FROM ORDER_DETAIL where Order_Detail_Id = ? ";
//	private static final String GET_ALL_STMT = "SELECT Order_Detail_Id,Order_Detail_Count,product_id,order_id FROM ORDER_DETAIL";
//
//
//	public void insert(OrderDetailVO orderDetailVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, orderDetailVO.getOrder_detail_count());
//			pstmt.setInt(2, orderDetailVO.getProduct_id());
//			pstmt.setInt(3, orderDetailVO.getOrder_id());
//			System.out.println("成功");
//			pstmt.executeUpdate();
//			
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	public void update(OrderDetailVO orderDetailVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//
//			pstmt.setInt(1, orderDetailVO.getOrder_detail_count());
//			pstmt.setInt(2, orderDetailVO.getOrder_detail_id());
//			pstmt.setInt(3,orderDetailVO.getProduct_id());
//			pstmt.setInt(4,orderDetailVO.getOrder_id());
//			System.out.println("成功");
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	public void delete(Integer order_detail_VO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//
//			con.setAutoCommit(false);
//
//			pstmt = con.prepareStatement(DELETE_ODI);
//			pstmt.setInt(1, order_detail_VO);
//			pstmt.executeUpdate();
//			System.out.println("成功");
//
//			con.commit();
//			con.setAutoCommit(true);
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	public OrderDetailVO findByPrimaryKey(Integer Order_Detail_Id) {
//		OrderDetailVO orderDetailVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, Order_Detail_Id);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				orderDetailVO = new OrderDetailVO();
//				orderDetailVO.setOrder_detail_id(rs.getInt("Order_Detail_Id"));
//				orderDetailVO.setOrder_detail_count(rs.getInt("Order_detail_count"));
//				orderDetailVO.setProduct_id(rs.getInt("product_id"));
//				orderDetailVO.setOrder_id(rs.getInt("order_id"));
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		}
//
//		finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return orderDetailVO;
//
//	}
//
//	public List<OrderDetailVO> getAll() {
//		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
//		OrderDetailVO orderDetailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				orderDetailVO = new OrderDetailVO();
//				orderDetailVO.setOrder_detail_id(rs.getInt("Order_Detail_Id"));
//				orderDetailVO.setOrder_detail_count(rs.getInt("Order_detail_count"));
//				orderDetailVO.setProduct_id(rs.getInt("product_id"));
//				orderDetailVO.setOrder_id(rs.getInt("order_id"));
//				list.add(orderDetailVO);
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	public static void main(String[] args) {
//		OrderDetailDAO od = new OrderDetailDAO();
////		 新增
////		OrderDetailVO vo = new OrderDetailVO();
////		vo.setOrder_detail_count(1);
////		vo.setProduct_id(2);
////		vo.setOrder_id(3);
////		od.insert(vo);
//	
//		//修改
////		OrderDetailVO vo = new OrderDetailVO();
////		vo.setOrder_detail_count(5);
////		vo.setOrder_detail_id(2);
////		vo.setProduct_id(3);
////		vo.setOrder_id(4);
////		od.update(vo);
//		//刪除
////		od.delete(1);
//		
//		 // 查詢
////		OrderDetailVO deptVO3 = od.findByPrimaryKey(3);
////		System.out.print(deptVO3.getOrder_detail_id() + ",");
////		System.out.print(deptVO3.getOrder_detail_count()+ ",");
////		System.out.println("---------------------");
//		
//		
////		//查全部
////	    List<OrderDetailVO> list = od.getAll();
////		for (OrderDetailVO aDept : list) {
////		System.out.print(aDept.getOrder_detail_id() + ",");
////		System.out.print(aDept.getOrder_detail_count() + ",");
////		System.out.println();
//		
////		}
//	}	
//}
