package com.applicant.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ApplicantDAO implements ApplicantDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, applicantVO.getActivities_id());
				pstmt.setInt(2, applicantVO.getUsers_id());

				pstmt.executeUpdate();


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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(2, activities_id);
				pstmt.setInt(3, users_id);
				pstmt.setInt(1, users_id1);

				pstmt.executeUpdate();

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, activities_id);
				pstmt.setInt(2, users_id);

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, activities_id);
				pstmt.setInt(2, users_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// deptVO 也稱為 Domain objects
					applicantVO = new ApplicantVO();
					applicantVO.setActivities_id(rs.getInt("activities_id"));
					applicantVO.setUsers_id(rs.getInt("users_id"));
					applicantVO.setRegistration_time(rs.getTimestamp("registration_time"));
					
				}

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					applicantVO = new ApplicantVO();
					applicantVO.setActivities_id(rs.getInt("activities_id"));
					applicantVO.setUsers_id(rs.getInt("users_id"));
					applicantVO.setRegistration_time(rs.getTimestamp("registration_time"));

					list.add(applicantVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
