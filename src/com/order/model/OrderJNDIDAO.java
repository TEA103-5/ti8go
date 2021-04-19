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
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;
import com.mysql.jdbc.Statement;
import com.orderDetail.model.OrderDetailJNDIDAO;
import com.orderDetail.model.OrderDetailVO;
import com.product.model.ProductVO;

import Order_CompositeQuery.order_CompositeQuery;


public class OrderJNDIDAO implements OrderDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ORDER1 (Order_Status,Card_Number,users_id,order_address) VALUES ( ? , ? , ?, ? )";
	private static final String UPDATE = "UPDATE ORDER1 set Order_Status=?,Card_Number=?,users_id=?,order_address=? where Order_Id = ?";
	private static final String DELETE_OI = "DELETE FROM ORDER1 where Order_Id = ?";
	private static final String DELETE_ODI = "DELETE FROM ORDER_DETAIL where Order_Id = ?";
	private static final String GET_ONE_STMT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,users_id,order_address FROM ORDER1 where Order_Id = ? ";
	private static final String GET_ALL_STMT = "SELECT Order_Id,Card_Number,Order_Status,Order_Date,users_id,order_address FROM ORDER1";

	private static final String GET_USERS_ByORDER_STMT = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,order_address FROM order1 where users_id = ? order by users_id";

	private static final String GOBU = "SELECT Order_Id ,Card_Number,Order_Status,Order_Date,users_id,order_address FROM order1 where users_id = ? order by order_id";
	private static final String INSERT_OrderSTMT = "INSERT INTO ORDER1 (Order_Status,Card_Number,users_id,order_address) VALUES ( ? , ? , ?, ? )";

	public Set<OrderVO> getOrderByUsersId(Integer u_id) {
		Set<OrderVO> set = new LinkedHashSet<OrderVO>();
		OrderVO orderVO = null;

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
				orderVO.setOrder_address(rs.getString("order_address"));
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
				orderVO.setOrder_address(rs.getString("order_address"));
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

	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getOrder_status());
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getUsers_id());
			pstmt.setString(4, orderVO.getOrder_address());

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

	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderVO.getOrder_status());
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getUsers_id());
			pstmt.setString(4, orderVO.getOrder_address());
			pstmt.setInt(5, orderVO.getOrder_id());

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
				orderVO.setOrder_address(rs.getString("order_address"));
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
				orderVO.setOrder_address(rs.getString("order_address"));

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

	public List insertWithDetail(OrderVO orderVO, List<OrderDetailVO> list) {
		List list1 = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增部門
			String cols[] = { "Order_Id" };
			pstmt = con.prepareStatement(INSERT_OrderSTMT, cols);
			pstmt.setInt(1, orderVO.getOrder_status());
			pstmt.setString(2, orderVO.getCard_number());
			pstmt.setInt(3, orderVO.getUsers_id());
			pstmt.setString(4, orderVO.getOrder_address());
			list1.add(orderVO);
			pstmt.executeUpdate();
			// 掘取對應的自增主鍵值
			String next_deptno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_deptno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_deptno + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			OrderDetailJNDIDAO dao = new OrderDetailJNDIDAO();
			System.out.println("list.size()-B=" + list.size());
			for (OrderDetailVO detail : list) {
				List<OrderDetailVO> list2 = new ArrayList<OrderDetailVO>();
				list2.add(detail);
				detail.setOrder_id(new Integer(next_deptno));
				dao.insert2(detail, con);
				
				list1.addAll(list2);
				System.out.println("先Order成功");
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增訂單編號" + next_deptno + "時,共有明細" + list.size() + "張同時被新增");

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-by-Order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list1;

	}
	 public List<OrderVO> getAll(Map<String, String[]> map) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from ORDER1 "
		          + order_CompositeQuery.get_WhereCondition(map)
		          + "order by Order_Id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by OrderDNDI) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				empVO = new OrderVO();
				empVO.setOrder_id(rs.getInt("Order_Id"));
				empVO.setCard_number(rs.getString("Card_number"));
				empVO.setOrder_status(rs.getInt("Order_status"));
				empVO.setOrder_date(rs.getTimestamp("Order_date"));
				empVO.setUsers_id(rs.getInt("users_id"));
				empVO.setOrder_address(rs.getString("order_address"));
				list.add(empVO); // Store the row in the List
				System.out.println("JNDI"+list);
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
		return list;
	}
}
