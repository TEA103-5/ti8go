package com.place_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Place_collectDAO implements Place_collectDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO place_collect (place_id , users_id ) VALUES(? ,? )";
	private static final String GET_ALL_STMT = "SELECT place_id , users_id , place_collect_time FROM place_collect order by users_id";
//	private static final String GET_ONE_STMT = "SELECT place_id , users_id , place_collect_time FROM place_collect where users_id = ?";
	private static final String DELETE = "DELETE FROM place_collect where place_id = ? and users_id = ? ";
	// 收藏不會用更新的方式去變更 , 所以更新指令只是練習用的,條件寫死在update方法裡
//	private static final String UPDATE = "UPDATE place_collect set place_id=? , users_id=?  where place_id = ? and users_id = ?";
	
	private static final String GET_ALL_BY_ID_STMT = "SELECT place_id , users_id , place_collect_time FROM place_collect where users_id = ?";


	@Override
	public void insert(Place_collectVO place_collectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setObject(1, place_collectVO.getPlace_id(), Types.INTEGER);
			pstmt.setObject(2, place_collectVO.getUsers_id(), Types.INTEGER);

			pstmt.executeUpdate();

		}  catch (SQLException e) {
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
//	public void update(Place_collectVO place_collectVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setObject(1, place_collectVO.getPlace_id(), Types.INTEGER);
//			pstmt.setObject(2, place_collectVO.getUsers_id(), Types.INTEGER);
//			pstmt.setObject(3, 1, Types.INTEGER);
//			pstmt.setObject(4, 1, Types.INTEGER);
//
//			pstmt.executeUpdate();
//
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
//
//	}

	@Override
	public void delete(Integer place_id, Integer users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setObject(1, place_id, Types.INTEGER);
			pstmt.setObject(2, users_id, Types.INTEGER);
			pstmt.executeUpdate();
		}  catch (SQLException e) {
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
//	public Place_collectVO findByPrimaryKey(Integer users_id) {
//		Place_collectVO place_collectVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			pstmt.setObject(1, users_id, Types.INTEGER);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				place_collectVO = new Place_collectVO();
//
//				place_collectVO.setPlace_id(rs.getInt("place_id"));
//				place_collectVO.setUsers_id(rs.getInt("users_id"));
//				place_collectVO.setPlace_collect_time(rs.getTimestamp("place_collect_time"));
//
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
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
//
//		return place_collectVO;
//	}

	@Override
	public List<Place_collectVO> getAll() {
		List<Place_collectVO> list = new ArrayList<Place_collectVO>();
		Place_collectVO place_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_collectVO = new Place_collectVO();

				place_collectVO.setPlace_id(rs.getInt("place_id"));
				place_collectVO.setUsers_id(rs.getInt("users_id"));
				place_collectVO.setPlace_collect_time(rs.getTimestamp("place_collect_time"));

				list.add(place_collectVO);

			}
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
	public List<Place_collectVO> getAllByPrimaryKey(Integer users_id) {
		List<Place_collectVO> list = new ArrayList<Place_collectVO>();
		Place_collectVO place_collectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_ID_STMT);
			pstmt.setObject(1, users_id, Types.INTEGER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_collectVO = new Place_collectVO();

				place_collectVO.setPlace_id(rs.getInt("place_id"));
				place_collectVO.setUsers_id(rs.getInt("users_id"));
				place_collectVO.setPlace_collect_time(rs.getTimestamp("place_collect_time"));

				list.add(place_collectVO);

			}
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

}
