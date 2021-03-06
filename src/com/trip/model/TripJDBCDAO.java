package com.trip.model;

import java.util.*;


import java.sql.*;

public class TripJDBCDAO implements TripDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip (users_id,last_editor,trip_state,read_authority,"
			+ "edit_authority,trip_area,trip_start,trip_end,trip_name,trip_description,"
			+ "trip_type,trip_tot_cost,place_weather) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM trip order by trip_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM trip where trip_id = ?";
		private static final String DELETE = 
			"DELETE FROM trip where trip_id = ?";
		private static final String UPDATE = 
			"UPDATE trip set users_id= ?,last_editor= ?,trip_state= ?,read_authority= ?,"
			+ "edit_authority= ?,trip_area= ?,trip_start= ?,trip_end= ?,trip_name= ?,trip_description= ?,"
			+ " trip_type= ?,trip_tot_cost= ?,place_weather= ? where trip_id = ?";
		

	@Override
	public void insert(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tripVO.getUsers_id());
			pstmt.setInt(2, tripVO.getLast_editor());
			pstmt.setInt(3, tripVO.getTrip_state());
			pstmt.setInt(4, tripVO.getRead_authority());
			pstmt.setInt(5, tripVO.getEdit_authority());
			pstmt.setString(6, tripVO.getTrip_area());
			pstmt.setDate(7, tripVO.getTrip_start());
			pstmt.setDate(8, tripVO.getTrip_end());
			pstmt.setString(9, tripVO.getTrip_name());
			pstmt.setString(10, tripVO.getTrip_description());
			pstmt.setString(11, tripVO.getTrip_type());
			pstmt.setInt(12, tripVO.getTrip_tot_cost());
			pstmt.setString(13, tripVO.getPlace_weather());


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
	public void update(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tripVO.getUsers_id());
			pstmt.setInt(2, tripVO.getLast_editor());
			pstmt.setInt(3, tripVO.getTrip_state());
			pstmt.setInt(4, tripVO.getRead_authority());
			pstmt.setInt(5, tripVO.getEdit_authority());
			pstmt.setString(6, tripVO.getTrip_area());
			pstmt.setDate(7, tripVO.getTrip_start());
			pstmt.setDate(8, tripVO.getTrip_end());
			pstmt.setString(9, tripVO.getTrip_name());
			pstmt.setString(10, tripVO.getTrip_description());
			pstmt.setString(11, tripVO.getTrip_type());
			pstmt.setInt(12, tripVO.getTrip_tot_cost());
			pstmt.setString(13, tripVO.getPlace_weather());
			pstmt.setInt(14, tripVO.getTrip_id());

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
	public void delete(Integer trip_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trip_id);

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
	public TripVO findByPrimaryKey(Integer trip_id) {
		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trip_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getInt("trip_id"));
				 tripVO.setUsers_id(rs.getInt("Users_id"));
				 tripVO.setTrip_create_time(rs.getTimestamp("Trip_create_time"));
				 tripVO.setLast_edit_time(rs.getTimestamp("Last_edit_time"));
				 tripVO.setLast_editor(rs.getInt("Last_editor"));
				 tripVO.setTrip_state(rs.getInt("Trip_state"));
				 tripVO.setTrip_like(rs.getInt("Trip_like"));
				 tripVO.setTrip_look(rs.getInt("Trip_look"));
				 tripVO.setRead_authority(rs.getInt("Read_authority"));
				 tripVO.setEdit_authority(rs.getInt("Edit_authority"));
				 tripVO.setTrip_area(rs.getString("Trip_area"));
				 tripVO.setTrip_start(rs.getDate("Trip_start"));
				 tripVO.setTrip_end(rs.getDate("Trip_end"));
				 tripVO.setTrip_name(rs.getString("trip_name"));
				 tripVO.setTrip_description(rs.getString("trip_description"));
				 tripVO.setTrip_type(rs.getString("trip_type"));
				 tripVO.setTrip_tot_cost(rs.getInt("trip_tot_cost"));
				 tripVO.setPlace_weather(rs.getString("place_weather"));


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
		return tripVO;
	}



	@Override
	public List<TripVO> getAll() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

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
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getInt("trip_id"));
				 tripVO.setUsers_id(rs.getInt("Users_id"));
				 tripVO.setTrip_create_time(rs.getTimestamp("Trip_create_time"));
				 tripVO.setLast_edit_time(rs.getTimestamp("Last_edit_time"));
				 tripVO.setLast_editor(rs.getInt("Last_editor"));
				 tripVO.setTrip_state(rs.getInt("Trip_state"));
				 tripVO.setTrip_like(rs.getInt("Trip_like"));
				 tripVO.setTrip_look(rs.getInt("Trip_look"));
				 tripVO.setRead_authority(rs.getInt("Read_authority"));
				 tripVO.setEdit_authority(rs.getInt("Edit_authority"));
				 tripVO.setTrip_area(rs.getString("Trip_area"));
				 tripVO.setTrip_start(rs.getDate("Trip_start"));
				 tripVO.setTrip_end(rs.getDate("Trip_end"));
				 tripVO.setTrip_name(rs.getString("trip_name"));
				 tripVO.setTrip_description(rs.getString("trip_description"));
				 tripVO.setTrip_type(rs.getString("trip_type"));
				 tripVO.setTrip_tot_cost(rs.getInt("trip_tot_cost"));
				 tripVO.setPlace_weather(rs.getString("place_weather"));

				
				
				list.add(tripVO); // Store the row in the list
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
		TripJDBCDAO dao = new TripJDBCDAO();

		// 新增
