package com.note.model;

import java.sql.*;
import java.util.*;

public class NoteJDBCDAO implements NoteDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO Note (note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT note_id,note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id,note_like FROM Note order by note_id ";
		private static final String GET_ONE_STMT = 
			"SELECT note_id,note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id,note_like FROM Note where note_id = ?";
		private static final String DELETE = 
			"DELETE FROM Note where note_id = ?";
		private static final String UPDATE = 
			"UPDATE Note set note_classid=?, note_date=?, travel_start=?, note_title=?, note_description=?, note_update=?, users_id=?, trip_id=?  where note_id = ?";

		@Override
		public void insert(NoteVO noteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, noteVO.getNote_classid());
				pstmt.setTimestamp(2, noteVO.getNote_date());
				pstmt.setDate(3, noteVO.getTravel_start());
				pstmt.setString(4, noteVO.getNote_title());
				pstmt.setString(5, noteVO.getNote_description());
				pstmt.setTimestamp(6, noteVO.getNote_update());
				pstmt.setInt(7, noteVO.getUsers_id());
//				pstmt.setInt(8, noteVO.getTrip_id());
				pstmt.setObject(8,noteVO.getTrip_id(), Types.INTEGER);

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

		@Override
		public void update(NoteVO noteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, noteVO.getNote_classid());
				pstmt.setTimestamp(2, noteVO.getNote_date());
				pstmt.setDate(3, noteVO.getTravel_start());
				pstmt.setString(4, noteVO.getNote_title());
				pstmt.setString(5, noteVO.getNote_description());
				pstmt.setTimestamp(6, noteVO.getNote_update());
				pstmt.setInt(7, noteVO.getUsers_id());
				pstmt.setInt(8, noteVO.getTrip_id());
				pstmt.setInt(9, noteVO.getNote_id());

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

		@Override
		public void delete(Integer note_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, note_id);

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

		@Override
		public NoteVO findByPrimaryKey(Integer note_id) {

			NoteVO noteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, note_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					
					noteVO = new NoteVO();
					noteVO.setNote_id(rs.getInt("note_id"));
					noteVO.setNote_classid(rs.getInt("note_classid"));
					noteVO.setNote_date(rs.getTimestamp("note_date"));
					noteVO.setTravel_start(rs.getDate("travel_start"));
					noteVO.setNote_title(rs.getString("note_title"));
					noteVO.setNote_description(rs.getString("note_description"));
					noteVO.setNote_update(rs.getTimestamp("note_update"));
					noteVO.setUsers_id(rs.getInt("users_id"));
					noteVO.setTrip_id(rs.getInt("trip_id"));
					noteVO.setNote_like(rs.getInt("note_like"));
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
			return noteVO;
		}

		@Override
		public List<NoteVO> getAll() {
			List<NoteVO> list = new ArrayList<NoteVO>();
			NoteVO noteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					noteVO = new NoteVO();
					noteVO.setNote_id(rs.getInt("note_id"));
					noteVO.setNote_classid(rs.getInt("note_classid"));
					noteVO.setNote_date(rs.getTimestamp("note_date"));
					noteVO.setTravel_start(rs.getDate("travel_start"));
					noteVO.setNote_title(rs.getString("note_title"));
					noteVO.setNote_description(rs.getString("note_description"));
					noteVO.setNote_update(rs.getTimestamp("note_update"));
					noteVO.setUsers_id(rs.getInt("users_id"));
					noteVO.setTrip_id(rs.getInt("trip_id"));
					noteVO.setNote_like(rs.getInt("note_like"));
					list.add(noteVO); // Store the row in the list
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
		}

		public static void main(String[] args) {

			NoteJDBCDAO dao = new NoteJDBCDAO();

			// 新增
//			NoteVO noteVO1 = new NoteVO();
//			noteVO1.setNote_classid(1);
//			noteVO1.setNote_date(java.sql.Timestamp.valueOf("2021-02-28 08:12:12"));
//			noteVO1.setTravel_start(java.sql.Date.valueOf("2021-02-28"));
//			noteVO1.setNote_title("今天天氣晴");
//			noteVO1.setNote_description("天氣晴宜蘭好好玩");
//			noteVO1.setNote_update(java.sql.Timestamp.valueOf("2021-02-28 08:12:12"));
//			noteVO1.setUsers_id(10);
//			dao.insert(noteVO1);

			// 修改
//			NoteVO noteVO2 = new NoteVO();
//			noteVO2.setNote_id(2);
//			noteVO2.setNote_classid(2);
//			noteVO2.setNote_date(java.sql.Timestamp.valueOf("2021-02-26 09:10:12"));
//			noteVO2.setTravel_start(java.sql.Date.valueOf("2021-02-26"));
//			noteVO2.setNote_title("今天天氣陰雨");
//			noteVO2.setNote_description("天氣不好只好宅");
//			noteVO2.setNote_update(java.sql.Timestamp.valueOf("2021-02-26 09:10:12"));
//			noteVO2.setUsers_id(20);
//			noteVO2.setTrip_id(3);
//			dao.update(noteVO2);
//
//			// 刪除
//			dao.delete(3);
//
//			// 查詢
//			NoteVO noteVO3 = dao.findByPrimaryKey(2);
//			System.out.print(noteVO3.getNote_id() + ",");
//			System.out.print(noteVO3.getNote_classid() + ",");
//			System.out.print(noteVO3.getNote_date() + ",");
//			System.out.print(noteVO3.getTravel_start() + ",");
//			System.out.print(noteVO3.getNote_title() + ",");
//			System.out.print(noteVO3.getNote_description() + ",");
//			System.out.println(noteVO3.getNote_update()+ ",");
//			System.out.println(noteVO3.getUsers_id()+ ",");
//			System.out.println(noteVO3.getTrip_id()+ ",");
//			System.out.println(noteVO3.getNote_like());
//			System.out.println("---------------------");
//
//			// 查詢
			List<NoteVO> list = dao.getAll();
			for (NoteVO aNote : list) {
				System.out.print(aNote.getNote_id() + ",");
				System.out.print(aNote.getNote_classid() + ",");
				System.out.print(aNote.getNote_date() + ",");
				System.out.print(aNote.getTravel_start() + ",");
				System.out.print(aNote.getNote_title() + ",");
				System.out.print(aNote.getNote_description() + ",");
				System.out.print(aNote.getNote_update() + ",");
				System.out.print(aNote.getUsers_id() + ",");
				System.out.print(aNote.getTrip_id()+ ",");
				System.out.println(aNote.getNote_like());
				System.out.println();
			}
		}
}
