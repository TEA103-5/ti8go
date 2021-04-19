package com.users.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class UsersDAO implements UsersDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO users (users_id, users_mail, users_pwd, users_identi_status, users_nickname, users_name, users_sex, users_birthday, users_id_number, users_pic, users_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			
			pstmt.setInt(1, usersVO.getUsers_id());
			pstmt.setString(2, usersVO.getUsers_mail());
			pstmt.setString(3, usersVO.getUsers_pwd());
			pstmt.setInt(4, usersVO.getUsers_status());
			pstmt.setString(5, usersVO.getUsers_nickname());
			pstmt.setString(6, usersVO.getUsers_name());
			pstmt.setInt(7, usersVO.getUsers_sex());
			pstmt.setString(8, usersVO.getUsers_birthday());
			pstmt.setString(9, usersVO.getUsers_id_number());
		// 2. setBytes
				byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
			pstmt.setBytes(10, pic);
			pstmt.setString(11, usersVO.getUsers_phone());
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
			byte[] pic = getPictureByteArray("items/user.png");
			pstmt.setBytes(9, pic);
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


}
