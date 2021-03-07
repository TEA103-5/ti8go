package com.place_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Place_collectDAOJDBC implements Place_collectDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO place_collect (place_id , users_id ) VALUES(? ,? )";
	private static final String GET_ALL_STMT = "SELECT place_collect_id , place_id , users_id , place_collect_time FROM place_collect order by place_collect_id";
	private static final String GET_ONE_STMT = "SELECT place_collect_id , place_id , users_id , place_collect_time FROM place_collect where place_collect_id = ?";
	private static final String DELETE = "DELETE FROM place_collect where place_collect_id = ?";
	private static final String UPDATE = "UPDATE place_collect set place_id=? , users_id=? where place_collect_id = ?";

	@Override
	public void insert(Place_collectVO place_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, place_collectVO.getPlace_id(), Types.INTEGER);
			pstmt.setObject(2, place_collectVO.getUsers_id(), Types.INTEGER);

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
	public void update(Place_collectVO place_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setObject(1, place_collectVO.getPlace_id(), Types.INTEGER);
			pstmt.setObject(2, place_collectVO.getUsers_id(), Types.INTEGER);
			pstmt.setObject(3, place_collectVO.getPlace_collect_id(), Types.INTEGER);

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
	public void delete(Integer place_collect_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setObject(1, place_collect_id, Types.INTEGER);
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
	public Place_collectVO findByPrimaryKey(Integer place_collect_id) {
		Place_collectVO place_collectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setObject(1, place_collect_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_collectVO = new Place_collectVO();

				place_collectVO.setPlace_collect_id(rs.getInt("place_collect_id"));
				place_collectVO.setPlace_id(rs.getInt("place_id"));
				place_collectVO.setUsers_id(rs.getInt("users_id"));
				place_collectVO.setPlace_collect_time(rs.getTimestamp("place_collect_time"));

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

		return place_collectVO;
	}

	@Override
	public List<Place_collectVO> getAll() {
		List<Place_collectVO> list = new ArrayList<Place_collectVO>();
		Place_collectVO place_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_collectVO = new Place_collectVO();

				place_collectVO.setPlace_collect_id(rs.getInt("place_collect_id"));
				place_collectVO.setPlace_id(rs.getInt("users_id"));
				place_collectVO.setUsers_id(rs.getInt("users_id"));
				place_collectVO.setPlace_collect_time(rs.getTimestamp("place_collect_time"));

				list.add(place_collectVO);

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

	public static void main(String[] args) {

		Place_collectDAOJDBC dao = new Place_collectDAOJDBC();

		// 新增
		Place_collectVO place_collectVO1 = new Place_collectVO();
		place_collectVO1.setPlace_id(5);
		place_collectVO1.setUsers_id(1);

		dao.insert(place_collectVO1);

		// 修改
		Place_collectVO place_collectVO2 = new Place_collectVO();
		place_collectVO2.setPlace_id(5);
		place_collectVO2.setUsers_id(1);
		place_collectVO2.setPlace_collect_id(1);
		;

		dao.update(place_collectVO2);

		// 刪除
		dao.delete(1);

		// 查詢一筆
		Place_collectVO place_collectVO3 = dao.findByPrimaryKey(2);

		System.out.print(place_collectVO3.getPlace_collect_id() + ",");
		System.out.print(place_collectVO3.getPlace_id() + ",");
		System.out.print(place_collectVO3.getUsers_id() + ",");
		System.out.println(place_collectVO3.getPlace_collect_time());
		System.out.println("---------------------");

		// 查詢全部
		List<Place_collectVO> list = dao.getAll();

		for (Place_collectVO Aplace_collectVO : list) {
			System.out.print(Aplace_collectVO.getPlace_collect_id() + ",");
			System.out.print(Aplace_collectVO.getPlace_id() + ",");
			System.out.print(Aplace_collectVO.getUsers_id() + ",");
			System.out.print(Aplace_collectVO.getPlace_collect_time());
			System.out.println();
		}

	}
}
