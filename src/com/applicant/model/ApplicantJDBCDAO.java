package com.applicant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantJDBCDAO implements ApplicantDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO applicant (activities_id,users_id ) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT activities_id,users_id,registration_time FROM applicant order by registration_time desc";
	private static final String GET_ONE_STMT = 
		"SELECT activities_id,users_id,registration_time FROM applicant where (activities_id = ?) and (users_id = ?) ";
	private static final String DELETE = 
		"DELETE FROM applicant where  (activities_id = ?) and (users_id = ?)";
	private static final String UPDATE = 
		"UPDATE applicant set users_id = ? WHERE (activities_id = ?) and (users_id = ?)";

	
	
	
	
	
	
	
	@Override
	public void insert(ApplicantVO applicantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, applicantVO.getActivities_id());
			pstmt.setInt(2, applicantVO.getUsers_id());

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
	public void update(Integer activities_id, Integer users_id, Integer users_id1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(2, activities_id);
			pstmt.setInt(3, users_id);
			pstmt.setInt(1, users_id1);

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
	public void delete(Integer activities_id, Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, activities_id);
			pstmt.setInt(2, users_id);

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
	public ApplicantVO findByPrimaryKey(Integer activities_id, Integer users_id) {
		ApplicantVO applicantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activities_id);
			pstmt.setInt(2, users_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				applicantVO = new ApplicantVO();
				applicantVO.setActivities_id(rs.getInt("activities_id"));
				applicantVO.setUsers_id(rs.getInt("users_id"));
				applicantVO.setRegistration_time(rs.getTimestamp("registration_time"));
				
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
		return applicantVO;
	
	}

	@Override
	public List<ApplicantVO> getAll() {
		List<ApplicantVO> list = new ArrayList<ApplicantVO>();
		ApplicantVO applicantVO = null;

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
				applicantVO = new ApplicantVO();
				applicantVO.setActivities_id(rs.getInt("activities_id"));
				applicantVO.setUsers_id(rs.getInt("users_id"));
				applicantVO.setRegistration_time(rs.getTimestamp("registration_time"));

				list.add(applicantVO); // Store the row in the list
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
		ApplicantJDBCDAO dao = new ApplicantJDBCDAO();
		
		// 新增
		ApplicantVO applicantvo1 = new ApplicantVO();
		applicantvo1.setActivities_id(1);
		applicantvo1.setUsers_id(1);
		dao.insert(applicantvo1);
		System.out.println("-------insert success--------");
//		
		// 修改	
//		dao.update(2,2,1);
//		System.out.println("-------update success--------");

		// 刪除
//		dao.delete(1,1);
//		System.out.println("-------delete success--------");

		// 查詢
//		ApplicantVO applicantvo3 = dao.findByPrimaryKey(1,3);
//		System.out.print(applicantvo3.getActivities_id() + ",");
//		System.out.print(applicantvo3.getUsers_id()+ ",");
//		System.out.println(applicantvo3.getRegistration_time() );
//		System.out.println("---------findByPrimaryKey success------------");

		// 查詢
		List<ApplicantVO> list = dao.getAll();
		for (ApplicantVO aApplicant : list) {
			System.out.print(aApplicant.getActivities_id() + ",");
			System.out.print(aApplicant.getUsers_id() + ",");
			System.out.print(aApplicant.getRegistration_time() );
			System.out.println();
		}
		System.out.println("---------findAll success------------");
	}

}
