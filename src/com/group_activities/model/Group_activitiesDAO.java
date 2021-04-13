package com.group_activities.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.applicant.model.ApplicantVO;

public class Group_activitiesDAO implements Group_activitiesDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = 
				"INSERT INTO group_activities (trip_id,users_id,activities_state,activities_deadline,"
				+ "activities_max_users,activities_start,activities_end,activities_name,"
				+ "activities_description) VALUES (?,  ?, ?, ?, ?,?,?,?,?)";
			private static final String GET_ALL_STMT = 
				"SELECT * FROM group_activities order by activities_id";
			private static final String GET_ONE_STMT = 
				"SELECT * FROM group_activities where activities_id = ?";
			private static final String DELETE = 
				"DELETE FROM group_activities where activities_id = ?";
			private static final String UPDATE = 
				"UPDATE group_activities set trip_id= ?,activities_state= ?,activities_deadline= ?"
				+ ",activities_max_users= ?,activities_users_count= ?,activities_start= ?,activities_end= ?"
				+ ",activities_name= ?,activities_description= ? where activities_id = ?";
			private static final String GET_Applicant_ByTrip_STMT = "SELECT * FROM applicant where activities_id = ? order by registration_time desc";
		
		@Override
		public void insert(Group_activitiesVO group_activitiesVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, group_activitiesVO.getTrip_id());
				pstmt.setInt(2, group_activitiesVO.getUsers_id());
				pstmt.setInt(3, group_activitiesVO.getActivities_state());
				pstmt.setDate(4, group_activitiesVO.getActivities_deadline());
				pstmt.setInt(5, group_activitiesVO.getActivities_max_users());
				pstmt.setDate(6, group_activitiesVO.getActivities_start());
				pstmt.setDate(7, group_activitiesVO.getActivities_end());
				pstmt.setString(8, group_activitiesVO.getActivities_name());
				pstmt.setString(9, group_activitiesVO.getActivities_description());

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
		public void update(Group_activitiesVO group_activitiesVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, group_activitiesVO.getTrip_id());
				pstmt.setInt(2, group_activitiesVO.getActivities_state());
				pstmt.setDate(3, group_activitiesVO.getActivities_deadline());
				pstmt.setInt(4, group_activitiesVO.getActivities_max_users());
				pstmt.setInt(5, group_activitiesVO.getActivities_users_count());
				pstmt.setDate(6, group_activitiesVO.getActivities_start());
				pstmt.setDate(7, group_activitiesVO.getActivities_end());
				pstmt.setString(8, group_activitiesVO.getActivities_name());
				pstmt.setString(9, group_activitiesVO.getActivities_description());
				pstmt.setInt(10, group_activitiesVO.getActivities_id());

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
		public void delete(Integer activities_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, activities_id);

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
		public Group_activitiesVO findByPrimaryKey(Integer activities_id) {

			Group_activitiesVO group_activitiesVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, activities_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					group_activitiesVO = new Group_activitiesVO();
					group_activitiesVO.setActivities_id(rs.getInt("activities_id"));
					group_activitiesVO.setTrip_id(rs.getInt("trip_id"));
					group_activitiesVO.setUsers_id(rs.getInt("users_id"));
					group_activitiesVO.setActivities_create_time(rs.getTimestamp("activities_create_time"));
					group_activitiesVO.setActivities_state(rs.getInt("activities_state"));
					group_activitiesVO.setActivities_deadline(rs.getDate("activities_deadline"));
					group_activitiesVO.setActivities_max_users(rs.getInt("activities_max_users"));
					group_activitiesVO.setActivities_users_count(rs.getInt("activities_users_count"));
					group_activitiesVO.setActivities_start(rs.getDate("activities_start"));
					group_activitiesVO.setActivities_end(rs.getDate("activities_end"));
					group_activitiesVO.setActivities_name(rs.getString("activities_name"));
					group_activitiesVO.setActivities_description(rs.getString("activities_description"));
				}

				// Handle any driver errors
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
			return group_activitiesVO;
		}

		@Override
		public List<Group_activitiesVO> getAll() {
			List<Group_activitiesVO> list = new ArrayList<Group_activitiesVO>();
			Group_activitiesVO group_activitiesVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					group_activitiesVO = new Group_activitiesVO();
					group_activitiesVO.setActivities_id(rs.getInt("activities_id"));
					group_activitiesVO.setTrip_id(rs.getInt("trip_id"));
					group_activitiesVO.setUsers_id(rs.getInt("users_id"));
					group_activitiesVO.setActivities_create_time(rs.getTimestamp("activities_create_time"));
					group_activitiesVO.setActivities_state(rs.getInt("activities_state"));
					group_activitiesVO.setActivities_deadline(rs.getDate("activities_deadline"));
					group_activitiesVO.setActivities_max_users(rs.getInt("activities_max_users"));
					group_activitiesVO.setActivities_users_count(rs.getInt("activities_users_count"));
					group_activitiesVO.setActivities_start(rs.getDate("activities_start"));
					group_activitiesVO.setActivities_end(rs.getDate("activities_end"));
					group_activitiesVO.setActivities_name(rs.getString("activities_name"));
					group_activitiesVO.setActivities_description(rs.getString("activities_description"));

					list.add(group_activitiesVO); // Store the row in the list
				}

				// Handle any driver errors
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

		@Override
		public Set<ApplicantVO> getApplicantByTrip(Integer activities_id) {
			Set<ApplicantVO> set = new LinkedHashSet<ApplicantVO>();
			ApplicantVO applicantVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_Applicant_ByTrip_STMT);
				pstmt.setInt(1, activities_id);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					applicantVO = new ApplicantVO();
					applicantVO.setActivities_id(rs.getInt("activities_id"));
					applicantVO.setUsers_id(rs.getInt("users_id"));
					applicantVO.setRegistration_time(rs.getTimestamp("registration_time"));

					set.add(applicantVO); // Store the row in the vector
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
			return set;
		}
}
