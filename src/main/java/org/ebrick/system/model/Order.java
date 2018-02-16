package org.ebrick.system.model;

import java.util.Date;
import java.util.List;

public class Order {
	
	private int orderId;
	private int qty;
	private double price;
	private String date;
	private String status;
	private Brick brick;
	private Organisation organisation;
	private List<Customer> customers;
	
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public Order(){};
	public Order(int id, int qty, double price,String date, String status, 
			Organisation organisation) {
		super();
		this.setOrderId(id);
		this.qty = qty;
		this.price = price;
		this.date=date;
		this.status = status;
		this.organisation = organisation;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	public Brick getBrick() {
		return brick;
	}
	public void setBrick(Brick brick) {
		this.brick = brick;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
