package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DAO {

	private DataSource mysqlDS;

	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<Store> loadStores() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setStoreId(myRs.getInt("id"));
		s.setStoreName(myRs.getString("name"));
		s.setFounded(myRs.getString("founded"));
			stores.add(s);
		}
		return stores;
	}
	
	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getStoreId());
		myStmt.setString(2, store.getStoreName());
		myStmt.setString(3, store.getFounded());
		myStmt.execute();				
	}
	
	
	
	public ArrayList<Product> loadProducts() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setPid(myRs.getInt("PID"));
			p.setSid(myRs.getInt("SID"));
			p.setProdName(myRs.getString("PRODNAME"));
			p.setPrice(myRs.getDouble("PRICE"));
			products.add(p);
		}
		return products;
	}
	
	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into product values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getPid());
		myStmt.setInt(2, product.getSid());
		myStmt.setString(3, product.getProdName());
		myStmt.setDouble(4, product.getPrice());
		myStmt.execute();				
	}
	
	public ArrayList<ShowProduct> loadShowProducts(int storeId) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select p.pid, p.prodName, p.price, s.name, s.founded, s.id from product p inner join stores on p.sid = s.storeId where p.sid like "+storeId;

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<ShowProduct> showproducts = new ArrayList<ShowProduct>();

		// process result set
		while (myRs.next()) {
			ShowProduct sp = new ShowProduct();
			sp.setPid(myRs.getInt("pid"));
			sp.setProdName(myRs.getString("Product Name"));
			sp.setPrice(myRs.getDouble("Price"));
		sp.setStoreName(myRs.getString("name"));
		sp.setFounded(myRs.getString("founded"));
		sp.setStoreId(myRs.getInt("id"));
		}
		return showproducts;
	}
	
	

}

