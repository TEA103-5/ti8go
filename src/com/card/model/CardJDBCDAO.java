package com.card.model;

import java.util.*;

import util.Util;

import java.sql.*;

public class CardJDBCDAO implements CardDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO card (card_id, users_id, card_number, card_date, card_last, card_default) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE card SET card_id = ?, card_number = ?, card_date = ?, card_last = ?, card_default = ? where users_id = ?";
	private static final String DELETE_STMT = "DELETE FROM card WHERE card_id = ?";
	private static final String FIND_BY_PK = "SELECT * FROM card WHERE card_id = ?";
	private static final String GET_ALL = "SELECT * FROM card"; 
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}



	private int card_id;
	
	@Override
	public void insert(CardVO cardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, cardVO.getCard_id());
			pstmt.setInt(2, cardVO.getUsers_id());
			pstmt.setString(3, cardVO.getCard_number());
			pstmt.setString(4, cardVO.getCard_date());
			pstmt.setString(5, cardVO.getCard_last());
			pstmt.setInt(6, cardVO.getCard_default());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(CardVO cardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, cardVO.getCard_id());
			pstmt.setString(2, cardVO.getCard_number());
			pstmt.setString(3, cardVO.getCard_date());
			pstmt.setString(4, cardVO.getCard_last());
			pstmt.setInt(5, cardVO.getCard_default());
			pstmt.setInt(6, cardVO.getUsers_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void delete(Integer card_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, card_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public CardVO findByPK(Integer card_id) {
		CardVO card = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, card_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				card = new CardVO();
				card.setCard_id(rs.getInt("card_id"));
				card.setUsers_id(rs.getInt("users_id"));
				card.setCard_number(rs.getString("card_number"));
				card.setCard_date(rs.getString("card_date"));
				card.setCard_last(rs.getString("card_last"));
				card.setCard_default(rs.getInt("card_default"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
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

		return card;
	}



	@Override
	public List<CardVO> getAll() {
		List<CardVO> cardList = new ArrayList<>();
		CardVO card = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				card = new CardVO();
				card.setCard_id(rs.getInt("card_id"));
				card.setUsers_id(rs.getInt("users_id"));
				card.setCard_number(rs.getString("card_number"));
				card.setCard_date(rs.getString("card_date"));
				card.setCard_last(rs.getString("card_last"));
				card.setCard_default(rs.getInt("card_default"));
				cardList.add(card);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return cardList;
	
	}



	public static void main(String[] args) {
		CardJDBCDAO dao = new CardJDBCDAO();
		
		//新增
//		cardVO cardVO1 = new cardVO();
//		cardVO1.setCard_id(20);
//		cardVO1.setUsers_id(1);
//		cardVO1.setCard_number("1234567890124321");
//		cardVO1.setCard_date("0122");
//		cardVO1.setCard_last("888");
//		cardVO1.setCard_default(1);
//		dao.insert(cardVO1);
		
		//修改
//		cardVO cardVO2 = new cardVO();
//		cardVO2.setCard_id(31);
//		cardVO2.setUsers_id(9);
//		cardVO2.setCard_number("1234567890124321");
//		cardVO2.setCard_date("1223");
//		cardVO2.setCard_last("777");
//		cardVO2.setCard_default(1);
//		dao.update(cardVO2);
		
		//刪除
//		dao.delete(4);
		
		//查詢
		CardVO card3 = dao.findByPK(7);
		System.out.print("--- ");
		System.out.print(card3.getCard_id() + ",");
		System.out.print(card3.getUsers_id() + ",");
		System.out.print(card3.getCard_number() + ",");
		System.out.print(card3.getCard_date() + ",");
		System.out.print(card3.getCard_last() + ",");
		System.out.print(card3.getCard_default() + ",");
		System.out.println(" ---");
		
		//查詢
		List<CardVO> list = dao.getAll();
		for (CardVO  card : list) {
			System.out.print(card.getCard_id() + ",");
			System.out.print(card.getUsers_id() + ",");
			System.out.print(card.getCard_number() + ",");
			System.out.print(card.getCard_date() + ",");
			System.out.print(card.getCard_last() + ",");
			System.out.print(card.getCard_default() + ",");
			System.out.println();
		}
	}

}
