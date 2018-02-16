package org.ebrick.system.model;

public class Brick {
	
	private int id;
	private String type;
	private double price;
	private int qty;
	private int totQty;
	private int balQty;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public Brick(){};
	public Brick(int id, String type, double price,int qty,int totQty,int balQty) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.qty =qty;
		this.totQty = totQty;
		this.balQty = balQty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getBalQty() {
		return balQty;
	}
	public void setBalQty(int balQty) {
		this.balQty = balQty;
	}
	public int getTotQty() {
		return totQty;
	}
	public void setTotQty(int totQty) {
		this.totQty = totQty;
	}

}
