package com.notec.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

	
	public class NoteCDAO implements NoteCDAO_interface {
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
				"INSERT INTO Note_C (note_c_title,note_c_content,note_c_img,note_id) VALUES (?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT note_c_id,note_c_title,note_c_content,note_c_img,note_id FROM Note_c order by note_c_id ";
			private static final String GET_ONE_STMT = 
				"SELECT note_c_id,note_c_title,note_c_content,note_c_img,note_id FROM Note_c where note_c_id = ?";
			private static final String DELETE = 
				"DELETE FROM Note_c where note_c_id = ?";
			private static final String UPDATE = 
				"UPDATE Note_c set note_c_title=?, note_c_content=?, note_c_img=?, note_id=? where note_c_id = ?";

			@Override
			public void insert(NoteCVO noteCVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);
					
					pstmt.setString(1, noteCVO.getNote_c_title());
					pstmt.setString(2, noteCVO.getNote_c_content());
					pstmt.setBytes(3, noteCVO.getNote_c_img());
					pstmt.setInt(4, noteCVO.getNote_id());

					pstmt.executeUpdate();

					// Handle any driver errors
				
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
					// Clean up JDBC resources
				} 
				finally {
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
			public void update(NoteCVO noteCVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);
					
					pstmt.setString(1, noteCVO.getNote_c_title());
					pstmt.setString(2, noteCVO.getNote_c_content());
					pstmt.setBytes(3, noteCVO.getNote_c_img());
					pstmt.setInt(4, noteCVO.getNote_id());
					pstmt.setInt(5, noteCVO.getNote_c_id());
	

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
			public void delete(Integer note_c_id) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setInt(1, note_c_id);

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
			public NoteCVO findByPrimaryKey(Integer note_c_id) {

				NoteCVO noteCVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setInt(1, note_c_id);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						
						noteCVO = new NoteCVO();
						noteCVO.setNote_c_id(rs.getInt("note_c_id"));
						noteCVO.setNote_c_title(rs.getString("note_c_title"));
						noteCVO.setNote_c_content(rs.getString("note_c_content"));
						noteCVO.setNote_c_img(rs.getBytes("note_c_img"));
						noteCVO.setNote_id(rs.getInt("note_id"));
						
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
				return noteCVO;
			}

			@Override
			public List<NoteCVO> getAll() {
				List<NoteCVO> list = new ArrayList<NoteCVO>();
				NoteCVO noteCVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();;
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						
						noteCVO = new NoteCVO();
						noteCVO.setNote_c_id(rs.getInt("note_c_id"));
						noteCVO.setNote_c_title(rs.getString("note_c_title"));
						noteCVO.setNote_c_content(rs.getString("note_c_content"));
						noteCVO.setNote_c_img(rs.getBytes("note_c_img"));
						noteCVO.setNote_id(rs.getInt("note_id"));
						list.add(noteCVO); // Store the row in the list
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
}