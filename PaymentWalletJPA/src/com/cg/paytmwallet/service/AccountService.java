package com.cg.paytmwallet.service;

import com.cg.paytmwallet.bean.AccountApp1;
import com.cg.paytmwallet.exception.AccountException;

public interface AccountService {
String createAccount(AccountApp1 account) throws AccountException;
double showBalance(String mobileNo) throws AccountException;
double deposit(String mobileNo,double amount) throws AccountException;
double withdraw(String mobileNo,double amount) throws AccountException;
boolean fundTransfer(String mobileNo1,String mobileNo2,double amount) throws AccountException;
AccountApp1 printTransactions(String mobileNo) throws AccountException;

}
