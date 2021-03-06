package com.place.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAOJDBC implements PlaceDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO place (place_name, place_address, place_longitude, place_latitude, "
			+ "place_tel,place_region,place_type,place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time) VALUES(? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT place_id,place_name,place_address,place_longitude,place_latitude,place_tel,place_region,place_type,"
			+ "place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time FROM place order by place_id";
	private static final String GET_ONE_STMT = "SELECT place_id,place_name,place_address,place_longitude,place_latitude,place_tel,place_region,place_type,"
			+ "place_index,place_pic1,place_pic2,place_pic3,place_state,users_id,business_time FROM place where place_id = ? ";
	private static final String DELETE = "DELETE FROM place where place_id = ?";
	private static final String UPDATE = "UPDATE place set place_name=?, place_address=?, place_longitude=?, place_latitude=?, "
			+ "place_tel=?,place_region=?,place_type=?,place_index=?,place_pic1=?,place_pic2=?,place_pic3=?,place_state=?,users_id=?,business_time=? where place_id=?";

	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, placeVO.getPlace_name());
			pstmt.setString(2, placeVO.getPlace_address());
			pstmt.setBigDecimal(3, placeVO.getPlace_longitude());
			pstmt.setBigDecimal(4, placeVO.getPlace_latitude());
			pstmt.setString(5, placeVO.getPlace_tel());
			pstmt.setString(6, placeVO.getPlace_region());
			pstmt.setString(7, placeVO.getPlace_type());
			pstmt.setString(8, placeVO.getPlace_index());
			pstmt.setBinaryStream(9, placeVO.getPlace_pic1());
			pstmt.setBinaryStream(10, placeVO.getPlace_pic2());
			pstmt.setBinaryStream(11, placeVO.getPlace_pic3());
			pstmt.setInt(12, placeVO.getPlace_state());
//			pstmt.setInt(13, placeVO.getUsers_id());   // 因setInt 會將傳入的Integer物件轉換成int,當傳入物件為null時會出現空指針例外
			pstmt.setObject(13, placeVO.getUsers_id(), Types.INTEGER);
