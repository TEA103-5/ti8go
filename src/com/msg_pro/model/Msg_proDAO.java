package com.msg_pro.model;

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

public class Msg_proDAO implements Msg_proDAO_interface{
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
			"INSERT INTO msg_pro(msg_pro_text,users_id,product_id) VALUES (?, ?, ?)";
	//這裡有個問題是PK用完了怎麼辦?
	private static final String DELETE = 
			"DELETE FROM msg_pro where msg_pro_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_pro set msg_pro_text=? where msg_pro_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_pro_id,msg_pro_time,msg_pro_time_last,msg_pro_status,msg_pro_ip,msg_pro_text,users_id,product_id FROM msg_pro where msg_pro_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_pro_id,msg_pro_time,msg_pro_time_last,msg_pro_status,msg_pro_ip,msg_pro_text,users_id,product_id FROM msg_pro order by msg_pro_id";
		

	@Override
	public void insert(Msg_proVO msg_proVO) {//新增
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, msg_proVO.getMsg_pro_text());
			pstmt.setInt(3, msg_proVO.getProduct_id());
			pstmt.setInt(2, msg_proVO.getUsers_id());
			
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
	public void update(Msg_proVO msg_proVO) {//更新
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msg_proVO.getMsg_pro_text());
			pstmt.setInt(2, msg_proVO.getMsg_pro_id());
			
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
	public void delete(Integer msg_pro_id) {//刪除
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, msg_pro_id);

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
	public Msg_proVO findByPrimaryKey(Integer msg_pro_id) {
		Msg_proVO msg_proVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			,msg_p_time,msg_p_time_last,msg_p_status,msg_p_ip,msg_p_text,users_id,place_id
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, msg_pro_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				msg_proVO = new Msg_proVO();
				msg_proVO.setMsg_pro_id(rs.getInt("msg_pro_id"));
				msg_proVO.setMsg_pro_time(rs.getTimestamp("msg_pro_time"));
				msg_proVO.setMsg_pro_time_last(rs.getTimestamp("msg_pro_time_last"));
				msg_proVO.setMsg_pro_status(rs.getInt("msg_pro_status"));
				msg_proVO.setMsg_pro_ip(rs.getString("msg_pro_ip"));
				msg_proVO.setMsg_pro_text(rs.getString("msg_pro_text"));
				msg_proVO.setUsers_id(rs.getInt("users_id"));
				msg_proVO.setProduct_id(rs.getInt("product_id"));
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
		return msg_proVO;
	}
	
	@Override
	public List<Msg_proVO> getAll() {
		List<Msg_proVO> list = new ArrayList<Msg_proVO>();
		Msg_proVO msg_proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				msg_proVO = new Msg_proVO();
				msg_proVO.setMsg_pro_id(rs.getInt("msg_pro_id"));
				msg_proVO.setMsg_pro_time(rs.getTimestamp("msg_pro_time"));
				msg_proVO.setMsg_pro_time_last(rs.getTimestamp("msg_pro_time_last"));
				msg_proVO.setMsg_pro_status(rs.getInt("msg_pro_status"));
				msg_proVO.setMsg_pro_ip(rs.getString("msg_pro_ip"));
				msg_proVO.setMsg_pro_text(rs.getString("msg_pro_text"));
				msg_proVO.setUsers_id(rs.getInt("users_id"));
				msg_proVO.setProduct_id(rs.getInt("product_id"));
				list.add(msg_proVO);
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
