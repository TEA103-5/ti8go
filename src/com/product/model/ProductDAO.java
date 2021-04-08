package com.product.model;

<<<<<<< HEAD
import java.sql.Connection;

=======


import java.sql.Connection;
import java.sql.DriverManager;
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ProductDAO implements ProductDAO_Interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//單獨更新照片
	private static final String UPDATEP = 
			"UPDATE product set product_pic=? where product_id = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (PRODUCT_Name,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id) VALUES (? ,? ,? ,? ,? ,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT product_id ,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id,product_update_time FROM PRODUCT where product_id = ? ";
	private static final String GET_ALL_STMT = 
			"SELECT product_id,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id,product_update_time FROM PRODUCT";
	private static final String UPDATE = 
			"UPDATE PRODUCT set PRODUCT_Name=?,PRODUCT_Status=?,PRODUCT_Content=?,PRODUCT_Description=?,PRODUCT_Categories=?,PRODUCT_Price=?,PRODUCT_Stock=?  where product_id = ?";
	private static final String DELETE = 
			"DELETE FROM PRODUCT where product_id = ?";

	
	@Override
	public void updatep(ProductVO saleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEP);
			
			pstmt.setBytes(1, saleVO.getProduct_pic());
			pstmt.setInt(2, saleVO.getProduct_id());
			
			pstmt.executeUpdate();
			}catch (SQLException se) {
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
	 public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_name());