//		  TripVO  tripVO1 = new TripVO();
//		  tripVO1.setUsers_id(3);
//		  tripVO1.setLast_editor(4);
//		  tripVO1.setTrip_state(1);
//		  tripVO1.setRead_authority(1);
//		  tripVO1.setEdit_authority(1);
//		  tripVO1.setTrip_area("台灣");
//		  tripVO1.setTrip_start(java.sql.Date.valueOf("2021-03-03"));
//		  tripVO1.setTrip_end(java.sql.Date.valueOf("2021-03-10"));
//		  tripVO1.setTrip_name("Taiwango");
//		  tripVO1.setTrip_description("環島之旅");
//		  tripVO1.setTrip_type("環島");
//		  tripVO1.setTrip_tot_cost(10000);
//		  tripVO1.setPlace_weather("晴天");
//
//		dao.insert(tripVO1);
//		System.out.println("-------insert success--------");
//		
//		// 修改
//		TripVO  tripVO2 = new TripVO();
//		  tripVO2.setTrip_id(5);
//		  tripVO2.setUsers_id(4);
//		  tripVO2.setLast_editor(4);
//		  tripVO2.setTrip_state(1);
//		  tripVO2.setRead_authority(1);
//		  tripVO2.setEdit_authority(1);
//		  tripVO2.setTrip_area("台灣");
//		  tripVO2.setTrip_start(java.sql.Date.valueOf("2021-03-03"));
//		  tripVO2.setTrip_end(java.sql.Date.valueOf("2021-03-10"));
//		  tripVO2.setTrip_name("Taiwango");
//		  tripVO2.setTrip_description("環島之旅");
//		  tripVO2.setTrip_type("環島");
//		  tripVO2.setTrip_tot_cost(10000);
//		  tripVO2.setPlace_weather("晴天");
//		dao.update(tripVO2);
//		System.out.println("-------update success--------");
//		

//		// 刪除
//		dao.delete(4);
//		System.out.println("-------delete success--------");

//		// 查詢
//		TripVO  tripVO3 = dao.findByPrimaryKey(3);
//		  System.out.print( tripVO3.getTrip_id() + ",");
//		  System.out.print( tripVO3.getUsers_id() + ",");
//		  System.out.print( tripVO3.getTrip_create_time() + ",");
//		  System.out.print( tripVO3.getLast_edit_time() + ",");
//		  System.out.print( tripVO3.getLast_editor() + ",");
//		  System.out.print( tripVO3.getTrip_state() + ",");
//		  System.out.print( tripVO3.getTrip_like() + ",");
//		  System.out.print( tripVO3.getTrip_look() + ",");
//		  System.out.print( tripVO3.getRead_authority() + ",");
//		  System.out.print( tripVO3.getEdit_authority() + ",");
//		  System.out.print( tripVO3.getTrip_area() + ",");
//		  System.out.print( tripVO3.getTrip_start() + ",");
//		  System.out.print( tripVO3.getTrip_end() + ",");
//		  System.out.print( tripVO3.getTrip_name() + ",");
//		  System.out.print( tripVO3.getTrip_description() + ",");
//		  System.out.print( tripVO3.getTrip_type() + ",");
//		  System.out.print( tripVO3.getTrip_tot_cost() + ",");
//		  System.out.println( tripVO3.getPlace_weather());
//	
//
//		System.out.println("---------findByPrimaryKey success------------");

		// 查詢
		List<TripVO> list = dao.getAll();
		for (TripVO aTrip : list) {
			  System.out.print( aTrip.getTrip_id() + ",");
			  System.out.print( aTrip.getUsers_id() + ",");
			  System.out.print( aTrip.getTrip_create_time() + ",");
			  System.out.print( aTrip.getLast_edit_time() + ",");
			  System.out.print( aTrip.getLast_editor() + ",");
			  System.out.print( aTrip.getTrip_state() + ",");
			  System.out.print( aTrip.getTrip_like() + ",");
			  System.out.print( aTrip.getTrip_look() + ",");
			  System.out.print( aTrip.getRead_authority() + ",");
			  System.out.print( aTrip.getEdit_authority() + ",");
			  System.out.print( aTrip.getTrip_area() + ",");
			  System.out.print( aTrip.getTrip_start() + ",");
			  System.out.print( aTrip.getTrip_end() + ",");
			  System.out.print( aTrip.getTrip_name() + ",");
			  System.out.print( aTrip.getTrip_description() + ",");
			  System.out.print( aTrip.getTrip_type() + ",");
			  System.out.print( aTrip.getTrip_tot_cost() + ",");
			  System.out.print( aTrip.getPlace_weather());
			  System.out.println();
		}
		System.out.println("---------findAll success------------");
		

	}

}
