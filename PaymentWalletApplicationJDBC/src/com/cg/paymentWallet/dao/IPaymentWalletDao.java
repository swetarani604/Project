package com.cg.paymentWallet.dao;


import com.cg.paymentWallet.bean.PaymentWallet;
import com.cg.paymentWallet.exception.PaymentWalletException;

public interface IPaymentWalletDao {
public String createAccount(PaymentWallet account) throws PaymentWalletException;
public double showBalance(String mobileNo) throws PaymentWalletException;
double deposit(String mobileNo, double depositAmount) throws PaymentWalletException;
double withdraw(String mobileNo, double withdrawAmount) throws PaymentWalletException;
public double fundTransfer(String firstMobileNo,String secondMobileNo,double amountToTransfer) 
		throws PaymentWalletException;
public PaymentWallet printTransaction(String mobileNo) throws PaymentWalletException;
}
