package com.notec.model;

import java.io.IOException;
import java.sql.*;
import java.util.*;



public class NoteCJDBCDAO implements NoteCDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Note_C (note_c_title,note_c_content,note_c_img,note_id) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT note_c_id,note_c_title,note_c_content,note_c_img,note_id FROM Note_c order by note_c_id ";
		private static final String GET_ONE_STMT = 
			"SELECT note_c_id,note_c_title,note_c_content,note_c_img,note_id FROM Note_c where note_c_id = ?";
		private static final String DELETE = 
			"DELETE FROM Note_c where note_c_id = ?";
		private static final String UPDATE = 
			"UPDATE Note_c set note_c_title=?, note_c_content=?, note_c_img=?, note_id=? where note_c_id = ?";

		@Override
		public void insert(NoteCVO noteCVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, noteCVO.getNote_c_title());
				pstmt.setString(2, noteCVO.getNote_c_content());
				pstmt.setBytes(3, noteCVO.getNote_c_img());
				pstmt.setInt(4, noteCVO.getNote_id());

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
			} 
			finally {
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
		public void update(NoteCVO noteCVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, noteCVO.getNote_c_title());
				pstmt.setString(2, noteCVO.getNote_c_content());
				pstmt.setBytes(3, noteCVO.getNote_c_img());
				pstmt.setInt(4, noteCVO.getNote_id());
				pstmt.setInt(5, noteCVO.getNote_c_id());

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
		public void delete(Integer note_c_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, note_c_id);

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
		public NoteCVO findByPrimaryKey(Integer note_c_id) {

			NoteCVO noteCVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, note_c_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					
					noteCVO = new NoteCVO();
					noteCVO.setNote_c_id(rs.getInt("note_c_id"));
					noteCVO.setNote_c_title(rs.getString("note_c_title"));
					noteCVO.setNote_c_content(rs.getString("note_c_content"));
					noteCVO.setNote_c_img(rs.getBytes("note_c_img"));
					noteCVO.setNote_id(rs.getInt("note_id"));
					
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
			return noteCVO;
		}

		@Override
		public List<NoteCVO> getAll() {
			List<NoteCVO> list = new ArrayList<NoteCVO>();
			NoteCVO noteCVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					noteCVO = new NoteCVO();
					noteCVO.setNote_c_id(rs.getInt("note_c_id"));
					noteCVO.setNote_c_title(rs.getString("note_c_title"));
					noteCVO.setNote_c_content(rs.getString("note_c_content"));
					noteCVO.setNote_c_img(rs.getBytes("note_c_img"));
					noteCVO.setNote_id(rs.getInt("note_id"));
					list.add(noteCVO); // Store the row in the list
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

		public static void main(String[] args) throws IOException {

			NoteCJDBCDAO dao = new NoteCJDBCDAO();
			Picture picture = new Picture();

			// 新增
//			NoteCVO noteCVO1 = new NoteCVO();
//			noteCVO1.setNote_c_title("宜蘭遊");
//			noteCVO1.setNote_c_content("今天天氣晴宜蘭好好玩");
//			noteCVO1.setNote_c_img(picture.getPictureByteArray("C:/TEA103_Workspace/TivagoTest/items/1.jpg"));
//			noteCVO1.setNote_id(2);
//			
//			dao.insert(noteCVO1);

			// 修改
			NoteCVO noteCVO2 = new NoteCVO();
			noteCVO2.setNote_c_id(3);
			noteCVO2.setNote_c_title("宜蘭天氣晴");
			noteCVO2.setNote_c_content("今天好開心,天氣晴朗又好玩!");
			noteCVO2.setNote_c_img(picture.getPictureByteArray("C:/TEA103_Workspace/TivagoTest/items/2.jpg"));
			noteCVO2.setNote_id(1);
			dao.update(noteCVO2);

//			// 刪除
//			dao.delete(2);
//
//			// 查詢
//			NoteCVO noteCVO3 = dao.findByPrimaryKey(2);
//			System.out.print(noteCVO3.getNote_c_id() + ",");
//			System.out.print(noteCVO3.getNote_c_title() + ",");
//			System.out.print(noteCVO3.getNote_c_content() + ",");
//			System.out.print(noteCVO3.getNote_c_img() + ",");
//			System.out.print(noteCVO3.getNote_id() + ",");
//			
//			System.out.println("---------------------");
//
//			// 查詢
//			List<NoteCVO> list = dao.getAll();
//			for (NoteCVO aNoteC : list) {
//				System.out.print(aNoteC.getNote_c_id() + ",");
//				System.out.print(aNoteC.getNote_c_title() + ",");
//				System.out.print(aNoteC.getNote_c_content() + ",");
//				System.out.print(aNoteC.getNote_c_img() + ",");
//				System.out.print(aNoteC.getNote_id() + ",");
//				System.out.println();
//			}
		}
}
