package com.msg_p_c.model;

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



public class Msg_p_cDAO implements Msg_p_cDAO_interface{
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
			"INSERT INTO msg_p_c(msg_p_c_text,users_id,msg_p_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_p_c where msg_p_c_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_p_c set msg_p_c_text=? where msg_p_c_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_p_c_id,msg_p_c_time,msg_p_c_time_last,msg_p_c_status,msg_p_c_ip,msg_p_c_text,users_id,msg_p_id FROM msg_p_c where msg_p_c_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_p_c_id,msg_p_c_time,msg_p_c_time_last,msg_p_c_status,msg_p_c_ip,msg_p_c_text,users_id,msg_p_id FROM msg_p_c order by msg_p_c_id";
		

	@Override
	public void insert(Msg_p_cVO msg_p_cVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_p_cVO.getMsg_p_c_text());
			pstmt.setInt(3, msg_p_cVO.getMsg_p_id());
			pstmt.setInt(2, msg_p_cVO.getUsers_id());
			
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
	public void update(Msg_p_cVO msg_p_cVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_p_cVO.getMsg_p_c_text());
			pstmt.setInt(2, msg_p_cVO.getMsg_p_c_id());
			
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
	public void delete(Integer msg_p_c_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_p_c_id);

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
	public Msg_p_cVO findByPrimaryKey(Integer msg_p_c_id) {
		Msg_p_cVO msg_p_cVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_p_c_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_p_cVO = new Msg_p_cVO();
				msg_p_cVO.setMsg_p_c_id(rs.getInt("msg_p_c_id"));
				msg_p_cVO.setMsg_p_c_time(rs.getTimestamp("msg_p_c_time"));
				msg_p_cVO.setMsg_p_c_time_last(rs.getTimestamp("msg_p_c_time_last"));
				msg_p_cVO.setMsg_p_c_status(rs.getInt("msg_p_c_status"));
				msg_p_cVO.setMsg_p_c_ip(rs.getString("msg_p_c_ip"));
				msg_p_cVO.setMsg_p_c_text(rs.getString("msg_p_c_text"));
				msg_p_cVO.setUsers_id(rs.getInt("users_id"));
				msg_p_cVO.setMsg_p_id(rs.getInt("Msg_p_id"));
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
		return msg_p_cVO;
	}
	
	@Override
	public List<Msg_p_cVO> getAll() {
		List<Msg_p_cVO> list = new ArrayList<Msg_p_cVO>();
		Msg_p_cVO msg_p_cVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_p_cVO = new Msg_p_cVO();
				msg_p_cVO.setMsg_p_c_id(rs.getInt("msg_p_c_id"));
				msg_p_cVO.setMsg_p_c_time(rs.getTimestamp("msg_p_c_time"));
				msg_p_cVO.setMsg_p_c_time_last(rs.getTimestamp("msg_p_c_time_last"));
				msg_p_cVO.setMsg_p_c_status(rs.getInt("msg_p_c_status"));
				msg_p_cVO.setMsg_p_c_ip(rs.getString("msg_p_c_ip"));
				msg_p_cVO.setMsg_p_c_text(rs.getString("msg_p_c_text"));
				msg_p_cVO.setUsers_id(rs.getInt("users_id"));
				msg_p_cVO.setMsg_p_id(rs.getInt("Msg_p_id"));
				list.add(msg_p_cVO);
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
