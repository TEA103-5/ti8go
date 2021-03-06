package com.msg_t_c.model;

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



public class Msg_t_cDAO implements Msg_t_cDAO_interface{
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
			"INSERT INTO msg_t_c(msg_t_c_text,users_id,msg_t_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_t_c where msg_t_c_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_t_c set msg_t_c_text=? where msg_t_c_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_t_c_id,msg_t_c_time,msg_t_c_time_last,msg_t_c_status,msg_t_c_ip,msg_t_c_text,users_id,msg_t_id FROM msg_t_c where msg_t_c_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_t_c_id,msg_t_c_time,msg_t_c_time_last,msg_t_c_status,msg_t_c_ip,msg_t_c_text,users_id,msg_t_id FROM msg_t_c order by msg_t_c_id";
		

	@Override
	public void insert(Msg_t_cVO msg_t_cVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_t_cVO.getMsg_t_c_text());
			pstmt.setInt(3, msg_t_cVO.getMsg_t_id());
			pstmt.setInt(2, msg_t_cVO.getUsers_id());
			
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
	public void update(Msg_t_cVO msg_t_cVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_t_cVO.getMsg_t_c_text());
			pstmt.setInt(2, msg_t_cVO.getMsg_t_c_id());
			
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
	public void delete(Integer msg_t_c_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_t_c_id);

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
	public Msg_t_cVO findByPrimaryKey(Integer msg_t_c_id) {
		Msg_t_cVO msg_t_cVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_t_c_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_t_cVO = new Msg_t_cVO();
				msg_t_cVO.setMsg_t_c_id(rs.getInt("msg_t_c_id"));
				msg_t_cVO.setMsg_t_c_time(rs.getTimestamp("msg_t_c_time"));
				msg_t_cVO.setMsg_t_c_time_last(rs.getTimestamp("msg_t_c_time_last"));
				msg_t_cVO.setMsg_t_c_status(rs.getInt("msg_t_c_status"));
				msg_t_cVO.setMsg_t_c_ip(rs.getString("msg_t_c_ip"));
				msg_t_cVO.setMsg_t_c_text(rs.getString("msg_t_c_text"));
				msg_t_cVO.setUsers_id(rs.getInt("users_id"));
				msg_t_cVO.setMsg_t_id(rs.getInt("Msg_t_id"));
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
		return msg_t_cVO;
	}
	
	@Override
	public List<Msg_t_cVO> getAll() {
		List<Msg_t_cVO> list = new ArrayList<Msg_t_cVO>();
		Msg_t_cVO msg_t_cVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_t_cVO = new Msg_t_cVO();
				msg_t_cVO.setMsg_t_c_id(rs.getInt("msg_t_c_id"));
				msg_t_cVO.setMsg_t_c_time(rs.getTimestamp("msg_t_c_time"));
				msg_t_cVO.setMsg_t_c_time_last(rs.getTimestamp("msg_t_c_time_last"));
				msg_t_cVO.setMsg_t_c_status(rs.getInt("msg_t_c_status"));
				msg_t_cVO.setMsg_t_c_ip(rs.getString("msg_t_c_ip"));
				msg_t_cVO.setMsg_t_c_text(rs.getString("msg_t_c_text"));
				msg_t_cVO.setUsers_id(rs.getInt("users_id"));
				msg_t_cVO.setMsg_t_id(rs.getInt("Msg_t_id"));
				list.add(msg_t_cVO);
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
