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

public class ShowProductController {
	DAO dao;
	ArrayList<ShowProduct> showproducts;

	public ShowProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ShowProduct> getShowProducts() {
		return showproducts;
	}

	public void setProducts(ArrayList<ShowProduct> products) {
		this.showproducts = showproducts;
	}

	public String loadShowProducts(int storeId) {
		System.out.println("In loadShowProducts()");
		try {
			showproducts = dao.loadShowProducts(storeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	

	
}
