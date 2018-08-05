package com.cg.paymentWallet.test;

import static org.junit.Assert.*;
import com.cg.paymentWallet.Service.AccountService;
import com.cg.paymentWallet.Service.IAccountService;
import com.cg.paymentWallet.bean.Account;
import com.cg.paymentWallet.exception.PaymentWalletException;

public class PaymentWalletAppTest {

	IAccountService accountService = new AccountService();
	public void testCreateAccountforMobile(){
		Account account = new Account("919191", "Amrita", "amrita@cg.com", 200.0);
		try {
			accountService.createAccount(account);
		} catch (PaymentWalletException e) {
			
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}

}
