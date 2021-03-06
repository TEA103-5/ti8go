package com.msg_n.model;

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




public class Msg_nDAO implements Msg_nDAO_interface{
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
			"INSERT INTO msg_n(msg_n_text,users_id,note_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_n where msg_n_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_n set msg_n_text=? where msg_n_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_n_id,msg_n_time,msg_n_time_last,msg_n_status,msg_n_ip,msg_n_text,users_id,note_id FROM msg_n where msg_n_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_n_id,msg_n_time,msg_n_time_last,msg_n_status,msg_n_ip,msg_n_text,users_id,note_id FROM msg_n order by msg_n_id";
		

	@Override
	public void insert(Msg_nVO msg_nVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_nVO.getMsg_n_text());
			pstmt.setInt(3, msg_nVO.getNote_id());
			pstmt.setInt(2, msg_nVO.getUsers_id());
			
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
	public void update(Msg_nVO msg_nVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_nVO.getMsg_n_text());
			pstmt.setInt(2, msg_nVO.getMsg_n_id());
			
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
	public void delete(Integer msg_n_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_n_id);

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
	public Msg_nVO findByPrimaryKey(Integer msg_n_id) {
		Msg_nVO msg_nVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_n_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_nVO = new Msg_nVO();
				msg_nVO.setMsg_n_id(rs.getInt("msg_n_id"));
				msg_nVO.setMsg_n_time(rs.getTimestamp("msg_n_time"));
				msg_nVO.setMsg_n_time_last(rs.getTimestamp("msg_n_time_last"));
				msg_nVO.setMsg_n_status(rs.getInt("msg_n_status"));
				msg_nVO.setMsg_n_ip(rs.getString("msg_n_ip"));
				msg_nVO.setMsg_n_text(rs.getString("msg_n_text"));
				msg_nVO.setUsers_id(rs.getInt("users_id"));
				msg_nVO.setNote_id(rs.getInt("note_id"));
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
		return msg_nVO;
	}
	
	@Override
	public List<Msg_nVO> getAll() {
		List<Msg_nVO> list = new ArrayList<Msg_nVO>();
		Msg_nVO msg_nVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_nVO = new Msg_nVO();
				msg_nVO.setMsg_n_id(rs.getInt("msg_n_id"));
				msg_nVO.setMsg_n_time(rs.getTimestamp("msg_n_time"));
				msg_nVO.setMsg_n_time_last(rs.getTimestamp("msg_n_time_last"));
				msg_nVO.setMsg_n_status(rs.getInt("msg_n_status"));
				msg_nVO.setMsg_n_ip(rs.getString("msg_n_ip"));
				msg_nVO.setMsg_n_text(rs.getString("msg_n_text"));
				msg_nVO.setUsers_id(rs.getInt("users_id"));
				msg_nVO.setNote_id(rs.getInt("note_id"));
				list.add(msg_nVO);
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
