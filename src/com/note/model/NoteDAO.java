package com.note.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.note.model.NoteVO;

public class NoteDAO implements NoteDAO_interface {
	
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
			"INSERT INTO Note (note_classid,travel_start,note_title,note_description,users_id,trip_id,note_like) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT note_id,note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id,note_like FROM Note where note_classid=1 order by note_id ";

		private static final String GET_ONE_STMT = 
			"SELECT note_id,note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id,note_like FROM Note where note_id = ?";
		private static final String DELETE = 
			"DELETE FROM Note where note_id = ?";
		private static final String UPDATE = 
			"UPDATE Note set note_classid=?, travel_start=?, note_title=?, note_description=?, users_id=?, trip_id=?, note_like=?  where note_id = ?";
		
		// 利用條件篩選搜尋找出要放在畫面CARD內的資料
		private static final String GET_BY_TITLE_AND_DESCRIPTION = 
			"SELECT note_id,note_classid,note_date,travel_start,note_title,note_description,note_update,users_id,trip_id,note_like FROM Note where note_title like ? or note_description like ? ";
		
		@Override
		public void insert(NoteVO noteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, noteVO.getNote_classid());
//				pstmt.setTimestamp(2, noteVO.getNote_date());
				pstmt.setDate(2, noteVO.getTravel_start());
				pstmt.setString(3, noteVO.getNote_title());
				pstmt.setString(4, noteVO.getNote_description());
//				pstmt.setTimestamp(6, noteVO.getNote_update());
				pstmt.setInt(5, noteVO.getUsers_id());
//				pstmt.setInt(8, noteVO.getTrip_id());
				pstmt.setObject(6,noteVO.getTrip_id(), Types.INTEGER);
				pstmt.setInt(7, noteVO.getNote_like());

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
		public void update(NoteVO noteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, noteVO.getNote_classid());
//				pstmt.setTimestamp(2, noteVO.getNote_date());
				pstmt.setDate(2, noteVO.getTravel_start());
				pstmt.setString(3, noteVO.getNote_title());
				pstmt.setString(4, noteVO.getNote_description());
//				pstmt.setTimestamp(6, noteVO.getNote_update());
				pstmt.setInt(5, noteVO.getUsers_id());
				pstmt.setInt(6, noteVO.getTrip_id());
				pstmt.setInt(7, noteVO.getNote_like());
				pstmt.setInt(8, noteVO.getNote_id());

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
		public void delete(Integer note_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, note_id);

				pstmt.executeUpdate();

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
		public NoteVO findByPrimaryKey(Integer note_id) {

			NoteVO noteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, note_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					
					noteVO = new NoteVO();
					noteVO.setNote_id(rs.getInt("note_id"));
					noteVO.setNote_classid(rs.getInt("note_classid"));
					noteVO.setNote_date(rs.getTimestamp("note_date"));
					noteVO.setTravel_start(rs.getDate("travel_start"));
					noteVO.setNote_title(rs.getString("note_title"));
					noteVO.setNote_description(rs.getString("note_description"));
					noteVO.setNote_update(rs.getTimestamp("note_update"));
					noteVO.setUsers_id(rs.getInt("users_id"));
					noteVO.setTrip_id(rs.getInt("trip_id"));
					noteVO.setNote_like(rs.getInt("note_like"));
				}

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
			return noteVO;
		}

		@Override
		public List<NoteVO> getAll() {
			List<NoteVO> list = new ArrayList<NoteVO>();
			NoteVO noteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					noteVO = new NoteVO();
					noteVO.setNote_id(rs.getInt("note_id"));
					noteVO.setNote_classid(rs.getInt("note_classid"));
					noteVO.setNote_date(rs.getTimestamp("note_date"));
					noteVO.setTravel_start(rs.getDate("travel_start"));
					noteVO.setNote_title(rs.getString("note_title"));
					noteVO.setNote_description(rs.getString("note_description"));
					noteVO.setNote_update(rs.getTimestamp("note_update"));
					noteVO.setUsers_id(rs.getInt("users_id"));
					noteVO.setTrip_id(rs.getInt("trip_id"));
					noteVO.setNote_like(rs.getInt("note_like"));
					list.add(noteVO); // Store the row in the list
				}
			
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
		public List<NoteVO> getMyGo(String note_title, String note_description) {
			List<NoteVO> list = new ArrayList<NoteVO>();
			NoteVO noteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_BY_TITLE_AND_DESCRIPTION);
				pstmt.setString(1, "%" + note_title + "%");
				pstmt.setString(2, "%" + note_description + "%");
				rs = pstmt.executeQuery();

				while (rs.next()) {

					noteVO = new NoteVO();
					noteVO.setNote_id(rs.getInt("note_id"));
					noteVO.setNote_classid(rs.getInt("note_classid"));
					noteVO.setNote_date(rs.getTimestamp("note_date"));
					noteVO.setTravel_start(rs.getDate("travel_start"));
					noteVO.setNote_title(rs.getString("note_title"));
					noteVO.setNote_description(rs.getString("note_description"));
					noteVO.setNote_update(rs.getTimestamp("note_update"));
					noteVO.setUsers_id(rs.getInt("users_id"));
					noteVO.setTrip_id(rs.getInt("trip_id"));
					noteVO.setNote_like(rs.getInt("note_like"));

					list.add(noteVO);

				}
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. " + e.getMessage());
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