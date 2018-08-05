package com.cg.paytmwallet.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AccountApp1 {
	@Id
	private String mobileNo;
private String name;
private String emailid;
private double balance;



public AccountApp1() {
	super();
}


public AccountApp1(String name, String emailid, double balance, String mobileNo) {
	super();
	this.name = name;
	this.emailid = emailid;
	this.balance = balance;
	this.mobileNo = mobileNo;
}


public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}



}
