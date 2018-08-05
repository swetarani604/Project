package com.cg.paymentWallet.Service;



import com.cg.paymentWallet.bean.PaymentWallet;
import com.cg.paymentWallet.dao.IPaymentWalletDao;
import com.cg.paymentWallet.dao.PaymentWalletDao;
import com.cg.paymentWallet.exception.PaymentWalletException;

public class PaymentWalletService implements IPaymentWalletService{

	IPaymentWalletDao paymentWalletDao = new PaymentWalletDao();
	@Override
	public String createAccount(PaymentWallet paymentWallet) throws PaymentWalletException {
		if(validateMobile(paymentWallet.getMobileNo()) 
				&& validateName(paymentWallet.getCustomerName())
				&& validateEmailID(paymentWallet.getEmailID())
				&& validateBalance(paymentWallet.getBalance())){
			
			return paymentWalletDao.createAccount(paymentWallet);
			
		}
		return paymentWalletDao.createAccount(paymentWallet);
		
		}	
		
	
	private boolean validateName(String name)throws PaymentWalletException{
		if(name.isEmpty() || name==null || name==" "){
			throw new PaymentWalletException("Name cannot be empty");
		}else 
			if(!name.matches("[A-Z][A-Za-z]{3,}")){
				throw new PaymentWalletException("Name should be capital letter and should contain only albhabet");
			
		}
		return true;
	}
	private boolean validateBalance(double balance)throws PaymentWalletException{
		if(balance<=0){
			throw new PaymentWalletException("Balance should be more than zero");
		}
		return true;
	}
	
	private boolean validateMobile(String mobileNo) throws PaymentWalletException{
		if(!mobileNo.matches("\\d{10}")){
			throw new PaymentWalletException("Mobile Number should contain 10 digits");
	}
		return true;
	}
	private boolean validateEmailID(String email)throws PaymentWalletException{
		if(!email.matches("[a-z0-9]+@[a-z]+\\.com")){
			throw new PaymentWalletException("Enter valid emailID");
		}
		return true;
	}
	@Override
	public double showBalance(String mobileNo) throws PaymentWalletException {
		if(!validateMobile(mobileNo)){
			throw new PaymentWalletException("Enter valid mobile number");
		}
		return paymentWalletDao.showBalance(mobileNo);
	}
	@Override
	public double deposit(String mobileNo, double depositAmount)
			throws PaymentWalletException {
		if(validateMobile(mobileNo) && (validateBalance(depositAmount))){
			return paymentWalletDao.deposit(mobileNo, depositAmount);
		}
		throw new PaymentWalletException();
	}


	@Override
	public double withdraw(String mobileNo, double withdrawAmount)
			throws PaymentWalletException {
		
		if(validateMobile(mobileNo) && (validateBalance(withdrawAmount))){
			return paymentWalletDao.withdraw(mobileNo, withdrawAmount);
		
		}
		throw new PaymentWalletException();
	}

	
	@Override
	public double fundTransfer(String firstMobileNo, String secondMobileNo,
			double amountToTransfer) throws PaymentWalletException {
		if(validateMobile(firstMobileNo) && validateMobile(secondMobileNo)
				&& validateBalance(amountToTransfer)){
		return paymentWalletDao.fundTransfer(firstMobileNo, secondMobileNo, amountToTransfer);
		}
		throw new PaymentWalletException();
		
	}


	@Override
	public PaymentWallet printTransaction(String mobileNo)
			throws PaymentWalletException {
	if (!validateMobile(mobileNo)){
		throw new PaymentWalletException("transaction details not available");
	}
	return paymentWalletDao.printTransaction(mobileNo);
	}

}
