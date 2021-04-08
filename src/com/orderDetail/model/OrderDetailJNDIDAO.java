package com.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.OrderDAO;
import com.order.model.OrderDAO_Interface;
import com.order.model.OrderVO;

public class OrderDetailJNDIDAO implements OrderDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL (Order_Detail_Count,PRODUCT_Id ,Order_Id) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE ORDER_DETAIL set Order_Detail_Count=?,product_id=?,order_id=? where Order_Detail_Id = ?";
	private static final String DELETE_ODI = "DELETE FROM ORDER_DETAIL where Order_Detail_Id = ?";
	private static final String GET_ONE_STMT = "SELECT Order_Detail_Id,Order_Detail_Count,Product_id,order_id FROM ORDER_DETAIL where Order_Detail_Id = ? ";
	private static final String GET_ALL_STMT = "SELECT Order_Detail_Id,Order_Detail_Count,product_id,order_id FROM ORDER_DETAIL";

	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailVO.getOrder_detail_count());
			pstmt.setInt(2, orderDetailVO.getProduct_id());
			pstmt.setInt(3, orderDetailVO.getOrder_id());
			System.out.println("成功");
			pstmt.executeUpdate();
			
			
		
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderDetailVO.getOrder_detail_count());
			pstmt.setInt(2, orderDetailVO.getOrder_detail_id());
			pstmt.setInt(3,orderDetailVO.getProduct_id());
			pstmt.setInt(4,orderDetailVO.getOrder_id());
			System.out.println("成功");

			pstmt.executeUpdate();

		
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	public void delete(Integer order_detail_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_ODI);
			pstmt.setInt(1, order_detail_VO);
			pstmt.executeUpdate();
			System.out.println("成功");

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public OrderDetailVO findByPrimaryKey(Integer Order_Detail_Id) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, Order_Detail_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_detail_id(rs.getInt("Order_Detail_Id"));
				orderDetailVO.setOrder_detail_count(rs.getInt("Order_detail_count"));
				orderDetailVO.setProduct_id(rs.getInt("product_id"));
				orderDetailVO.setOrder_id(rs.getInt("order_id"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		finally {
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
		return orderDetailVO;

	}

	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_detail_id(rs.getInt("Order_Detail_Id"));
				orderDetailVO.setOrder_detail_count(rs.getInt("Order_detail_count"));
				orderDetailVO.setProduct_id(rs.getInt("product_id"));
				orderDetailVO.setOrder_id(rs.getInt("order_id"));
				list.add(orderDetailVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	public static void main(String[] args) {
		OrderDetailJNDIDAO od = new OrderDetailJNDIDAO();
//		 新增
		OrderDetailVO vo = new OrderDetailVO();
		vo.setOrder_detail_count(1);
		vo.setProduct_id(2);
		vo.setOrder_id(3);
		od.insert(vo);
	
		//修改
//		OrderDetailVO vo = new OrderDetailVO();
//		vo.setOrder_detail_id(1);
//		vo.setOrder_detail_count(5);
//		od.update(vo);
	
		//刪除
//		od.delete(1);
		
		 // 查詢
//		OrderDetailVO deptVO3 = od.findByPrimaryKey(3);
//		System.out.print(deptVO3.getOrder_detail_id() + ",");
//		System.out.print(deptVO3.getOrder_detail_count()+ ",");
//		System.out.println("---------------------");
		
		
//		//查全部
//	    List<OrderDetailVO> list = od.getAll();
//		for (OrderDetailVO aDept : list) {
//		System.out.print(aDept.getOrder_detail_id() + ",");
//		System.out.print(aDept.getOrder_detail_count() + ",");
//		System.out.println();
		
//		}
	}	
}
