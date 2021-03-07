package com.note_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Note_collectDAOJDBC implements Note_collectDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO note_collect (note_id , users_id) VALUES (? , ?)";
	private static final String GET_ALL_STMT = "SELECT note_collect_id , note_id , users_id , note_collect_time FROM note_collect order by note_collect_id";
	private static final String GET_ONE_STMT = "SELECT note_collect_id , note_id , users_id , note_collect_time FROM note_collect where note_collect_id = ?";
	private static final String DELETE = "DELETE FROM note_collect where note_collect_id = ?";
	private static final String UPDATE = "UPDATE note_collect set note_id=? , users_id=? where note_collect_id = ?";

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

	@Override
	public void update(Note_collectVO note_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			;

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setObject(1, note_collectVO.getNote_id(), Types.INTEGER);
			pstmt.setObject(2, note_collectVO.getUsers_id(), Types.INTEGER);
			pstmt.setObject(3, note_collectVO.getNote_collect_id(), Types.INTEGER);
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
	public void delete(Integer note_collect_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setObject(1, note_collect_id, Types.INTEGER);
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
	public Note_collectVO findByPrimaryKey(Integer note_collect_id) {
		Note_collectVO note_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, note_collect_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				note_collectVO = new Note_collectVO();

				note_collectVO.setNote_collect_id(rs.getInt("note_collect_id"));
				note_collectVO.setNote_id(rs.getInt("note_id"));
				note_collectVO.setUsers_id(rs.getInt("users_id"));
				note_collectVO.setNote_collect_time(rs.getTimestamp("note_collect_time"));
			}

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
			if (rs != null) {
				try {
					rs.close();
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
				
				note_collectVO.setNote_collect_id(rs.getInt("note_collect_id"));
				note_collectVO.setNote_id(rs.getInt("note_id"));
				note_collectVO.setUsers_id(rs.getInt("users_id"));
				note_collectVO.setNote_collect_time(rs.getTimestamp("note_collect_time"));
				
				list.add(note_collectVO);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		Note_collectDAOJDBC dao = new Note_collectDAOJDBC();

//		// 新增
//		Note_collectVO note_collectVO1 = new Note_collectVO();
//		note_collectVO1.setNote_id(2);
//		note_collectVO1.setUsers_id(1);
//		dao.insert(note_collectVO1);
//
//		// 更新
//		Note_collectVO note_collectVO2 = new Note_collectVO();
//		note_collectVO2.setNote_id(2);
//		note_collectVO2.setUsers_id(1);
//		note_collectVO2.setNote_collect_id(1);
//		dao.update(note_collectVO2);
//
//		// 刪除
//		dao.delete(1);

		// 查詢一筆
		Note_collectVO note_collectVO3 = dao.findByPrimaryKey(2);

		System.out.print(note_collectVO3.getNote_collect_id() + ",");
		System.out.print(note_collectVO3.getNote_id() + ",");
		System.out.print(note_collectVO3.getUsers_id() + ",");
		System.out.println(note_collectVO3.getNote_collect_time());
		System.out.println("---------------------");
		
		// 查詢全部
		List<Note_collectVO> list = dao.getAll();
		
		for(Note_collectVO aNote_collect : list) {
			
			System.out.print(aNote_collect.getNote_collect_id() + ",");
			System.out.print(aNote_collect.getNote_id() + ",");
			System.out.print(aNote_collect.getUsers_id() + ",");
			System.out.println(aNote_collect.getNote_collect_time());
			System.out.println();
		}

	}
}
