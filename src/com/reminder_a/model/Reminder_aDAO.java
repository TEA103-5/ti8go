package com.reminder_a.model;

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



public class Reminder_aDAO implements Reminder_aDAO_interface{
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
			"INSERT INTO reminder_a(reminder_a_text,admins_id) VALUES (?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM reminder_a where reminder_a_id = ?";
	private static final String UPDATE = 
			"UPDATE reminder_a set reminder_a_status=? where reminder_a_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT reminder_a_id,reminder_a_time,reminder_a_text,reminder_a_status,admins_id FROM reminder_a where reminder_a_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT reminder_a_id,reminder_a_time,reminder_a_text,reminder_a_status,admins_id FROM reminder_a order by reminder_a_id";
		

	@Override
	public void insert(Reminder_aVO reminder_aVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, reminder_aVO.getReminder_a_text());
			pstmt.setInt(2, reminder_aVO.getAdmins_id());
			
			
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
	public void update(Reminder_aVO reminder_aVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reminder_aVO.getReminder_a_status());
			pstmt.setInt(2, reminder_aVO.getReminder_a_id());
			
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
	public void delete(Integer reminder_a_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, reminder_a_id);

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
	public Reminder_aVO findByPrimaryKey(Integer reminder_a_id) {
		Reminder_aVO reminder_aVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, reminder_a_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_aVO = new Reminder_aVO();
				reminder_aVO.setReminder_a_id(rs.getInt("reminder_a_id"));
				reminder_aVO.setReminder_a_time(rs.getTimestamp("reminder_a_time"));
				reminder_aVO.setReminder_a_status(rs.getInt("reminder_a_status"));
				reminder_aVO.setReminder_a_text(rs.getString("reminder_a_text"));
				reminder_aVO.setAdmins_id(rs.getInt("admins_id"));
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
		return reminder_aVO;
	}
	
	@Override
	public List<Reminder_aVO> getAll() {
		List<Reminder_aVO> list = new ArrayList<Reminder_aVO>();
		Reminder_aVO reminder_aVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_aVO = new Reminder_aVO();
				reminder_aVO.setReminder_a_id(rs.getInt("reminder_a_id"));
				reminder_aVO.setReminder_a_time(rs.getTimestamp("reminder_a_time"));
				reminder_aVO.setReminder_a_status(rs.getInt("reminder_a_status"));
				reminder_aVO.setReminder_a_text(rs.getString("reminder_a_text"));
				reminder_aVO.setAdmins_id(rs.getInt("admins_id"));
				list.add(reminder_aVO);
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
		
		return list;
	}
}
