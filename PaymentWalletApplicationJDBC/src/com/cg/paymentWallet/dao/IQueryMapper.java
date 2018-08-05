package com.cg.paymentWallet.dao;

public interface IQueryMapper {

	public String INSERT_QUERY = "Insert into PaymentWallet values(?,?,?,?)";
	public String SHOW_BALANCE = "Select balance from PaymentWallet where mobileNo=?";
	public String UPDATE="update PaymentWallet set balance=? where mobileNo=?";
	public String GET_ALL_DETAILS="Select * from PaymentWallet where mobileNo=?";

}