//			pstmt.setInt(14, placeVO.getBusiness_time());
			pstmt.setObject(14, placeVO.getBusiness_time(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, placeVO.getPlace_name());
			pstmt.setString(2, placeVO.getPlace_address());
			pstmt.setBigDecimal(3, placeVO.getPlace_longitude());
			pstmt.setBigDecimal(4, placeVO.getPlace_latitude());
			pstmt.setString(5, placeVO.getPlace_tel());
			pstmt.setString(6, placeVO.getPlace_region());
			pstmt.setString(7, placeVO.getPlace_type());
			pstmt.setString(8, placeVO.getPlace_index());
			pstmt.setBinaryStream(9, placeVO.getPlace_pic1());
			pstmt.setBinaryStream(10, placeVO.getPlace_pic2());
			pstmt.setBinaryStream(11, placeVO.getPlace_pic3());
			pstmt.setInt(12, placeVO.getPlace_state());
//			pstmt.setInt(13, placeVO.getUsers_id());   // 因setInt 會將傳入的Integer物件轉換成int,當傳入物件為null時會出現空指針例外
			pstmt.setObject(13, placeVO.getUsers_id(), Types.INTEGER);
//			pstmt.setInt(14, placeVO.getBusiness_time());
			pstmt.setObject(14, placeVO.getBusiness_time(), Types.INTEGER);
			pstmt.setObject(15, placeVO.getPlace_id(), Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer place_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setObject(1, place_id, Types.INTEGER);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	}

	@Override
	public PlaceVO findByPrimaryKey(Integer place_id) {
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				placeVO.setPlace_pic1(rs.getBinaryStream("place_pic1"));
				placeVO.setPlace_pic2(rs.getBinaryStream("place_pic2"));
				placeVO.setPlace_pic3(rs.getBinaryStream("place_pic3"));
				placeVO.setPlace_state(rs.getInt("place_state"));
				placeVO.setUsers_id(rs.getInt("users_id"));
				placeVO.setBusiness_time(rs.getInt("business_time"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				placeVO.setPlace_pic1(rs.getBinaryStream("place_pic1"));
				placeVO.setPlace_pic2(rs.getBinaryStream("place_pic2"));
				placeVO.setPlace_pic3(rs.getBinaryStream("place_pic3"));
				placeVO.setPlace_state(rs.getInt("place_state"));
				placeVO.setUsers_id(rs.getInt("users_id"));
				placeVO.setBusiness_time(rs.getInt("business_time"));
				list.add(placeVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		return list;
	}

	public static void main(String[] args) throws IOException {
		PlaceDAOJDBC dao = new PlaceDAOJDBC();
		Picture picture = new Picture();
		InputStream fis1 = null;
		InputStream fis2 = null;
		InputStream fis3 = null;

		// 新增
		PlaceVO placeVO1 = new PlaceVO();
		placeVO1.setPlace_name("南京復興捷運站");
		placeVO1.setPlace_address("南京東路一段一號");
		placeVO1.setPlace_longitude(new BigDecimal(121.297187));
		placeVO1.setPlace_latitude(new BigDecimal(24.943325));
		placeVO1.setPlace_tel("886-123456789");
		placeVO1.setPlace_region("台北市");
		placeVO1.setPlace_type("景點");
		placeVO1.setPlace_index("這裡是南京復興捷運站");

		placeVO1.setPlace_pic1(fis1 = picture.getStreamFromLocal("items/FC_Barcelona.png"));
		placeVO1.setPlace_pic2(fis2 = picture.getStreamFromLocal("items/FC_Barcelona.png"));
		placeVO1.setPlace_pic3(fis3 = picture.getStreamFromLocal("items/FC_Barcelona.png"));
		placeVO1.setPlace_state(0);
		placeVO1.setUsers_id(1);
		placeVO1.setBusiness_time(1);
		
		dao.insert(placeVO1);
		
		fis1.close();
		fis2.close();
		fis3.close();

		// 修改
		PlaceVO placeVO2 = new PlaceVO();
		placeVO2.setPlace_name("南京復興捷運站EXEX");
		placeVO2.setPlace_address("南京東路一段一號EXEX");
		placeVO2.setPlace_longitude(new BigDecimal(121.297187));
		placeVO2.setPlace_latitude(new BigDecimal(24.943325));
		placeVO2.setPlace_tel("886-123456789");
		placeVO2.setPlace_region("台北市");
		placeVO2.setPlace_type("景點");
		placeVO2.setPlace_index("這裡是南京復興捷運站");

		placeVO2.setPlace_pic1(fis1 = picture.getStreamFromLocal("items/FC_Real_Madrid.png"));
		placeVO2.setPlace_pic2(fis2 = picture.getStreamFromLocal("items/FC_Real_Madrid.png"));
		placeVO2.setPlace_pic3(fis3 = picture.getStreamFromLocal("items/FC_Real_Madrid.png"));
		placeVO2.setPlace_state(0);
//		placeVO2.setUsers_id(1);
//		placeVO2.setBusiness_time(1);
		placeVO2.setPlace_id(11);
		
		dao.update(placeVO2);
		
		fis1.close();
		fis2.close();
		fis3.close();

		// 刪除
		dao.delete(11);

		// 查詢單筆
		PlaceVO placeVo3 = dao.findByPrimaryKey(12);
		
		System.out.print(placeVo3.getPlace_id() + ",");
		System.out.print(placeVo3.getPlace_address() + ",");
		System.out.print(placeVo3.getPlace_longitude() + ",");
		System.out.print(placeVo3.getPlace_latitude() + ",");
		System.out.print(placeVo3.getPlace_tel() + ",");
		System.out.print(placeVo3.getPlace_region() + ",");
		System.out.print(placeVo3.getPlace_type() + ",");
		System.out.print(placeVo3.getPlace_index() + ",");
//		System.out.print(placeVo3.getPlace_pic1() + ",");
		picture.readPicture(placeVo3.getPlace_pic1());
		System.out.print(placeVo3.getPlace_pic2() + ",");
		System.out.print(placeVo3.getPlace_pic3() + ",");
		System.out.print(placeVo3.getPlace_state() + ",");
		System.out.print(placeVo3.getUsers_id() + ",");
		System.out.println(placeVo3.getBusiness_time() + ",");
		System.out.println("---------------------");
		
		// 查詢全部
		List<PlaceVO> list = dao.getAll();
		for(PlaceVO aPlace : list) {
			System.out.print(aPlace.getPlace_id() + ",");
			System.out.print(aPlace.getPlace_address() + ",");
			System.out.print(aPlace.getPlace_longitude() + ",");
			System.out.print(aPlace.getPlace_latitude() + ",");
			System.out.print(aPlace.getPlace_tel() + ",");
			System.out.print(aPlace.getPlace_region() + ",");
			System.out.print(aPlace.getPlace_type() + ",");
			System.out.print(aPlace.getPlace_index() + ",");
			System.out.print(aPlace.getPlace_pic1() + ",");
			System.out.print(aPlace.getPlace_pic2() + ",");
			System.out.print(aPlace.getPlace_pic3() + ",");
			System.out.print(aPlace.getPlace_state() + ",");
			System.out.print(aPlace.getUsers_id() + ",");
			System.out.print(aPlace.getBusiness_time() + ",");
			System.out.println();
			
		}
		
		
	}

}
