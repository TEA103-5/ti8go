package com.msg_n_c.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;



public class Msg_n_cJDBCDAO implements Msg_n_cDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	

	private static final String INSERT_STMT = 
			"INSERT INTO msg_n_c(msg_n_c_text,users_id,msg_n_id) VALUES (?, ?, ?)";

	private static final String DELETE = 
			"DELETE FROM msg_n_c where msg_n_c_id = ?";
	private static final String UPDATE = 
			"UPDATE msg_n_c set msg_n_c_text=? where msg_n_c_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT msg_n_c_id,msg_n_c_time,msg_n_c_time_last,msg_n_c_status,msg_n_c_ip,msg_n_c_text,users_id,msg_n_id FROM msg_n_c where msg_n_c_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT msg_n_c_id,msg_n_c_time,msg_n_c_time_last,msg_n_c_status,msg_n_c_ip,msg_n_c_text,users_id,msg_n_id FROM msg_n_c order by msg_n_c_id";
	
	//新增
	@Override
	public void insert(Msg_n_cVO msg_n_cVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msg_n_cVO.getMsg_n_c_text());
			pstmt.setInt(3, msg_n_cVO.getMsg_n_id());
			pstmt.setInt(2, msg_n_cVO.getUsers_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	//修改
	@Override
	   public void update(Msg_n_cVO msg_n_cVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, msg_n_cVO.getMsg_n_c_text());
				pstmt.setInt(2, msg_n_cVO.getMsg_n_c_id());
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
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

	   };
	   
	   //刪除
	   @Override
	    public void delete(Integer msg_n_c_id) {
	    	
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, msg_n_c_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
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

	    };
	    
	    //查一筆
	    @Override
	    public Msg_n_cVO findByPrimaryKey(Integer msg_n_c_id) {
	    	Msg_n_cVO msg_n_cVO=null;
	    	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,  msg_n_c_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					msg_n_cVO = new Msg_n_cVO();
					msg_n_cVO.setMsg_n_c_id(rs.getInt("msg_n_c_id"));
					msg_n_cVO.setMsg_n_c_time(rs.getTimestamp("msg_n_c_time"));
					msg_n_cVO.setMsg_n_c_time_last(rs.getTimestamp("msg_n_c_time_last"));
					msg_n_cVO.setMsg_n_c_status(rs.getInt("msg_n_c_status"));
					msg_n_cVO.setMsg_n_c_ip(rs.getString("msg_n_c_ip"));
					msg_n_cVO.setMsg_n_c_text(rs.getString("msg_n_c_text"));
					msg_n_cVO.setUsers_id(rs.getInt("users_id"));
					msg_n_cVO.setMsg_n_id(rs.getInt("Msg_n_id"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
	    	
	    	return msg_n_cVO;
	    };
	    
	    //查全部
	    @Override
	    public List<Msg_n_cVO> getAll(){
	    	List<Msg_n_cVO> list = new ArrayList<Msg_n_cVO>();
			Msg_n_cVO msg_n_cVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					msg_n_cVO = new Msg_n_cVO();
					msg_n_cVO.setMsg_n_c_id(rs.getInt("msg_n_c_id"));
					msg_n_cVO.setMsg_n_c_time(rs.getTimestamp("msg_n_c_time"));
					msg_n_cVO.setMsg_n_c_time_last(rs.getTimestamp("msg_n_c_time_last"));
					msg_n_cVO.setMsg_n_c_status(rs.getInt("msg_n_c_status"));
					msg_n_cVO.setMsg_n_c_ip(rs.getString("msg_n_c_ip"));
					msg_n_cVO.setMsg_n_c_text(rs.getString("msg_n_c_text"));
					msg_n_cVO.setUsers_id(rs.getInt("users_id"));
					msg_n_cVO.setMsg_n_id(rs.getInt("Msg_n_id"));
					list.add(msg_n_cVO);
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
	    };
	//測試
	public static void main(String[] args) throws Exception{

		Msg_n_cJDBCDAO dao = new Msg_n_cJDBCDAO();

		// 新增
//		Msg_n_cVO empVO1 = new Msg_n_cVO();
//		empVO1.setMsg_n_id(1);
//		empVO1.setUsers_id(1);
//		empVO1.setMsg_n_c_text("測試文字123");
//		dao.insert(empVO1);

		// 修改
//		Msg_n_cVO empVO2 = new Msg_n_cVO();
//		empVO2.setMsg_n_c_text("測試文字321");
//		empVO2.setMsg_n_c_id(1);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(3);

//		// 查詢1筆
		Msg_n_cVO empVO3 = dao.findByPrimaryKey(1);
		System.out.print(empVO3.getMsg_n_c_id() + ",");
		System.out.print(empVO3.getMsg_n_c_text() + ",");
		System.out.print(empVO3.getMsg_n_c_time() + ",");
		System.out.print(empVO3.getMsg_n_id() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
		System.out.println();
		System.out.println("--------------");
//
//
//		// 查詢全部
		List<Msg_n_cVO> list = dao.getAll();
		for (Msg_n_cVO aEmp : list) {
			System.out.print(empVO3.getMsg_n_c_id() + ",");
			System.out.print(empVO3.getMsg_n_c_text() + ",");
			System.out.print(empVO3.getMsg_n_c_time() + ",");
			System.out.print(empVO3.getMsg_n_id() + ",");
			System.out.println("----");
			System.out.println();
		}
	}
}
