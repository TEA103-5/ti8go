package com.msg_p.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Msg_pDAO implements Msg_pDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO msg_p(msg_p_text,users_id,place_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_p where msg_p_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_p set msg_p_text=? where msg_p_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_p_id,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id FROM msg_p where msg_p_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_p_id,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id FROM msg_p order by msg_p_id";
		

	@Override
	public void insert(Msg_pVO msg_pVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_pVO.getMsg_p_text());
			pstmt.setInt(3, msg_pVO.getPlace_id());
			pstmt.setInt(2, msg_pVO.getUsers_id());
			
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
	public void update(Msg_pVO msg_pVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_pVO.getMsg_p_text());
			pstmt.setInt(2, msg_pVO.getMsg_p_id());
			
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
	public void delete(Integer msg_p_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_p_id);

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
	public Msg_pVO findByPrimaryKey(Integer msg_p_id) {
		Msg_pVO msg_pVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_p_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_pVO = new Msg_pVO();
				msg_pVO.setMsg_p_id(rs.getInt("msg_p_id"));
				msg_pVO.setMsg_p_time(rs.getTimestamp("msg_p_time"));
				msg_pVO.setMsg_p_time_last(rs.getTimestamp("msg_p_time_last"));
				msg_pVO.setMsg_p_status(rs.getInt("msg_p_status"));
				msg_pVO.setMsg_p_ip(rs.getString("msg_p_ip"));
				msg_pVO.setMsg_p_text(rs.getString("msg_p_text"));
				msg_pVO.setUsers_id(rs.getInt("users_id"));
				msg_pVO.setPlace_id(rs.getInt("place_id"));
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
		return msg_pVO;
	}
	
	@Override
	public List<Msg_pVO> getAll() {
		List<Msg_pVO> list = new ArrayList<Msg_pVO>();
		Msg_pVO msg_pVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_pVO = new Msg_pVO();
				msg_pVO.setMsg_p_id(rs.getInt("msg_p_id"));
				msg_pVO.setMsg_p_time(rs.getTimestamp("msg_p_time"));
				msg_pVO.setMsg_p_time_last(rs.getTimestamp("msg_p_time_last"));
				msg_pVO.setMsg_p_status(rs.getInt("msg_p_status"));
				msg_pVO.setMsg_p_ip(rs.getString("msg_p_ip"));
				msg_pVO.setMsg_p_text(rs.getString("msg_p_text"));
				msg_pVO.setUsers_id(rs.getInt("users_id"));
				msg_pVO.setPlace_id(rs.getInt("place_id"));
				list.add(msg_pVO);
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
