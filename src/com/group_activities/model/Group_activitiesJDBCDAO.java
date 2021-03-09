package com.group_activities.model;

import java.util.*;
import java.sql.*;

public class Group_activitiesJDBCDAO implements Group_activitiesDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO group_activities (trip_id,users_id,activities_state,activities_deadline,"
			+ "activities_max_users,activities_users_count,activities_start,activities_end,activities_name,"
			+ "activities_description) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM group_activities order by activities_create_time desc";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM group_activities where activities_id = ?";
		private static final String DELETE = 
			"DELETE FROM group_activities where activities_id = ?";
		private static final String UPDATE = 
			"UPDATE group_activities set trip_id= ?,users_id= ?,activities_state= ?,activities_deadline= ?"
			+ ",activities_max_users= ?,activities_users_count= ?,activities_start= ?,activities_end= ?"
			+ ",activities_name= ?,activities_description= ? where activities_id = ?";


	@Override
	public void insert(Group_activitiesVO group_activitiesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, group_activitiesVO.getTrip_id());
			pstmt.setInt(2, group_activitiesVO.getUsers_id());
			pstmt.setInt(3, group_activitiesVO.getActivities_state());
			pstmt.setDate(4, group_activitiesVO.getActivities_deadline());
			pstmt.setInt(5, group_activitiesVO.getActivities_max_users());
			pstmt.setInt(6, group_activitiesVO.getActivities_users_count());
			pstmt.setDate(7, group_activitiesVO.getActivities_start());
			pstmt.setDate(8, group_activitiesVO.getActivities_end());
			pstmt.setString(9, group_activitiesVO.getActivities_name());
			pstmt.setString(10, group_activitiesVO.getActivities_description());

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
	public void update(Group_activitiesVO group_activitiesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, group_activitiesVO.getTrip_id());
			pstmt.setInt(2, group_activitiesVO.getUsers_id());
			pstmt.setInt(3, group_activitiesVO.getActivities_state());
			pstmt.setDate(4, group_activitiesVO.getActivities_deadline());
			pstmt.setInt(5, group_activitiesVO.getActivities_max_users());
			pstmt.setInt(6, group_activitiesVO.getActivities_users_count());
			pstmt.setDate(7, group_activitiesVO.getActivities_start());
			pstmt.setDate(8, group_activitiesVO.getActivities_end());
			pstmt.setString(9, group_activitiesVO.getActivities_name());
			pstmt.setString(10, group_activitiesVO.getActivities_description());
			pstmt.setInt(11, group_activitiesVO.getActivities_id());

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
	public void delete(Integer activities_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, activities_id);

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
	public Group_activitiesVO findByPrimaryKey(Integer activities_id) {
		Group_activitiesVO group_activitiesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
		Group_activitiesJDBCDAO dao = new Group_activitiesJDBCDAO();

		// 新增
//		Group_activitiesVO group_activitiesVO1 = new Group_activitiesVO();
//		group_activitiesVO1.setTrip_id(1);
//		group_activitiesVO1.setUsers_id(2);
//		group_activitiesVO1.setActivities_state(1);
//		group_activitiesVO1.setActivities_deadline(java.sql.Date.valueOf("2021-05-01"));
//		group_activitiesVO1.setActivities_max_users(30);
//		group_activitiesVO1.setActivities_users_count(4);
//		group_activitiesVO1.setActivities_start(java.sql.Date.valueOf("2021-06-01"));
//		group_activitiesVO1.setActivities_end(java.sql.Date.valueOf("2021-06-01"));
//		group_activitiesVO1.setActivities_name("台北一日遊");
//		group_activitiesVO1.setActivities_description("台北真好玩");
//		dao.insert(group_activitiesVO1);
//		System.out.println("-------insert success--------");
//		
//		// 修改
//		Group_activitiesVO group_activitiesVO2 = new Group_activitiesVO();
//		group_activitiesVO2.setTrip_id(1);
//		group_activitiesVO2.setUsers_id(2);
//		group_activitiesVO2.setActivities_state(1);
//		group_activitiesVO2.setActivities_deadline(java.sql.Date.valueOf("2021-05-01"));
//		group_activitiesVO2.setActivities_max_users(30);
//		group_activitiesVO2.setActivities_users_count(4);
//		group_activitiesVO2.setActivities_start(java.sql.Date.valueOf("2021-06-02"));
//		group_activitiesVO2.setActivities_end(java.sql.Date.valueOf("2021-06-02"));
//		group_activitiesVO2.setActivities_name("Tivagogo");
//		group_activitiesVO2.setActivities_description("台北真好玩");
//		group_activitiesVO2.setActivities_id(4);
//		dao.update(group_activitiesVO2);
//		System.out.println("-------update success--------");

//		// 刪除
//		dao.delete(4);
//		System.out.println("-------delete success--------");

//		// 查詢
		Group_activitiesVO group_activitiesVO3 = dao.findByPrimaryKey(1);
		System.out.print(group_activitiesVO3.getActivities_id() + ",");
		System.out.print(group_activitiesVO3.getTrip_id() + ",");
		System.out.print(group_activitiesVO3.getUsers_id() + ",");
		System.out.print(group_activitiesVO3.getActivities_create_time() + ",");
		System.out.print(group_activitiesVO3.getActivities_state() + ",");
		System.out.print(group_activitiesVO3.getActivities_deadline() + ",");
		System.out.print(group_activitiesVO3.getActivities_max_users()+ ",");
		System.out.print(group_activitiesVO3.getActivities_users_count()+ ",");
		System.out.print(group_activitiesVO3.getActivities_start()+ ",");
		System.out.print(group_activitiesVO3.getActivities_end()+ ",");
		System.out.print(group_activitiesVO3.getActivities_name()+ ",");
		System.out.println(group_activitiesVO3.getActivities_description());	
//		System.out.println("---------findByPrimaryKey success------------");

		// 查詢
		List<Group_activitiesVO> list = dao.getAll();
		for (Group_activitiesVO aGroup_activities : list) {
			System.out.print(aGroup_activities.getActivities_id() + ",");
			System.out.print(aGroup_activities.getTrip_id() + ",");
			System.out.print(aGroup_activities.getUsers_id() + ",");
			System.out.print(aGroup_activities.getActivities_create_time() + ",");
			System.out.print(aGroup_activities.getActivities_state() + ",");
			System.out.print(aGroup_activities.getActivities_deadline() + ",");
			System.out.print(aGroup_activities.getActivities_max_users()+ ",");
			System.out.print(aGroup_activities.getActivities_users_count()+ ",");
			System.out.print(aGroup_activities.getActivities_start()+ ",");
			System.out.print(aGroup_activities.getActivities_end()+ ",");
			System.out.print(aGroup_activities.getActivities_name()+ ",");
			System.out.print(aGroup_activities.getActivities_description());	
			System.out.println();
		}
		System.out.println("---------findAll success------------");

	}
}
