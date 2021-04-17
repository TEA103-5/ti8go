package com.product.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class ProductDAO implements ProductDAO_Interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//單獨更新照片
	private static final String UPDATEP = 
			"UPDATE product set product_pic=? where product_id = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (PRODUCT_Name,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,product_pic,sale_id,product_update_time) VALUES (?,? ,? ,? ,? ,? ,?,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT product_id ,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id,product_update_time FROM PRODUCT where product_id = ? ";
	private static final String GET_ALL_STMT = 
			"SELECT product_id,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id,product_update_time FROM PRODUCT";
	private static final String UPDATE = 
			"UPDATE PRODUCT set PRODUCT_Name=?,PRODUCT_Status=?,PRODUCT_Content=?,PRODUCT_Description=?,PRODUCT_Categories=?,PRODUCT_Price=?,PRODUCT_Stock=?  where product_id = ?";
	private static final String DELETE = 
			"DELETE FROM PRODUCT where product_id = ?";
	private static final String GET_ONE_STMT_t = 
			"SELECT product_id ,PRODUCT_Name,PRODUCT_Status,PRODUCT_Content,PRODUCT_Description,PRODUCT_Categories,PRODUCT_Price,PRODUCT_Stock,sale_id,product_update_time FROM PRODUCT where product_update_time = ? ";

	
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
			pstmt.setString(2, productVO.getProduct_content());
			pstmt.setString(3, productVO.getProduct_description());
			pstmt.setString(4, productVO.getProduct_categories());
			pstmt.setInt(5, productVO.getProduct_price());
			pstmt.setInt(6, productVO.getProduct_stock());
			pstmt.setBytes(7, productVO.getProduct_pic());
			pstmt.setInt(8, productVO.getSale_id());
			pstmt.setTimestamp(9, productVO.getProduct_update_time());
			
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
	 public ProductVO findByt(Timestamp t) {
		 ProductVO productVO = null;
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(GET_ONE_STMT_t);
			 
			 pstmt.setTimestamp(1, t);
			 
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
