package com.reminder_u.model;

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


public class Reminder_uDAO implements Reminder_uDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//ip的等之後確定怎抓再加!!
	private static final String INSERT_STMT = 
			"INSERT INTO reminder_u(reminder_u_text,users_id) VALUES (?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM reminder_u where reminder_u_id = ?";
	private static final String UPDATE = 
			"UPDATE reminder_u set reminder_u_status=? where reminder_u_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT reminder_u_id,reminder_u_time,reminder_u_text,reminder_u_status,users_id FROM reminder_u where reminder_u_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT reminder_u_id,reminder_u_time,reminder_u_text,reminder_u_status,users_id FROM reminder_u order by reminder_u_id";
		

	@Override
	public void insert(Reminder_uVO reminder_uVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, reminder_uVO.getReminder_u_text());
			pstmt.setInt(2, reminder_uVO.getUsers_id());
			
			
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
	public void update(Reminder_uVO reminder_uVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reminder_uVO.getReminder_u_status());
			pstmt.setInt(2, reminder_uVO.getReminder_u_id());
			
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
	public void delete(Integer reminder_u_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, reminder_u_id);

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
	public Reminder_uVO findByPrimaryKey(Integer reminder_u_id) {
		Reminder_uVO reminder_uVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, reminder_u_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_uVO = new Reminder_uVO();
				reminder_uVO.setReminder_u_id(rs.getInt("reminder_u_id"));
				reminder_uVO.setReminder_u_time(rs.getTimestamp("reminder_u_time"));
				reminder_uVO.setReminder_u_status(rs.getInt("reminder_u_status"));
				reminder_uVO.setReminder_u_text(rs.getString("reminder_u_text"));
				reminder_uVO.setUsers_id(rs.getInt("users_id"));
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
		return reminder_uVO;
	}
	
	
	@Override
	public List<Reminder_uVO> getAll() {
		List<Reminder_uVO> list = new ArrayList<Reminder_uVO>();
		Reminder_uVO reminder_uVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_uVO = new Reminder_uVO();
				reminder_uVO.setReminder_u_id(rs.getInt("reminder_u_id"));
				reminder_uVO.setReminder_u_time(rs.getTimestamp("reminder_u_time"));
				reminder_uVO.setReminder_u_status(rs.getInt("reminder_u_status"));
				reminder_uVO.setReminder_u_text(rs.getString("reminder_u_text"));
				reminder_uVO.setUsers_id(rs.getInt("users_id"));
			}
				list.add(reminder_uVO);
			
			
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
