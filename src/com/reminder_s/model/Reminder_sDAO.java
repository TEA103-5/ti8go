package com.reminder_s.model;

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


public class Reminder_sDAO implements Reminder_sDAO_interface{
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
			"INSERT INTO reminder_s(reminder_s_text,sale_id) VALUES (?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM reminder_s where reminder_s_id = ?";
	private static final String UPDATE = 
			"UPDATE reminder_s set reminder_s_status=? where reminder_s_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT reminder_s_id,reminder_s_time,reminder_s_text,reminder_s_status,sale_id FROM reminder_s where reminder_s_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT  reminder_s_id,reminder_s_time,reminder_s_text,reminder_s_status,sale_id FROM reminder_s order by reminder_s_id";
		

	@Override
	public void insert(Reminder_sVO reminder_sVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reminder_sVO.getReminder_s_text());
			pstmt.setInt(2, reminder_sVO.getSale_id());
			
			
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
	public void update(Reminder_sVO reminder_sVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reminder_sVO.getReminder_s_status());
			pstmt.setInt(2, reminder_sVO.getReminder_s_id());
			
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
	public void delete(Integer reminder_s_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, reminder_s_id);

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
	public Reminder_sVO findByPrimaryKey(Integer reminder_s_id) {
		Reminder_sVO reminder_sVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, reminder_s_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_sVO = new Reminder_sVO();
				reminder_sVO.setReminder_s_id(rs.getInt("reminder_s_id"));
				reminder_sVO.setReminder_s_time(rs.getTimestamp("reminder_s_time"));
				reminder_sVO.setReminder_s_status(rs.getInt("reminder_s_status"));
				reminder_sVO.setReminder_s_text(rs.getString("reminder_s_text"));
				reminder_sVO.setSale_id(rs.getInt("sale_id"));
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
		return reminder_sVO;
	}
	
	@Override
	public List<Reminder_sVO> getAll() {
		List<Reminder_sVO> list = new ArrayList<Reminder_sVO>();
		Reminder_sVO reminder_sVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reminder_sVO = new Reminder_sVO();
				reminder_sVO.setReminder_s_id(rs.getInt("reminder_s_id"));
				reminder_sVO.setReminder_s_time(rs.getTimestamp("reminder_s_time"));
				reminder_sVO.setReminder_s_status(rs.getInt("reminder_s_status"));
				reminder_sVO.setReminder_s_text(rs.getString("reminder_s_text"));
				reminder_sVO.setSale_id(rs.getInt("sale_id"));
				list.add(reminder_sVO);
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
