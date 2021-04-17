package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.orderDetail.model.OrderDetailVO;
import com.product.model.ProductVO;


public class OrderJNDIDAO implements OrderDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();

			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_detail = "INSERT INTO ORDER_DETAIL (Order_Detail_Count,PRODUCT_Id,Order_Id) VALUES (?,?,?)";
	private static final String INSERT_STMT2 = "insert into order1 (order_date,card_number,users_id,order_address) values (  ?,?, ?, ?)";
	
	
	private static final String INSERT_STMT = "INSERT INTO ORDER1 (Order_Status,Card_Number,users_id) VALUES ( ? , ? , ? )";
	private static final String UPDATE = "UPDATE ORDER1 set Order_Status=?,Card_Number=?,users_id=? where Order_Id = ?";
	private static final String DELETE_OI = "DELETE FROM ORDER1 where Order_Id = ?";
	private static final String DELETE_ODI = "DELETE FROM ORDER_DETAIL where Order_Id = ?";
	private static final String GET_ONE_STMT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,users_id FROM ORDER1 where Order_Id = ? ";
	private static final String GET_ALL_STMT = "SELECT Order_Id,Card_Number,Order_Status,Order_Date,users_id FROM ORDER1";

	private static final String GET_USERS_ByORDER_STMT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date, FROM order1 where users_id = ? order by users_id";
	
	
	private static final String GOBU = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,users_id FROM order1 where users_id = ? order by order_id";
	private static final String GOBT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,users_id FROM order1 where users_id = ? and order_date=?";
	
	public OrderVO findByTime(OrderVO ovo) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GOBT);

			pstmt.setInt(1, ovo.getUsers_id());
			pstmt.setTimestamp(2, ovo.getOrder_date());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
				orderVO.setCard_number(rs.getString("Card_number"));
				orderVO.setOrder_status(rs.getInt("Order_status"));
				orderVO.setOrder_date(rs.getTimestamp("Order_date"));
				orderVO.setUsers_id(rs.getInt("Users_id"));
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
	
	
	public Set<OrderVO> getOrderByUsersId(Integer u_id){
		Set<OrderVO> set=new LinkedHashSet<OrderVO>();
		OrderVO orderVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GOBU);
			pstmt.setInt(1, u_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
				orderVO.setCard_number(rs.getString("Card_Number"));
				orderVO.setOrder_status(rs.getInt("Order_Status"));
				orderVO.setOrder_date(rs.getTimestamp("Order_Date"));
				orderVO.setUsers_id(rs.getInt("users_id"));
				set.add(orderVO); // Store the row in the vector
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
		
		return set;
	}
	public Set<OrderVO> getUsersByorder(Integer users_id) {

		Set<OrderVO> set = new LinkedHashSet<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_USERS_ByORDER_STMT);
			pstmt.setInt(1, users_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
				orderVO.setCard_number(rs.getString("Card_Number"));
				orderVO.setOrder_status(rs.getInt("Order_Status"));
				orderVO.setOrder_date(rs.getTimestamp("Order_Date"));
				orderVO.setUsers_id(rs.getInt("users_id"));
				set.add(orderVO); // Store the row in the vector
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}
	
	
	public void insert2(OrderVO orderVO,HashMap ovomap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT2);
			
			pstmt.setTimestamp(1, orderVO.getOrder_date());
			
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getUsers_id());
			pstmt.setString(4, orderVO.getOrder_address());
			
			//autocommit false
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(GOBT);
			pstmt.setInt(1, orderVO.getUsers_id());
			pstmt.setTimestamp(2, orderVO.getOrder_date());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("Order_Id"));
			}
			rs.close();
			
			OrderDetailVO ovo;
			pstmt = con.prepareStatement(INSERT_detail);
			for(int i=0;i<ovomap.size();i++) {
			ovo=(OrderDetailVO) ovomap.get(i);
			pstmt.setInt(1, ovo.getOrder_detail_count());
			pstmt.setInt(2, ovo.getProduct_id());
			pstmt.setInt(3, orderVO.getOrder_id());
			pstmt.executeUpdate();
			}
			
			con.commit();
			con.setAutoCommit(true);
			

			
			
		} catch (SQLException e) {
			
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);
			
			pstmt.setTimestamp(1, orderVO.getOrder_date());
			
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getUsers_id());
			
			
			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setInt(1, orderVO.getOrder_status());
//			pstmt.setString(2, orderVO.getCard_number());
//			pstmt.setInt(3, orderVO.getUsers_id());
		//	System.out.println("成功");

			
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
			pstmt.setInt(3, orderVO.getUsers_id());
			pstmt.setInt(4, orderVO.getOrder_id());

			pstmt.executeUpdate();
			System.out.println("成功");

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

				orderVO.setOrder_date(rs.getTimestamp("Order_date"));
				orderVO.setUsers_id(rs.getInt("Users_id"));

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
				orderVO.setOrder_date(rs.getTimestamp("Order_date"));
				orderVO.setUsers_id(rs.getInt("Users_id"));

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
}

	
	
	
	
	
	
	
	
	
	

