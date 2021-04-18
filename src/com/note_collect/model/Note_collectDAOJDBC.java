package com.note_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.note_collect.model.Note_collectVO;

public class Note_collectDAOJDBC implements Note_collectDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO note_collect (note_id , users_id) VALUES (? , ?)";
	private static final String GET_ALL_STMT = "SELECT note_id , users_id , note_collect_time FROM note_collect order by users_id";
	private static final String GET_ONE_STMT = "SELECT note_id , users_id , note_collect_time FROM note_collect where note_id = ? and users_id = ?";
	private static final String DELETE = "DELETE FROM note_collect where note_id=? and users_id = ?";
	// 收藏不會用更新的方式去變更 , 所以更新指令只是練習用的,條件寫死在update方法裡
//	private static final String UPDATE = "UPDATE note_collect set note_id=? , users_id=? where note_id=? and users_id = ?";

	private static final String GET_ALL_BY_ID_STMT = "SELECT note_id , users_id , note_collect_time FROM note_collect where users_id = ?";
	
	@Override
	public void insert(Note_collectVO note_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, note_collectVO.getNote_id(), Types.INTEGER);
			pstmt.setObject(2, note_collectVO.getUsers_id(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

//	@Override
//	public void update(Note_collectVO note_collectVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setObject(1, note_collectVO.getNote_id(), Types.INTEGER);
//			pstmt.setObject(2, note_collectVO.getUsers_id(), Types.INTEGER);
//			pstmt.setObject(3, 1, Types.INTEGER);
//			pstmt.setObject(4, 1, Types.INTEGER);
//			
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	@Override
	public void delete(Integer note_id , Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setObject(1, note_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Note_collectVO findByPrimaryKey(Integer note_id , Integer users_id) {
		Note_collectVO note_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, note_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				note_collectVO = new Note_collectVO();
				
				note_collectVO.setNote_id(rs.getInt("note_id"));
				note_collectVO.setUsers_id(rs.getInt("users_id"));
				note_collectVO.setNote_collect_time(rs.getTimestamp("note_collect_time"));
				
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
		return note_collectVO;
	}

	@Override
	public List<Note_collectVO> getAll() {
		List<Note_collectVO> list = new ArrayList<Note_collectVO>();
		Note_collectVO note_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				note_collectVO = new Note_collectVO();
				
				note_collectVO.setNote_id(rs.getInt("note_id"));
				note_collectVO.setUsers_id(rs.getInt("users_id"));
				note_collectVO.setNote_collect_time(rs.getTimestamp("note_collect_time"));
				
				list.add(note_collectVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
	@Override
	public List<Note_collectVO> getAllByPrimaryKey(Integer users_id) {
		List<Note_collectVO> list = new ArrayList<Note_collectVO>();
		Note_collectVO note_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_ID_STMT);
			pstmt.setObject(1, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				note_collectVO = new Note_collectVO();
				
				note_collectVO.setNote_id(rs.getInt("note_id"));
				note_collectVO.setUsers_id(rs.getInt("users_id"));
				note_collectVO.setNote_collect_time(rs.getTimestamp("note_collect_time"));
				
				list.add(note_collectVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String args[]) {
		Note_collectDAOJDBC dao = new Note_collectDAOJDBC();

//		// 新增
//		Note_collectVO note_collectVO1 = new Note_collectVO();
//		note_collectVO1.setNote_id(3);
//		note_collectVO1.setUsers_id(10);
//		dao.insert(note_collectVO1);
//
//		// 修改
//		Note_collectVO note_collectVO2 = new Note_collectVO();
//		note_collectVO2.setNote_id(1);
//		note_collectVO2.setUsers_id(2);
//		dao.update(note_collectVO2);
//		
//		// 刪除
//		dao.delete(3 , 10);
		
		// 查詢一筆
//		Note_collectVO note_collectVO3 = dao.findByPrimaryKey(3 , 7);
//		
//		System.out.print(note_collectVO3.getNote_id() + ",");
//		System.out.print(note_collectVO3.getUsers_id() + ",");
//		System.out.println(note_collectVO3.getNote_collect_time() );
//		System.out.println("---------------------");
//		
//		// 查詢全部
		List<Note_collectVO> list = dao.getAll();
		
		for(Note_collectVO aNote_collect : list) {
			
			System.out.print(aNote_collect.getNote_id() + ",");
			System.out.print(aNote_collect.getUsers_id() + ",");
			System.out.print(aNote_collect.getNote_collect_time() );
			System.out.println();
		}
		
	}

}
