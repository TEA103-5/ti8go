package com.trip_detail.model;

import java.util.*;


import java.sql.*;


public class Trip_detailJDBCDAO implements Trip_detailDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,"
			+ "trip_content,place_id,trip_start_time,trip_end_time,trip_remarks,trip_cost) "
			+ "VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM trip_detail order by trip_id,trip_day,trip_sort";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM trip_detail where trip_detail_id = ?";
		private static final String DELETE = 
			"DELETE FROM trip_detail where trip_detail_id = ?";
		private static final String UPDATE = 
			"UPDATE trip_detail set trip_id=?,trip_day= ?,trip_sort= ?,trip_detail_type= ?,trip_content= ?"
			+ ",place_id= ?,trip_start_time= ?,trip_end_time= ?,trip_remarks= ?,trip_cost= ? where trip_detail_id= ?";
		
	@Override
	public void insert(Trip_detailVO trip_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
			pstmt.setInt(6, trip_detailVO.getPlace_id());
			pstmt.setTime(7, trip_detailVO.getTrip_start_time());
			pstmt.setTime(8, trip_detailVO.getTrip_end_time());
			pstmt.setString(9, trip_detailVO.getTrip_remarks());
			pstmt.setInt(10, trip_detailVO.getTrip_cost());

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
	public void update(Trip_detailVO trip_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
			pstmt.setInt(6, trip_detailVO.getPlace_id());
			pstmt.setTime(7, trip_detailVO.getTrip_start_time());
			pstmt.setTime(8, trip_detailVO.getTrip_end_time());
			pstmt.setString(9, trip_detailVO.getTrip_remarks());
			pstmt.setInt(10, trip_detailVO.getTrip_cost());
			pstmt.setInt(11, trip_detailVO.getTrip_detail_id());

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
	public void delete(Integer trip_detail_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trip_detail_id);

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
	public Trip_detailVO findByPrimaryKey(Integer trip_detail_id) {
		Trip_detailVO  trip_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trip_detail_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				 trip_detailVO = new Trip_detailVO();
				 trip_detailVO.setTrip_detail_id(rs.getInt("trip_detail_id"));
				 trip_detailVO.setTrip_id(rs.getInt("trip_id"));
				 trip_detailVO.setTrip_day(rs.getInt("trip_day"));
				 trip_detailVO.setTrip_sort(rs.getInt("trip_sort"));
				 trip_detailVO.setTrip_detail_type(rs.getString("trip_detail_type"));
				 trip_detailVO.setTrip_content(rs.getString("trip_content"));
				 trip_detailVO.setPlace_id(rs.getInt("place_id"));
				 trip_detailVO.setTrip_start_time(rs.getTime("trip_start_time"));
				 trip_detailVO.setTrip_end_time(rs.getTime("trip_end_time"));
				 trip_detailVO.setTrip_remarks(rs.getString("trip_remarks"));
				 trip_detailVO.setTrip_cost(rs.getInt("trip_cost"));
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
		return  trip_detailVO;
	}

	@Override
	public List<Trip_detailVO> getAll() {
		List<Trip_detailVO> list = new ArrayList<Trip_detailVO>();
		Trip_detailVO  trip_detailVO = null;

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
				trip_detailVO = new Trip_detailVO();
				 trip_detailVO.setTrip_detail_id(rs.getInt("trip_detail_id"));
				 trip_detailVO.setTrip_id(rs.getInt("trip_id"));
				 trip_detailVO.setTrip_day(rs.getInt("trip_day"));
				 trip_detailVO.setTrip_sort(rs.getInt("trip_sort"));
				 trip_detailVO.setTrip_detail_type(rs.getString("trip_detail_type"));
				 trip_detailVO.setTrip_content(rs.getString("trip_content"));
				 trip_detailVO.setPlace_id(rs.getInt("place_id"));
				 trip_detailVO.setTrip_start_time(rs.getTime("trip_start_time"));
				 trip_detailVO.setTrip_end_time(rs.getTime("trip_end_time"));
				 trip_detailVO.setTrip_remarks(rs.getString("trip_remarks"));
				 trip_detailVO.setTrip_cost(rs.getInt("trip_cost"));

				list.add(trip_detailVO); // Store the row in the list
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
		Trip_detailJDBCDAO dao = new Trip_detailJDBCDAO();

		// 新增
//		Trip_detailVO  trip_detailVO1 = new Trip_detailVO();
//		  trip_detailVO1.setTrip_id(3);
//		  trip_detailVO1.setTrip_day(1);
//		  trip_detailVO1.setTrip_sort(1);
//		  trip_detailVO1.setTrip_detail_type("其他");
//		  trip_detailVO1.setTrip_content("吃早餐");
//		  trip_detailVO1.setPlace_id(2);
//		  trip_detailVO1.setTrip_start_time(java.sql.Time.valueOf("07:00:00"));
//		  trip_detailVO1.setTrip_end_time(java.sql.Time.valueOf("08:00:00"));
//		  trip_detailVO1.setTrip_remarks("請在家各自出發");
//		  trip_detailVO1.setTrip_cost(300);
//		dao.insert( trip_detailVO1);
//		System.out.println("-------insert success--------");
//		
//		// 修改
//		Trip_detailVO  trip_detailVO2 = new Trip_detailVO();
//		 trip_detailVO2.setTrip_detail_id(5);
//		  trip_detailVO2.setTrip_id(2);
//		  trip_detailVO2.setTrip_day(2);
//		  trip_detailVO2.setTrip_sort(1);
//		  trip_detailVO2.setTrip_detail_type("餐廳");
//		  trip_detailVO2.setTrip_content("麥當勞");
//		  trip_detailVO2.setPlace_id(3);
//		  trip_detailVO2.setTrip_start_time(java.sql.Time.valueOf("08:00:00"));
//		  trip_detailVO2.setTrip_end_time(java.sql.Time.valueOf("09:00:00"));
//		  trip_detailVO2.setTrip_remarks("請各位要吃飽哦");
//		  trip_detailVO2.setTrip_cost(400);
//		dao.update( trip_detailVO2);
//		System.out.println("-------update success--------");
//		

//		// 刪除
//		dao.delete(8);
//		System.out.println("-------delete success--------");

//		// 查詢
//		Trip_detailVO  trip_detailVO3 = dao.findByPrimaryKey(10);
//		System.out.print( trip_detailVO3.getTrip_detail_id() + ",");
//		System.out.print( trip_detailVO3.getTrip_id() + ",");
//		System.out.print( trip_detailVO3.getTrip_day() + ",");
//		System.out.print( trip_detailVO3.getTrip_sort() + ",");
//		System.out.print( trip_detailVO3.getTrip_detail_type() + ",");
//		System.out.print( trip_detailVO3.getTrip_content() + ",");
//		System.out.print( trip_detailVO3.getPlace_id()+ ",");
//		System.out.print( trip_detailVO3.getTrip_start_time()+ ",");
//		System.out.print( trip_detailVO3.getTrip_end_time()+ ",");
//		System.out.print( trip_detailVO3.getTrip_remarks()+ ",");
//		System.out.println( trip_detailVO3.getTrip_cost());	
//		System.out.println("---------findByPrimaryKey success------------");

		// 查詢
		List<Trip_detailVO> list = dao.getAll();
		for (Trip_detailVO aTrip_detail : list) {
				System.out.print( aTrip_detail.getTrip_detail_id() + ",");
			System.out.print( aTrip_detail.getTrip_id() + ",");
			System.out.print( aTrip_detail.getTrip_day() + ",");
			System.out.print( aTrip_detail.getTrip_sort() + ",");
			System.out.print( aTrip_detail.getTrip_detail_type() + ",");
			System.out.print( aTrip_detail.getTrip_content() + ",");
			System.out.print( aTrip_detail.getPlace_id()+ ",");
			System.out.print( aTrip_detail.getTrip_start_time()+ ",");
			System.out.print( aTrip_detail.getTrip_end_time()+ ",");
			System.out.print( aTrip_detail.getTrip_remarks()+ ",");
			System.out.print( aTrip_detail.getTrip_cost());	
			System.out.println();
		}
		System.out.println("---------findAll success------------");
		

	}

	@Override
	public void insert2(Trip_detailVO trip_detailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
			pstmt.setInt(6, trip_detailVO.getPlace_id());
			pstmt.setTime(7, trip_detailVO.getTrip_start_time());
			pstmt.setTime(8, trip_detailVO.getTrip_end_time());
			pstmt.setString(9, trip_detailVO.getTrip_remarks());
			pstmt.setInt(10, trip_detailVO.getTrip_cost());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}

}
