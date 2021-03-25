package com.users.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class UsersDAO implements UsersDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO users (users_mail, users_pwd, users_identi_status, users_nickname, users_name, users_sex, users_birthday, users_id_number, users_pic, users_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE users SET users_mail =?, users_pwd =?, users_identi_status =?, users_nickname =?, users_name =?, users_sex =?, users_birthday =?, users_id_number =?, users_pic =?, users_phone =? where users_id = ?";
	private static final String DELETE_CARD = "DELETE FROM card WHERE users_id = ?";
	private static final String DELETE_USERS = "DELETE FROM users WHERE users_id = ?";
	private static final String FIND_BY_PK = "SELECT * FROM users WHERE users_id = ?";
	private static final String GET_ALL = "SELECT * FROM users"; 

//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public void insert(UsersVO usersVO) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
	//		pstmt.setInt(1, usersVO.getUsers_id());
			pstmt.setString(1, usersVO.getUsers_mail());
			pstmt.setString(2, usersVO.getUsers_pwd());
			pstmt.setInt(3, usersVO.getUsers_status());
			pstmt.setString(4, usersVO.getUsers_nickname());
			pstmt.setString(5, usersVO.getUsers_name());
			pstmt.setInt(6, usersVO.getUsers_sex());
			pstmt.setString(7, usersVO.getUsers_birthday());
			pstmt.setString(8, usersVO.getUsers_id_number());
		
			// 2. setBytes
			//	byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
			pstmt.setBytes(9, usersVO.getUsers_users_pic());
			pstmt.setString(10, usersVO.getUsers_phone());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	public void update(UsersVO usersVO) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, usersVO.getUsers_mail());
			pstmt.setString(2, usersVO.getUsers_pwd());
			pstmt.setInt(3, usersVO.getUsers_status());
			pstmt.setString(4, usersVO.getUsers_nickname());
			pstmt.setString(5, usersVO.getUsers_name());
			pstmt.setInt(6, usersVO.getUsers_sex());
			pstmt.setString(7, usersVO.getUsers_birthday());
			pstmt.setString(8, usersVO.getUsers_id_number());
		
			// 2. setBytes
			//	byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
			pstmt.setBytes(9, usersVO.getUsers_users_pic());
			pstmt.setString(10, usersVO.getUsers_phone());
			pstmt.setInt(11, usersVO.getUsers_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	@SuppressWarnings("resource")
	public void delete(Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_CARD);
			pstmt.setInt(1, users_id);
			
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_USERS);
			pstmt.setInt(1, users_id);
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	public UsersVO findByPK(Integer users_id) {
		UsersVO fBPK = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, users_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fBPK = new UsersVO();
				fBPK.setUsers_id(rs.getInt("users_id"));
				fBPK.setUsers_mail(rs.getString("users_mail"));
				fBPK.setUsers_pwd(rs.getString("users_pwd"));
				fBPK.setUsers_status(rs.getInt("users_identi_status"));
				fBPK.setUsers_nickname(rs.getString("users_nickname"));
				fBPK.setUsers_name(rs.getString("users_name"));
				fBPK.setUsers_sex(rs.getInt("users_sex"));
				fBPK.setUsers_birthday(rs.getString("users_birthday"));
				fBPK.setUsers_id_number(rs.getString("users_id_number"));
				fBPK.setUsers_phone(rs.getString("users_phone"));
				fBPK.setCreate_time(rs.getString("create_time"));
				fBPK.setUpdate_time(rs.getString("update_time"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
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

		return fBPK;
		
	}
	
	
	public List<UsersVO> getAll(){
		List<UsersVO> dataList = new ArrayList<>();
		UsersVO dataL = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dataL = new UsersVO();
				dataL.setUsers_id(rs.getInt("users_id"));
				dataL.setUsers_mail(rs.getString("users_mail"));
				dataL.setUsers_pwd(rs.getString("users_pwd"));
				dataL.setUsers_status(rs.getInt("users_identi_status"));
				dataL.setUsers_nickname(rs.getString("users_nickname"));
				dataL.setUsers_name(rs.getString("users_name"));
				dataL.setUsers_sex(rs.getInt("users_sex"));
				dataL.setUsers_birthday(rs.getString("users_birthday"));
				dataL.setUsers_id_number(rs.getString("users_id_number"));
				dataL.setUsers_phone(rs.getString("users_phone"));
				dataL.setCreate_time(rs.getString("create_time"));
				dataL.setUpdate_time(rs.getString("update_time"));
				dataList.add(dataL);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		return dataList;
	}
	
	public static void main(String[] args) throws Exception {
		UsersJDBCDAO dao = new UsersJDBCDAO();
		
		//新增
//		usersVO VO1 = new usersVO();
//		VO1.setUsers_id(21);
//		VO1.setUsers_mail("qqaa.com");
//		VO1.setUsers_pwd("543210");
//		VO1.setUsers_status(1);
//		VO1.setUsers_nickname("test");
//		VO1.setUsers_name("testadd");
//		VO1.setUesrs_sex(1);
//		VO1.setUesrs_birthday("20001231");
//		VO1.setUsers_id_number("A123456789");
//		VO1.setUser_phone("0909009090");
//		dao.insert(VO1);
		
		//修改
		UsersVO VO2 = new UsersVO();
		VO2.setUsers_id(21);
		VO2.setUsers_mail("qqaa.com");
		VO2.setUsers_pwd("543210");
		VO2.setUsers_status(1);
		VO2.setUsers_nickname("test");
		VO2.setUsers_name("testadd");
		VO2.setUsers_sex(1);
		VO2.setUsers_birthday("29991231");
		VO2.setUsers_id_number("Z123456789");
		VO2.setUsers_phone("0908090808");
		dao.update(VO2);

		//刪除
//		dao.delete(4);
		
		//查詢單行
//				usersVO users1 = dao.findByPK(21);
//				System.out.print("-- ");
//				System.out.print(users1.getUsers_id() + ",");
//				System.out.print(users1.getUsers_mail() + ",");
//				System.out.print(users1.getUsers_pwd() + ",");
//				System.out.print(users1.getUsers_status() + ",");
//				System.out.print(users1.getUsers_nickname() + ",");
//				System.out.print(users1.getUsers_name() + ",");
//				System.out.print(users1.getUesrs_sex() + ",");
//				System.out.print(users1.getUesrs_birthday() + ",");
//				System.out.print(users1.getUsers_id_number() + ",");
//				System.out.print(users1.getUser_phone() + ",");
//				System.out.println(" --");
		//查詢全部
				List<UsersVO> list = dao.getAll();
				System.out.println("------------------------------------------------------------");
				for (UsersVO users2 : list) {
					
					System.out.print(users2.getUsers_id() + ",");
					System.out.print(users2.getUsers_mail() + ",");
					System.out.print(users2.getUsers_pwd() + ",");
					System.out.print(users2.getUsers_status() + ",");
					System.out.print(users2.getUsers_nickname() + ",");
					System.out.print(users2.getUsers_name() + ",");
					System.out.print(users2.getUsers_sex() + ",");
					System.out.print(users2.getUsers_birthday() + ",");
					System.out.print(users2.getUsers_id_number() + ",");
					System.out.print(users2.getUsers_phone() + ",");
					System.out.println();
					
				}
				System.out.print("--------------------------------------------------------------");
	}

}
