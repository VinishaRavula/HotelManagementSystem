package com.reservation.service.models;

public class TransactionDetails {
	
	
	private String orderId;
	private String currency;
	private Integer amount;
	private String status;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TransactionDetails(String orderId, String currency, Integer amount, String status) {
		super();
		this.orderId = orderId;
		this.currency = currency;
		this.amount = amount;
		this.status = status;
	}
	public TransactionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TransactionDetails [orderId=" + orderId + ", currency=" + currency + ", amount=" + amount + ", status="
				+ status + "]";
	}
	
	

}
