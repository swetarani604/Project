package com.cg.paymentWallet.bean;

public class PaymentWallet {
private String customerName;
private String mobileNo;
private String emailID;
private double balance;
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getEmailID() {
	return emailID;
}
public void setEmailID(String emailID) {
	this.emailID = emailID;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public PaymentWallet(String customerName, String mobileNo, String emailID,
		double balance) {
	super();
	this.customerName = customerName;
	this.mobileNo = mobileNo;
	this.emailID = emailID;
	this.balance = balance;
}
public PaymentWallet() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "PaymentWallet [customerName=" + customerName + ", mobileNo="
			+ mobileNo + ", emailID=" + emailID + ", balance=" + balance + "]";
}


}