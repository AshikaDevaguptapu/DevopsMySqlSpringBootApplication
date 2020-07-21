package com.springboot.mysql.docker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transid;
	private String toaccount;
	private String fromaccount;
	private Long amount;
	
	
	public Transaction() {
		super();
		System.out.println(" i am not workinggggggggggggggggggg ");
		// TODO Auto-generated constructor stub
	}
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}
	public String getToaccount() {
		return toaccount;
	}
	public void setToaccount(String toaccount) {
		this.toaccount = toaccount;
	}
	public String getFromaccount() {
		return fromaccount;
	}
	public void setFromaccount(String fromaccount) {
		this.fromaccount = fromaccount;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
