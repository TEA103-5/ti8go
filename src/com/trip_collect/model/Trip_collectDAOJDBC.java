package com.trip_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.place_collect.model.Place_collectVO;

public class Trip_collectDAOJDBC implements Trip_collectDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO trip_collect (trip_id , users_id) VALUES (? , ?)";
	private static final String GET_ALL_STMT = "SELECT trip_id , users_id , trip_collect_time FROM trip_collect order by users_id";
	private static final String GET_ONE_STMT = "SELECT trip_id , users_id , trip_collect_time FROM trip_collect where trip_id = ? and users_id = ?";
	private static final String DELETE = "DELETE FROM trip_collect where trip_id=? and users_id = ?";
	// 收藏不會用更新的方式去變更 , 所以更新指令只是練習用的,條件寫死在update方法裡
//	private static final String UPDATE = "UPDATE trip_collect set trip_id=? , users_id=? where trip_id=? and users_id = ?";

	private static final String GET_ALL_BY_ID_STMT = "SELECT trip_id , users_id , trip_collect_time FROM trip_collect where users_id = ?";
	
	@Override
	public void insert(Trip_collectVO trip_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, trip_collectVO.getTrip_id(), Types.INTEGER);
			pstmt.setObject(2, trip_collectVO.getUsers_id(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

//	@Override
//	public void update(Trip_collectVO trip_collectVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setObject(1, trip_collectVO.getTrip_id(), Types.INTEGER);
//			pstmt.setObject(2, trip_collectVO.getUsers_id(), Types.INTEGER);
//			pstmt.setObject(3, 1, Types.INTEGER);
//			pstmt.setObject(4, 1, Types.INTEGER);
//			
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	@Override
	public void delete(Integer trip_id , Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setObject(1, trip_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Trip_collectVO findByPrimaryKey(Integer trip_id , Integer users_id) {
		Trip_collectVO trip_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, trip_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trip_collectVO = new Trip_collectVO();
				
				trip_collectVO.setTrip_id(rs.getInt("trip_id"));
				trip_collectVO.setUsers_id(rs.getInt("users_id"));
				trip_collectVO.setTrip_collect_time(rs.getTimestamp("trip_collect_time"));
				
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
		return trip_collectVO;
	}

	@Override
	public List<Trip_collectVO> getAll() {
		List<Trip_collectVO> list = new ArrayList<Trip_collectVO>();
		Trip_collectVO trip_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trip_collectVO = new Trip_collectVO();
				
				trip_collectVO.setTrip_id(rs.getInt("trip_id"));
				trip_collectVO.setUsers_id(rs.getInt("users_id"));
				trip_collectVO.setTrip_collect_time(rs.getTimestamp("trip_collect_time"));
				
				list.add(trip_collectVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
	@Override
	public List<Trip_collectVO> getAllByPrimaryKey(Integer users_id) {
		List<Trip_collectVO> list = new ArrayList<Trip_collectVO>();
		Trip_collectVO trip_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_ID_STMT);
			pstmt.setObject(1, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trip_collectVO = new Trip_collectVO();
				
				trip_collectVO.setTrip_id(rs.getInt("trip_id"));
				trip_collectVO.setUsers_id(rs.getInt("users_id"));
				trip_collectVO.setTrip_collect_time(rs.getTimestamp("trip_collect_time"));
				
				list.add(trip_collectVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String args[]) {
		Trip_collectDAOJDBC dao = new Trip_collectDAOJDBC();

////		// 新增
//		Trip_collectVO trip_collectVO1 = new Trip_collectVO();
//		trip_collectVO1.setTrip_id(2);
//		trip_collectVO1.setUsers_id(10);
//		dao.insert(trip_collectVO1);
//
//		// 修改
//		Trip_collectVO trip_collectVO2 = new Trip_collectVO();
//		trip_collectVO2.setTrip_id(1);
//		trip_collectVO2.setUsers_id(2);
//		dao.update(trip_collectVO2);
//		
//		// 刪除
//		dao.delete(2 , 10);
		
		// 查詢一筆
		Trip_collectVO trip_collectVO3 = dao.findByPrimaryKey(1 , 1);
		
		System.out.print(trip_collectVO3.getTrip_id() + ",");
		System.out.print(trip_collectVO3.getUsers_id() + ",");
		System.out.println(trip_collectVO3.getTrip_collect_time() );
		System.out.println("---------------------");
//		
//		// 查詢全部
		List<Trip_collectVO> list = dao.getAll();
		
		for(Trip_collectVO aTrip_collect : list) {
			
			System.out.print(aTrip_collect.getTrip_id() + ",");
			System.out.print(aTrip_collect.getUsers_id() + ",");
			System.out.print(aTrip_collect.getTrip_collect_time() );
			System.out.println();
		}
		
	}

}
