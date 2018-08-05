package com.cg.paytmwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.paytmwallet.bean.AccountApp1;
import com.cg.paytmwallet.dao.AccountDao;
import com.cg.paytmwallet.exception.AccountException;


public class AccountDaoImp implements AccountDao {
	
	EntityManagerFactory fact=Persistence.createEntityManagerFactory("JPA-PU");
	EntityManager em=fact.createEntityManager();
	
	@Override
	public String createAccount(AccountApp1 account) throws AccountException {
	em.getTransaction().begin();
	em.persist(account);
	em.getTransaction().commit();
	return account.getMobileNo();		
	}
   @Override
	public double showBalance(String mobileNo) throws AccountException {
	   String strqry="select e from AccountApp1 e where e.mobileNo=?";
		TypedQuery<AccountApp1> query=em.createQuery(strqry,AccountApp1.class);
		query.setParameter(1,mobileNo);
		AccountApp1 acc=query.getSingleResult();
		if(acc!=null){
			return acc.getBalance();
		}
		else{
	throw new AccountException("the mobile number is not there in the data base");
}
		
	} 
	
@Override
	public double deposit(String mobileNo, double amount)
			throws AccountException {
	String strqry="select e from AccountApp1 e where e.mobileNo=?";
	TypedQuery<AccountApp1> query=em.createQuery(strqry,AccountApp1.class);
	query.setParameter(1,mobileNo);
	AccountApp1 acc=query.getSingleResult();
	double bal=acc.getBalance()+amount;
	acc.setBalance(bal);
	em.getTransaction().begin();
	em.merge(acc);
	em.getTransaction().commit();
	return acc.getBalance();
}
@Override
	public double withdraw(String mobileNo, double amount)
			throws AccountException {
	String strqry="select e from AccountApp1 e where e.mobileNo=?";
	TypedQuery<AccountApp1> query=em.createQuery(strqry,AccountApp1.class);
	query.setParameter(1,mobileNo);
	AccountApp1 acc=query.getSingleResult();
	if(acc.getBalance()>amount)
	{
	double bal=acc.getBalance()-amount;
	acc.setBalance(bal);
	em.getTransaction().begin();
	em.merge(acc);
	em.getTransaction().commit();
	}else
	{
		throw new AccountException("Enter amount less than existing amount");
	}
	return acc.getBalance();
	}
@Override
	public boolean fundTransfer(String mobileNo1, String mobileNo2, double amount)
			throws AccountException {
		deposit(mobileNo1, amount);
	    withdraw(mobileNo2, amount);
		return true;
			}

	@Override
	public AccountApp1 printTransactions(String mobileNo) throws AccountException {
		String strqry="select e from AccountApp1 e where e.mobileNo=?";
		TypedQuery<AccountApp1> query=em.createQuery(strqry,AccountApp1.class);
		query.setParameter(1,mobileNo);
		AccountApp1 acc=query.getSingleResult();
		if(acc==null){
			throw new AccountException("the mobile number is not there in the data base");
		}
		return acc;
	}

}
