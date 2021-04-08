package com.order.model;

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

public class OrderJNDIDAO implements OrderDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = "INSERT INTO ORDER1 (Order_Status,Card_Number,Order_Rate) VALUES (?, ? ,? )";
	private static final String UPDATE = "UPDATE ORDER1 set Order_Status=?,Card_Number=?,Order_Rate=? where Order_Id = ?";
	private static final String DELETE_OI = "DELETE FROM ORDER1 where Order_Id = ?";
	private static final String DELETE_ODI = "DELETE FROM ORDER_DETAIL where Order_Id = ?";
	private static final String GET_ONE_STMT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Rate,Order_Date FROM ORDER1 where Order_Id = ? ";
	private static final String GET_ALL_STMT = "SELECT Order_Id,Card_Number,Order_Status,Order_Rate,Order_Date FROM ORDER1";

	
	
	
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getOrder_status());
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getOrder_rate());
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

	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderVO.getOrder_status());
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getOrder_rate());
			pstmt.setInt(4, orderVO.getOrder_id());
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

	public void delete(Integer Order_Id) {
		int deleteCount_ODD = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			con.setAutoCommit(false);

			// 先刪訂單明細
			pstmt = con.prepareStatement(DELETE_ODI);
			pstmt.setInt(1, Order_Id);
			deleteCount_ODD = pstmt.executeUpdate();

			// 再刪訂單
			pstmt = con.prepareStatement(DELETE_OI);
			pstmt.setInt(1, Order_Id);
			deleteCount_ODD = pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單明細編號" + Order_Id + "時,共有訂單明細" + deleteCount_ODD + "張同時被刪除");

		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public OrderVO findByPrimaryKey(Integer Order_Id) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, Order_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
				orderVO.setCard_number(rs.getString("Card_number"));
				orderVO.setOrder_status(rs.getInt("Order_status"));
				orderVO.setOrder_rate(rs.getInt("Order_rate"));
				orderVO.setOrder_date(rs.getTimestamp("Order_date"));
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
		return orderVO;
	}

	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
				orderVO.setCard_number(rs.getString("Card_number"));
				orderVO.setOrder_status(rs.getInt("Order_status"));
				orderVO.setOrder_rate(rs.getInt("Order_rate"));
				orderVO.setOrder_date(rs.getTimestamp("Order_date"));
				list.add(orderVO);
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
		OrderDAO od = new OrderDAO();
		// 新增
		OrderVO vo = new OrderVO();
		vo.setCard_number("1234567891012345");
		vo.setOrder_status(2);
		vo.setOrder_rate(4);
		
		od.insert(vo);

		//修改
//		OrderVO vo = new OrderVO();
//		vo.setOrder_id(2);
//		vo.setCard_number("0987878787878787");
//		vo.setOrder_status(8);;
//		vo.setOrder_rate(5);
//		od.update(vo);
		
		//刪除
//		od.delete(2);
		
		 // 查詢
//		OrderVO deptVO3 = od.findByPrimaryKey(1);
//		System.out.print(deptVO3.getOrder_id() + ",");
//		System.out.print(deptVO3.getCard_number() + ",");
//		System.out.println(deptVO3.getOrder_status());
//		System.out.println(deptVO3.getOrder_rate());
//		System.out.println(deptVO3.getOrder_date());
//		System.out.println("---------------------");
//		
//		//查全部
//		List<OrderVO> list = od.getAll();
//		for (OrderVO aDept : list) {
//		System.out.print(aDept.getOrder_id() + ",");
//		System.out.print(aDept.getCard_number() + ",");
//		System.out.print(aDept.getOrder_status() + ","	);
//		System.out.println(aDept.getOrder_rate() + ",");
//		System.out.println(aDept.getOrder_date());
//		System.out.println();
////			
//	}
		
}
}

	
	
	
	
	
	
	
	
	
	

