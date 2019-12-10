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

public class StoreController {
	DAO dao;
	ArrayList<Store> stores;

	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> store) {
		this.stores = store;
	}

	public String loadStores() {
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	
	public String addStore(Store s) {
		System.out.println( s.getStoreName() + " " + s.getFounded() + " ");
		try {
			dao.addStore(s);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store Id already exist");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
