package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PendingOrderTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private double buyerPrice;
	private int buyerQty;
	private double sellerPrice;
	private int sellerQty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBuyerPrice() {
		return buyerPrice;
	}

	public void setBuyerPrice(double buyerPrice) {
		this.buyerPrice = buyerPrice;
	}

	public int getBuyerQty() {
		return buyerQty;
	}

	public void setBuyerQty(int buyerQty) {
		this.buyerQty = buyerQty;
	}

	public double getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(double sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public int getSellerQty() {
		return sellerQty;
	}

	public void setSellerQty(int sellerQty) {
		this.sellerQty = sellerQty;
	}
	
}