//			pstmt.setInt(2, productVO.getProduct_status());
			pstmt.setString(2, productVO.getProduct_content());
			pstmt.setString(3, productVO.getProduct_description());
			pstmt.setString(4, productVO.getProduct_categories());
			pstmt.setInt(5, productVO.getProduct_price());
			pstmt.setInt(6, productVO.getProduct_stock());
			pstmt.setInt(7, productVO.getSale_id());
			
			pstmt.executeUpdate();
			}catch (SQLException se) {
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
     public void update(ProductVO productVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, productVO.getProduct_name());
				pstmt.setInt(2, productVO.getProduct_status());
				pstmt.setString(3, productVO.getProduct_content());
				pstmt.setString(4, productVO.getProduct_description());
				pstmt.setString(5, productVO.getProduct_categories());
				pstmt.setInt(6, productVO.getProduct_price());
				pstmt.setInt(7, productVO.getProduct_stock());
				
				pstmt.setInt(8, productVO.getProduct_id());
				
				pstmt.executeUpdate();
			}catch (SQLException se) {
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
     public void delete(Integer product_id) {
		 Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1, product_id);

				pstmt.executeUpdate();
				
			}catch (SQLException se) {
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
     public ProductVO findByPrimaryKey(Integer product_id) {
			ProductVO productVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,product_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					productVO = new ProductVO();
					productVO.setProduct_id(rs.getInt("PRODUCT_Id"));
					productVO.setProduct_name(rs.getString("PRODUCT_Name"));
					productVO.setProduct_status(rs.getInt("PRODUCT_Status"));
					productVO.setProduct_content(rs.getString("Product_content"));
					productVO.setProduct_description(rs.getString("Product_description"));
					productVO.setProduct_categories(rs.getString("Product_categories"));
					productVO.setProduct_price(rs.getInt("Product_price"));
					productVO.setProduct_stock(rs.getInt("Product_stock"));
					productVO.setSale_id(rs.getInt("sale_id"));
					productVO.setProduct_update_time(rs.getTimestamp("product_update_time"));
				}
			}  catch (SQLException e) {
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
			return productVO;
     }
     @Override
     public List<ProductVO> getAll(){
    	 
    	 List<ProductVO> list = new ArrayList<ProductVO>();
 		ProductVO productVO = null;

 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;

 		try {
 			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

 			while (rs.next()) {
 				productVO = new ProductVO();
 				productVO.setProduct_id(rs.getInt("PRODUCT_Id"));
 				productVO.setProduct_name(rs.getString("PRODUCT_Name"));
 				productVO.setProduct_status(rs.getInt("PRODUCT_Status"));
 				productVO.setProduct_content(rs.getString("Product_content"));
 				productVO.setProduct_description(rs.getString("Product_description"));
 				productVO.setProduct_categories(rs.getString("Product_categories"));
 				productVO.setProduct_price(rs.getInt("Product_price"));
 				productVO.setProduct_stock(rs.getInt("Product_stock"));
 				productVO.setSale_id(rs.getInt("sale_id"));
 				productVO.setProduct_update_time(rs.getTimestamp("product_update_time"));
 				list.add(productVO);
 			}
 		}  catch (SQLException e) {
 			throw new RuntimeException("A database error occured. " + e.getMessage());
 		}

 		finally {
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
=======
//import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

public class ProductDAO implements ProductDAO_Interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tea05?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT (PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,product_pic) VALUES (?, ? ,? ,? ,? ,? ,?,?)";
	private static final String GET_ONE_STMT = "SELECT product_id ,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,product_update_time,product_pic FROM PRODUCT where product_id = ? ";
	private static final String GET_ALL_STMT = "SELECT product_id,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,product_update_time FROM PRODUCT";
	private static final String UPDATE = "UPDATE PRODUCT set PRODUCT_Name=?,PRODUCT_Status=?,PRODUCT_Content=?,PRODUCT_Description=?,PRODUCT_Categories=?,PRODUCT_Price=?,PRODUCT_Stock=?,product_pic=?  where product_id = ?";
	private static final String DELETE_PI = "DELETE FROM PRODUCT where product_id = ?";
	private static final String DELETE_OPI = "DELETE FROM ORDER_DETAIL where product_id = ?";
	
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setInt(2, productVO.getProduct_status());
			pstmt.setString(3, productVO.getProduct_content());
			pstmt.setString(4, productVO.getProduct_description());
			pstmt.setString(5, productVO.getProduct_categories());
			pstmt.setInt(6, productVO.getProduct_price());
			pstmt.setInt(7, productVO.getProduct_stock());
			pstmt.setBytes(8, productVO.getProduct_pic());
			
			System.out.println("成功");
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " 
					+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " 
					+ e.getMessage());
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

	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setInt(2, productVO.getProduct_status());
			pstmt.setString(3, productVO.getProduct_content());
			pstmt.setString(4, productVO.getProduct_description());
			pstmt.setString(5, productVO.getProduct_categories());
			pstmt.setInt(6, productVO.getProduct_price());
			pstmt.setInt(7, productVO.getProduct_stock());
			
			pstmt.setInt(8, productVO.getProduct_id());
			System.out.println("成功");
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " 
					+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " 
					+ e.getMessage());
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

	public void delete(Integer product_id) {
		int deleteCount_ODD = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			// 先刪訂單明細
			pstmt = con.prepareStatement(DELETE_OPI);
			pstmt.setInt(1, product_id);
			deleteCount_ODD = pstmt.executeUpdate();

			// 再刪商品
			pstmt = con.prepareStatement(DELETE_PI);
			pstmt.setInt(1, product_id);
			deleteCount_ODD = pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單明細編號" + product_id + "時,共有訂單明細" + deleteCount_ODD + "筆同時被刪除");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	


	public ProductVO findByPrimaryKey(Integer PRODUCT_Id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PRODUCT_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getInt("PRODUCT_Id"));
				productVO.setProduct_name(rs.getString("PRODUCT_Name"));
				productVO.setProduct_status(rs.getInt("PRODUCT_Status"));
				productVO.setProduct_content(rs.getString("Product_content"));
				productVO.setProduct_description(rs.getString("Product_description"));
				productVO.setProduct_categories(rs.getString("Product_categories"));
				productVO.setProduct_price(rs.getInt("Product_price"));
				productVO.setProduct_stock(rs.getInt("Product_stock"));
				
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
		return productVO;
	}

	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getInt("PRODUCT_Id"));
				productVO.setProduct_name(rs.getString("PRODUCT_Name"));
				productVO.setProduct_status(rs.getInt("PRODUCT_Status"));
				productVO.setProduct_content(rs.getString("Product_content"));
				productVO.setProduct_description(rs.getString("Product_description"));
				productVO.setProduct_categories(rs.getString("Product_categories"));
				productVO.setProduct_price(rs.getInt("Product_price"));
				productVO.setProduct_stock(rs.getInt("Product_stock"));
				list.add(productVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		finally {
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
		ProductDAO test = new ProductDAO();
//		 新增
		ProductVO vo = new ProductVO();       
		vo.setProduct_name("登山鞋");
		vo.setProduct_status(9);
		vo.setProduct_content("很讚");
		vo.setProduct_description("超棒");
		vo.setProduct_categories("登山");
		vo.setProduct_price(30);
		vo.setProduct_stock(30);
//		
		test.insert(vo);
//////		
		//修改
//		ProductVO vo = new ProductVO();
//		vo.setProduct_id(1);
//		vo.setProduct_name("登山杖");
//		vo.setProduct_status(2);
//		vo.setProduct_content("水喔");
//		vo.setProduct_description("超棒");
//		vo.setProduct_categories("山");
//		vo.setProduct_price(40);
//		vo.setProduct_stock(60);
//		
//		test.update(vo);
	
	//刪除
	//test.delete(6);
	
    // 查詢
//		ProductVO deptVO3 = test.findByPrimaryKey(8);
//		System.out.print(deptVO3.getProduct_id() + ",");
//		System.out.print(deptVO3.getProduct_name() + ",");
//		System.out.println(deptVO3.getProduct_status());
//		System.out.println(deptVO3.getProduct_content());
//		System.out.println(deptVO3.getProduct_description());
//		System.out.println(deptVO3.getProduct_categories());
//		System.out.println(deptVO3.getProduct_price());
//		System.out.println(deptVO3.getProduct_stock());
//		System.out.println("---------------------");
	
	 //查全部
//		List<ProductVO> list = test.getAll();
//		for (ProductVO aDept : list) {
//			System.out.print(aDept.getProduct_id() + ",");
//			System.out.print(aDept.getProduct_name() + ",");
//			System.out.print(aDept.getProduct_status() + ","	);
//			System.out.println(aDept.getProduct_content()+ ",");
//			System.out.println(aDept.getProduct_description()+ ",");
//			System.out.println(aDept.getProduct_categories()+ ",");
//			System.out.println(aDept.getProduct_price()+ ",");
//			System.out.println(aDept.getProduct_stock());
//			System.out.println();
//	
//		}
	
	}
	
	
	
	
}
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
