package com.place.model;

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

import com.emp.model.EmpDAO_interface;
import com.emp.model.EmpVO;
import com.sun.xml.internal.ws.encoding.DataHandlerDataSource;

public class PlaceDAO implements PlaceDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO place (place_name, place_address, place_longitude, place_latitude, "
			+ "place_tel,place_region,place_type,place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time) VALUES(? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT place_id,place_name,place_address,place_longitude,place_latitude,place_tel,place_region,place_type,"
			+ "place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time,place_like FROM place order by place_id";
	private static final String GET_ONE_STMT = "SELECT place_id,place_name,place_address,place_longitude,place_latitude,place_tel,place_region,place_type,"
			+ "place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time,place_like FROM place where place_id = ? ";
	private static final String DELETE = "DELETE FROM place where place_id = ?";
	private static final String UPDATE = "UPDATE place set place_name=?, place_address=?, place_longitude=?, place_latitude=?, "
			+ "place_tel=?,place_region=?,place_type=?,place_index=?,place_pic1=?,place_pic2=?,place_pic3=?,place_state=?,users_id=?,business_time=? where place_id=?";

	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, placeVO.getPlace_name());
			pstmt.setString(2, placeVO.getPlace_address());
			pstmt.setBigDecimal(3, placeVO.getPlace_longitude());
			pstmt.setBigDecimal(4, placeVO.getPlace_latitude());
			pstmt.setString(5, placeVO.getPlace_tel());
			pstmt.setString(6, placeVO.getPlace_region());
			pstmt.setString(7, placeVO.getPlace_type());
			pstmt.setString(8, placeVO.getPlace_index());
			pstmt.setBytes(9, placeVO.getPlace_pic1());
			pstmt.setBytes(10, placeVO.getPlace_pic2());
			pstmt.setBytes(11, placeVO.getPlace_pic3());
			pstmt.setInt(12, placeVO.getPlace_state());
//			pstmt.setInt(13, placeVO.getUsers_id());   // 因setInt 會將傳入的Integer物件轉換成int,當傳入物件為null時會出現空指針例外
			pstmt.setObject(13, placeVO.getUsers_id(), Types.INTEGER);
//			pstmt.setInt(14, placeVO.getBusiness_time());
			pstmt.setObject(14, placeVO.getBusiness_time(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());

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
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, placeVO.getPlace_name());
			pstmt.setString(2, placeVO.getPlace_address());
			pstmt.setBigDecimal(3, placeVO.getPlace_longitude());
			pstmt.setBigDecimal(4, placeVO.getPlace_latitude());
			pstmt.setString(5, placeVO.getPlace_tel());
			pstmt.setString(6, placeVO.getPlace_region());
			pstmt.setString(7, placeVO.getPlace_type());
			pstmt.setString(8, placeVO.getPlace_index());

			pstmt.setBytes(9, placeVO.getPlace_pic1());
			pstmt.setBytes(10, placeVO.getPlace_pic2());
			pstmt.setBytes(11, placeVO.getPlace_pic3());
			pstmt.setInt(12, placeVO.getPlace_state());
//			pstmt.setInt(13, placeVO.getUsers_id());   // 因setInt 會將傳入的Integer物件轉換成int,當傳入物件為null時會出現空指針例外
			pstmt.setObject(13, placeVO.getUsers_id(), Types.INTEGER);
//			pstmt.setInt(14, placeVO.getBusiness_time());
			pstmt.setObject(14, placeVO.getBusiness_time(), Types.INTEGER);
			pstmt.setObject(15, placeVO.getPlace_id(), Types.INTEGER);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer place_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setObject(1, place_id, Types.INTEGER);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public PlaceVO findByPrimaryKey(Integer place_id) {
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, place_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPlace_id(rs.getInt("place_id"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_address(rs.getString("place_address"));
				placeVO.setPlace_longitude(rs.getBigDecimal("place_longitude"));
				placeVO.setPlace_latitude(rs.getBigDecimal("place_latitude"));
				placeVO.setPlace_tel(rs.getString("place_tel"));
				placeVO.setPlace_region(rs.getString("place_region"));
				placeVO.setPlace_type(rs.getString("place_type"));
				placeVO.setPlace_index(rs.getString("place_index"));
				placeVO.setPlace_pic1(rs.getBytes("place_pic1"));
//			placeVO.setPlace_pic1(rs.getBinaryStream("place_pic1"));
				placeVO.setPlace_pic2(rs.getBytes("place_pic2"));
				placeVO.setPlace_pic3(rs.getBytes("place_pic3"));
				placeVO.setPlace_state(rs.getInt("place_state"));
				placeVO.setUsers_id(rs.getInt("users_id"));
				placeVO.setBusiness_time(rs.getInt("business_time"));
				placeVO.setPlace_like(rs.getInt("place_like"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return placeVO;

	}

	@Override
	public List<PlaceVO> getAll() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.getResultSet();
			
			while (rs.next()) {

				placeVO = new PlaceVO();
				placeVO.setPlace_id(rs.getInt("place_id"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_address(rs.getString("place_address"));
				placeVO.setPlace_longitude(rs.getBigDecimal("place_longitude"));
				placeVO.setPlace_latitude(rs.getBigDecimal("place_latitude"));
				placeVO.setPlace_tel(rs.getString("place_tel"));
				placeVO.setPlace_region(rs.getString("place_region"));
				placeVO.setPlace_type(rs.getString("place_type"));
				placeVO.setPlace_index(rs.getString("place_index"));
				placeVO.setPlace_pic1(rs.getBytes("place_pic1"));
//			placeVO.setPlace_pic1(rs.getBinaryStream("place_pic1"));
				placeVO.setPlace_pic2(rs.getBytes("place_pic2"));
				placeVO.setPlace_pic3(rs.getBytes("place_pic3"));
				placeVO.setPlace_state(rs.getInt("place_state"));
				placeVO.setUsers_id(rs.getInt("users_id"));
				placeVO.setBusiness_time(rs.getInt("business_time"));
				placeVO.setPlace_like(rs.getInt("place_like"));
				list.add(placeVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
