package com.team.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamJDBCDAO implements TeamDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO team (trip_id,users_id ) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT trip_id,users_id FROM team order by trip_id,users_id";
	private static final String GET_ONE_STMT = 
		"SELECT trip_id,users_id FROM team where (trip_id = ?) and (users_id = ?) ";
	private static final String DELETE = 
		"DELETE FROM team where  (trip_id = ?) and (users_id = ?)";
	private static final String UPDATE = 
		"UPDATE team set users_id = ? WHERE (trip_id = ?) and (users_id = ?)";

	@Override
	public void insert(TeamVO teamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, teamVO.getTrip_id());
			pstmt.setInt(2, teamVO.getUsers_id());

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
	public void update(Integer trip_id, Integer users_id , Integer users_id1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(2, trip_id);
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
	public void delete(Integer trip_id, Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trip_id);
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
	public TeamVO findByPrimaryKey(Integer trip_id, Integer users_id) {
		TeamVO teamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trip_id);
			pstmt.setInt(2, users_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				teamVO = new TeamVO();
				teamVO.setTrip_id(rs.getInt("trip_id"));
				teamVO.setUsers_id(rs.getInt("users_id"));
				
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
		return teamVO;
	}

	@Override
	public List<TeamVO> getAll() {
		List<TeamVO> list = new ArrayList<TeamVO>();
		TeamVO teamVO = null;

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
				teamVO = new TeamVO();
				teamVO.setTrip_id(rs.getInt("trip_id"));
				teamVO.setUsers_id(rs.getInt("users_id"));

				list.add(teamVO); // Store the row in the list
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
		TeamJDBCDAO dao = new TeamJDBCDAO();
		
		// 新增
//		TeamVO teamvo1 = new TeamVO();
//		teamvo1.setTrip_id(1);
//		teamvo1.setUsers_id(3);
//		dao.insert(teamvo1);
//		System.out.println("-------insert success--------");
		
		// 修改
		
		dao.update(1,1,3);
		System.out.println("-------update success--------");

		// 刪除
//		dao.delete(1,1);
//		System.out.println("-------delete success--------");

		// 查詢
//		TeamVO teamvo3 = dao.findByPrimaryKey(1,2);
//		System.out.print(teamvo3.getTrip_id() + ",");
//		System.out.println(teamvo3.getUsers_id() );
//		System.out.println("---------findByPrimaryKey success------------");

		// 查詢
//		List<TeamVO> list = dao.getAll();
//		for (TeamVO aTeam : list) {
//			System.out.print(aTeam.getTrip_id() + ",");
//			System.out.print(aTeam.getUsers_id() );
//			System.out.println();
//		}
//		System.out.println("---------findAll success------------");
	}

}
