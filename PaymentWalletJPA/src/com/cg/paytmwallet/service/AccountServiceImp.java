 package com.cg.paytmwallet.service;



import com.cg.paytmwallet.bean.AccountApp1;
import com.cg.paytmwallet.dao.AccountDao;
import com.cg.paytmwallet.dao.AccountDaoImp;
import com.cg.paytmwallet.exception.AccountException;

public class AccountServiceImp implements AccountService {
	
	AccountDao accDao=new AccountDaoImp();
	
	@Override
	public String createAccount(AccountApp1 account) throws AccountException {
		if(validateNum(account.getMobileNo())&&validateName(account.getName())&&
				validateEmail(account.getEmailid())&&validateamount(account.getBalance()))
				{
				return accDao.createAccount(account);	
				}
		else{
			throw new AccountException("Invalid Inputs to create account");
		}
		
	}
    @Override
	public double showBalance(String mobileNo) throws AccountException {
		if(!validateNum(mobileNo))
		{
			throw new AccountException("Invalid inputs");
		}
		return accDao.showBalance(mobileNo);
	}

	@Override
	public double deposit(String mobileNo, double amount)
			throws AccountException {
		if(validateNum(mobileNo )&&validateamount(amount))
		{
			return accDao.deposit(mobileNo, amount);
		}
		else
		{
			throw new AccountException("Invalid inputs");
		}
	}

	@Override
	public double withdraw(String mobileNo, double amount)
			throws AccountException {
	
		if(validateNum(mobileNo )&&validateamount(amount))
		{
			return accDao.withdraw(mobileNo, amount);
		}
		else
		{
			throw new AccountException("Invalid inputs");
		}
	}

	@Override
	public boolean fundTransfer(String mobileNo1, String mobileNo2, double amount)
			throws AccountException {
		if(validateNum(mobileNo1 )&&validateNum(mobileNo2)&&validateamount(amount))
		{
			return accDao.fundTransfer(mobileNo1,mobileNo2, amount);
		}
		else
		{
			throw new AccountException("Invalid inputs");
		}
	}
	private boolean validateNum(String mobileNo) throws AccountException
	{
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException
			("Mobile Number should contain 10 digits");
		}
		return true;
		
	}
	private boolean validateName(String name) throws AccountException
	{
		if(!name.matches("[a-z]{3,}")){
				throw new AccountException
				("Name should contain only alphabets");
			}
		return true;
	}
	private boolean validateEmail(String mailId) throws AccountException
	{
		if(!mailId.matches("[a-z]{4,10}@{1}[a-z]{5}.com{1}"))
		{
			throw new AccountException("Invalid mailId");
		}
		return true;
		
	}
	private boolean validateamount(double amount) throws AccountException
	{
		if((amount<=0))
		{
			throw new AccountException("Enter Valid Amount should be greater than zero");
		}
		return true;
		
	}
	@Override
	public AccountApp1 printTransactions(String mobileNo) throws AccountException {
		if(validateNum(mobileNo))
		{
			return accDao.printTransactions(mobileNo);
		}
		else
		{
			throw new AccountException("Invalid inputs");
		}
	}

	

	

}
