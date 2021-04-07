package com.admins.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.users.model.UsersDAO_interface;

import util.Util;

public class AdminsDAO implements AdminsDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO admins (admins_email, admins_name, admins_password, admins_sex, admins_authority, admins_position) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE admins SET admins_email =?, admins_name =?, admins_password =?, admins_sex =?, admins_authority =?, admins_position =? where admins_id = ?";
	private static final String DELETE_STMT = "DELETE FROM admins WHERE admins_id = ?";
	private static final String FIND_BY_PK = "SELECT * FROM admins WHERE admins_id = ?";
	private static final String GET_ALL = "SELECT * FROM admins"; 

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public void insert(AdminsVO adminsVO) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setInt(1, adminsVO.getAdmins_id());
			pstmt.setString(1, adminsVO.getAdmins_email());
			pstmt.setString(2, adminsVO.getAdmins_name());
			pstmt.setString(3, adminsVO.getAdmins_password());
			pstmt.setInt(4, adminsVO.getAdmins_sex());
			pstmt.setString(5, adminsVO.getAdmins_authority());
			pstmt.setString(6, adminsVO.getAdmins_position());
		
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
	
	public void update(AdminsVO adminsVO) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			
			pstmt.setString(1, adminsVO.getAdmins_email());
			pstmt.setString(2, adminsVO.getAdmins_name());
			pstmt.setString(3, adminsVO.getAdmins_password());
			pstmt.setInt(4, adminsVO.getAdmins_sex());
			pstmt.setString(5, adminsVO.getAdmins_authority());
			pstmt.setString(6, adminsVO.getAdmins_position());
			pstmt.setInt(7, adminsVO.getAdmins_id());
			
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
	
	public void delete(Integer admins_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, admins_id);
			
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
	
	public AdminsVO findByPK(Integer admins_id) {
		AdminsVO fBPK = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, admins_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fBPK = new AdminsVO();
				fBPK.setAdmins_id(rs.getInt("admins_id"));
				fBPK.setAdmins_email(rs.getString("admins_email"));
				fBPK.setAdmins_name(rs.getString("admins_name"));
				fBPK.setAdmins_password(rs.getString("admins_password"));
				fBPK.setAdmins_sex(rs.getInt("admins_sex"));
				fBPK.setAdmins_authority(rs.getString("admins_authority"));
				fBPK.setAdmins_position(rs.getString("admins_position"));
				fBPK.setAdmins_create_time(rs.getString("admins_create_time"));
				fBPK.setAdmins_edit_time(rs.getString("admins_edit_time"));

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
	
	
	public List<AdminsVO> getAll(){
		List<AdminsVO> dataList = new ArrayList<>();
		AdminsVO dataL = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dataL = new AdminsVO();
				dataL.setAdmins_id(rs.getInt("admins_id"));
				dataL.setAdmins_email(rs.getString("admins_email"));
				dataL.setAdmins_name(rs.getString("admins_name"));
				dataL.setAdmins_password(rs.getString("admins_password"));
				dataL.setAdmins_sex(rs.getInt("admins_sex"));
				dataL.setAdmins_authority(rs.getString("admins_authority"));
				dataL.setAdmins_position(rs.getString("admins_position"));
				dataL.setAdmins_create_time(rs.getString("admins_create_time"));
				dataL.setAdmins_edit_time(rs.getString("admins_edit_time"));
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
		AdminsJDBCDAO dao = new AdminsJDBCDAO();
		
		//新增
//		adminsVO VO1 = new adminsVO();
//		VO1.setAdmins_id(12);
//		VO1.setAdmins_email("bb@bb.com");
//		VO1.setAdmins_name("BB");
//		VO1.setAdmins_password("54321");
//		VO1.setAdmins_sex(2);
//		VO1.setAdmins_authority("root");
//		VO1.setAdmins_position(null);
////
//		dao.insert(VO1);
		
		//修改
//		adminsVO VO2 = new adminsVO();
//		VO2.setAdmins_id(12);
//		VO2.setAdmins_email("CC@bb.com");
//		VO2.setAdmins_name("CC");
//		VO2.setAdmins_password("987654321");
//		VO2.setAdmins_sex(1);
//		VO2.setAdmins_authority("root");
//		VO2.setAdmins_position(null);
//		dao.update(VO2);

		//刪除
//		dao.delete(12);
		
		//查詢單行
				AdminsVO admins1 = dao.findByPK(11);
				System.out.print("-- ");
				System.out.print(admins1.getAdmins_id() + ",");
				System.out.print(admins1.getAdmins_email() + ",");
				System.out.print(admins1.getAdmins_name() + ",");
				System.out.print(admins1.getAdmins_password() + ",");
				System.out.print(admins1.getAdmins_sex() + ",");
				System.out.print(admins1.getAdmins_authority() + ",");
				System.out.print(admins1.getAdmins_position() + ",");
				
				System.out.println(" --");
		//查詢全部
				List<AdminsVO> list = dao.getAll();
				System.out.println("------------------------------------------------------------");
				for (AdminsVO admins2 : list) {
					
					System.out.print(admins2.getAdmins_id() + ",");
					System.out.print(admins2.getAdmins_email() + ",");
					System.out.print(admins2.getAdmins_name() + ",");
					System.out.print(admins2.getAdmins_password() + ",");
					System.out.print(admins2.getAdmins_sex() + ",");
					System.out.print(admins2.getAdmins_authority() + ",");
					System.out.print(admins2.getAdmins_position() + ",");
					System.out.println();
					
				}
				System.out.print("--------------------------------------------------------------");
	}

}


