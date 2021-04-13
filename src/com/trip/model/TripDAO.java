package com.trip.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip_detail.model.Trip_detailVO;

public class TripDAO implements TripDAO_interface {

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
			"INSERT INTO trip (users_id,last_editor,trip_state,read_authority,"
					+ "edit_authority,trip_area,trip_start,trip_end,trip_name,trip_description,"
					+ "trip_type,trip_tot_cost,place_weather) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = "SELECT * FROM trip order by trip_id";
	private static final String GET_ONE_STMT =  "SELECT * FROM trip where trip_id = ?";
	private static final String DELETE =  "DELETE FROM trip where trip_id = ?";
	private static final String UPDATE =  "UPDATE trip set users_id= ?,last_editor= ?,trip_state= ?,read_authority= ?,"
	+ "edit_authority= ?,trip_area= ?,trip_start= ?,trip_end= ?,trip_name= ?,trip_description= ?,"
	+ " trip_type= ?,trip_tot_cost= ?,place_weather= ? where trip_id = ?";
	private static final String UPDATE_LOOK =  "UPDATE trip set trip_liik=? where trip_id = ?";
	
	private static final String GET_Detail_ByTrip_STMT = "SELECT * FROM trip_detail where trip_id = ? order by trip_day,trip_sort";
	private static final String GET_Team_ByTrip_STMT = "SELECT * FROM team where trip_id = ? order by users_id";
//	private static final String GET_Activities_ByTrip_STMT = "SELECT * FROM group_activities where trip_id = ? order by activities_id";
	
	@Override
	public void insert(TripVO tripVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			con = ds.getConnection();
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
	
			con = ds.getConnection();
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
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
	
			pstmt.setInt(1, trip_id);
	
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
	public TripVO findByPrimaryKey(Integer trip_id) {
	
		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, trip_id);
			rs = pstmt.executeQuery();
			
//			pstmt = con.prepareStatement(UPDATE_LOOK);
//			pstmt.setInt(1, tripVO.getTrip_look());
//			pstmt.setInt(2, trip_id);
//
//			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// TripVO 也稱為 Domain objects
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
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// TripVO 也稱為 Domain objects
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
	public Set<Trip_detailVO> getDetailByTrip(Integer trip_id) {
		Set<Trip_detailVO> set = new LinkedHashSet<Trip_detailVO>();
		Trip_detailVO trip_detailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Detail_ByTrip_STMT);
			pstmt.setInt(1, trip_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
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

				set.add(trip_detailVO); // Store the row in the vector
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

	@Override
	public Set<TeamVO> getTeamByTrip(Integer trip_id) {
		Set<TeamVO> set = new LinkedHashSet<TeamVO>();
		TeamVO teamVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Team_ByTrip_STMT);
			pstmt.setInt(1, trip_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				teamVO = new TeamVO();
				teamVO.setTrip_id(rs.getInt("trip_id"));
				teamVO.setUsers_id(rs.getInt("users_id"));

				set.add(teamVO); // Store the row in the vector
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

	@Override
	public Set<Group_activitiesVO> getActivitiesByTrip(Integer trip_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithTrip_detail(TripVO tripVO, List<Trip_detailVO> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertWithTeam(TripVO tripVO, List<TeamVO> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertWithTrip_detail_and_Team(TripVO tripVO, List<Trip_detailVO> list, List<TeamVO> list1) {
		// TODO Auto-generated method stub
		
	}
}
