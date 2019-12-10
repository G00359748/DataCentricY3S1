package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProductController {
	DAO dao;
	ArrayList<Product> products;

	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String loadProducts() {
		System.out.println("In loadProducts()");
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public String addProduct(Product p) {
		System.out.println(p.getPid() + " " + p.getSid() + " " + p.getProdName() + " " + p.getPrice());
		try {
			dao.addProduct(p);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Product Id already exist");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	
	
}
