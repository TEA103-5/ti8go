package com.trip_detail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Trip_detailDAO implements Trip_detailDAO_interface{
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
			"INSERT INTO trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,"
			+ "trip_content,trip_start_time,trip_end_time,trip_remarks,trip_cost,place_id) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM trip_detail order by trip_detail_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM trip_detail where trip_detail_id = ?";
		private static final String DELETE = 
			"DELETE FROM trip_detail where trip_detail_id = ?";
		private static final String UPDATE = 
			"UPDATE trip_detail set trip_id=?,trip_day= ?,trip_sort= ?,trip_detail_type= ?,trip_content= ?"
			+ ",trip_start_time= ?,trip_end_time= ?,trip_remarks= ?,trip_cost= ? where trip_detail_id= ?";
		
	@Override
	public void insert(Trip_detailVO trip_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
			pstmt.setTime(6, trip_detailVO.getTrip_start_time());
			pstmt.setTime(7, trip_detailVO.getTrip_end_time());
			pstmt.setString(8, trip_detailVO.getTrip_remarks());
			pstmt.setInt(9, trip_detailVO.getTrip_cost());
			pstmt.setInt(10, trip_detailVO.getPlace_id());

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
	public void update(Trip_detailVO trip_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
//			pstmt.setInt(6, trip_detailVO.getPlace_id());
			pstmt.setTime(6, trip_detailVO.getTrip_start_time());
			pstmt.setTime(7, trip_detailVO.getTrip_end_time());
			pstmt.setString(8, trip_detailVO.getTrip_remarks());
			pstmt.setInt(9, trip_detailVO.getTrip_cost());
			pstmt.setInt(10, trip_detailVO.getTrip_detail_id());

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
	public void delete(Integer trip_detail_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trip_detail_id);

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
	public Trip_detailVO findByPrimaryKey(Integer trip_detail_id) {

		Trip_detailVO  trip_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
		return trip_detailVO;
	}

	@Override
	public List<Trip_detailVO> getAll() {
		List<Trip_detailVO> list = new ArrayList<Trip_detailVO>();
		Trip_detailVO  trip_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public void insert2(Trip_detailVO trip_detailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, trip_detailVO.getTrip_id());
			pstmt.setInt(2, trip_detailVO.getTrip_day());
			pstmt.setInt(3, trip_detailVO.getTrip_sort());
			pstmt.setString(4, trip_detailVO.getTrip_detail_type());
			pstmt.setString(5, trip_detailVO.getTrip_content());
//			pstmt.setInt(6, trip_detailVO.getPlace_id());
			pstmt.setTime(6, trip_detailVO.getTrip_start_time());
			pstmt.setTime(7, trip_detailVO.getTrip_end_time());
			pstmt.setString(8, trip_detailVO.getTrip_remarks());
			pstmt.setInt(9, trip_detailVO.getTrip_cost());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-trip_detail");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
}
