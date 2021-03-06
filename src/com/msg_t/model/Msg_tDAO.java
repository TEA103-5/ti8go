package com.msg_t.model;

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

import com.msg_p.model.Msg_pVO;

public class Msg_tDAO implements Msg_tDAO_interface{
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
			"INSERT INTO msg_t(msg_t_text,users_id,trip_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_t where msg_t_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_t set msg_t_text=? where msg_t_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_t_id,msg_t_time,msg_t_time_last,msg_t_status,msg_t_ip,msg_t_text,users_id,trip_id FROM msg_t where msg_t_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_t_id,msg_t_time,msg_t_time_last,msg_t_status,msg_t_ip,msg_t_text,users_id,trip_id FROM msg_t order by msg_t_id";
		

	@Override
	public void insert(Msg_tVO msg_tVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_tVO.getMsg_t_text());
			pstmt.setInt(3, msg_tVO.getTrip_id());
			pstmt.setInt(2, msg_tVO.getUsers_id());
			
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
	public void update(Msg_tVO msg_tVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_tVO.getMsg_t_text());
			pstmt.setInt(2, msg_tVO.getMsg_t_id());
			
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
	public void delete(Integer msg_t_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_t_id);

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
	public Msg_tVO findByPrimaryKey(Integer msg_t_id) {
		Msg_tVO msg_tVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_t_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_tVO = new Msg_tVO();
				msg_tVO.setMsg_t_id(rs.getInt("msg_t_id"));
				msg_tVO.setMsg_t_time(rs.getTimestamp("msg_t_time"));
				msg_tVO.setMsg_t_time_last(rs.getTimestamp("msg_t_time_last"));
				msg_tVO.setMsg_t_status(rs.getInt("msg_t_status"));
				msg_tVO.setMsg_t_ip(rs.getString("msg_t_ip"));
				msg_tVO.setMsg_t_text(rs.getString("msg_t_text"));
				msg_tVO.setUsers_id(rs.getInt("users_id"));
				msg_tVO.setTrip_id(rs.getInt("trip_id"));
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
		return msg_tVO;
	}
	
	@Override
	public List<Msg_tVO> getAll() {
		List<Msg_tVO> list = new ArrayList<Msg_tVO>();
		Msg_tVO msg_tVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_tVO = new Msg_tVO();
				msg_tVO.setMsg_t_id(rs.getInt("msg_t_id"));
				msg_tVO.setMsg_t_time(rs.getTimestamp("msg_t_time"));
				msg_tVO.setMsg_t_time_last(rs.getTimestamp("msg_t_time_last"));
				msg_tVO.setMsg_t_status(rs.getInt("msg_t_status"));
				msg_tVO.setMsg_t_ip(rs.getString("msg_t_ip"));
				msg_tVO.setMsg_t_text(rs.getString("msg_t_text"));
				msg_tVO.setUsers_id(rs.getInt("users_id"));
				msg_tVO.setTrip_id(rs.getInt("trip_id"));
				list.add(msg_tVO);
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
