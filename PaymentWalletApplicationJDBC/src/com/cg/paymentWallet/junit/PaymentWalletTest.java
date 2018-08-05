package com.cg.paymentWallet.junit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cg.paymentWallet.Service.IPaymentWalletService;
import com.cg.paymentWallet.Service.PaymentWalletService;
import com.cg.paymentWallet.bean.PaymentWallet;
import com.cg.paymentWallet.exception.PaymentWalletException;

public class PaymentWalletTest {
	IPaymentWalletService paymentService = new PaymentWalletService();
	@Test
	public void testCreateAccountToCheckMobileNo() {
		
		
		PaymentWallet paymentWallet = new PaymentWallet("Radha","1234567","amrita@cg.com", 200.0);
			try {
				paymentService.createAccount(paymentWallet);
			} catch (PaymentWalletException e) {
				
				Assert.assertEquals("Mobile Number should contain 10 digits",e.getMessage());
				
			}
		}
	@Test
	public void testCreateAccountToCheckName() {
		
		
		PaymentWallet paymentWallet = new PaymentWallet("radha","1234567890","amrita@cg.com", 200.0);
		try {
			paymentService.createAccount(paymentWallet);
		} catch (PaymentWalletException e) {
		
			Assert.assertEquals("Name should be capital letter and should contain only albhabet",e.getMessage());
			
		}
	}
	@Test
	public void testCreateAccountToCheckNameEmpty() {
		
		
		PaymentWallet paymentWallet = new PaymentWallet(" ","1234567890","amrita@cg.com", 200.0);
		try {
			paymentService.createAccount(paymentWallet);
			
		} catch (PaymentWalletException e) {
			
			
			Assert.assertEquals("Name cannot be empty",e.getMessage());
			
		}
	}
	@Test
public void testCreateAccountToCheckEmailID() {
		
		
		PaymentWallet paymentWallet = new PaymentWallet("Radha","1234567890","amrita", 200.0);
		try {
			paymentService.createAccount(paymentWallet);
			
		} catch (PaymentWalletException e) {
			
			
			Assert.assertEquals("Enter valid emailID",e.getMessage());
			
		}
	}
	@Test
	public void testCreateAccountToCheckBalance() {
			
			
		PaymentWallet paymentWallet = new PaymentWallet("Radha","1234567890","amrita@cg.com", 0.0);
			try {
				paymentService.createAccount(paymentWallet);
			
			} catch (PaymentWalletException e) {
				
				
				Assert.assertEquals("Balance should be more than zero",e.getMessage());
				
			}
		}
	
	@Test
	public void testCreateAccountToCheckSuccess(){
		PaymentWallet paymentWallet = new PaymentWallet("Sameer","9891023213", "sameer@cg.com", 45000);
		try {
			String result=paymentService.createAccount(paymentWallet);
			Assert.assertNotNull(result);
			
			
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

	@Test
	public void testShowBalance() {
		try {
			paymentService.showBalance("9898");
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
			
		}
	}
	/*@Test
	public void testShowBalanceToCheckMobileNotExist() {
		try {
			paymentService.showBalance("9898989898");
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Account with the provided mobile number 9898989898 does not exist", e.getMessage());
			
		}
	}*/
	@Test
	public void testShowBalanceToDisplayBalance() {
		PaymentWallet paymentWallet = new PaymentWallet();
		paymentWallet.setMobileNo("8781880192");
		try {
			double amount=paymentService.showBalance(paymentWallet.getMobileNo());
			assertEquals(39000,amount,0);
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
		}
		
			
		}
	


	@Test
	public void testWithdrawToCheckMobileNo() {
		PaymentWallet paymentWallet = new PaymentWallet();
		paymentWallet.setMobileNo("9090");
		try {
			paymentService.withdraw(paymentWallet.getMobileNo(), 2000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
		}
	}
	/*@Test
	public void testWithdrawToCheckWithdrawAmountLessThanBalance() {
		try {
			double balance=paymentService.withdraw("9891023213", 678000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Enter withdraw amount less than available balance", e.getMessage());
		}
	}*/
	@Test
	public void testWithdrawToCheckWithdrawAmountZero() {
		try {
			double balance=paymentService.withdraw("8891023287",-900);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Balance should be more than zero", e.getMessage());
		}
	}
	@Test
	public void testWithdrawToCheckWithdrawSuccess() {
		try {
			double balance=paymentService.withdraw("9791918991",9000);
			Assert.assertEquals(42000.0, balance,0.0);
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testDepositToCheckMobileNo() {
		PaymentWallet paymentWallet = new PaymentWallet();
		paymentWallet.setMobileNo("9090");
		try {
			paymentService.deposit(paymentWallet.getMobileNo(), 2000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testDepositToCheckAmountZero() {
		try {
			double balance=paymentService.deposit("9791918919",-900);
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals("Balance should be more than zero", e.getMessage());
		}
	}
	@Test
	public void testDepositToCheckDepositSuccess() {
		try {
			double balance=paymentService.deposit("9891023290",11999);
			Assert.assertEquals(49000.0, balance,0.0);
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
		}
	}


	@Test
	public void testFundTransferToCheckFirstMobile() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("9090");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("9090909090");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(), 2000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
		}
	}
	@Test
	public void testFundTransferToCheckSecondMobile() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("1234567890");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("909090");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(), 2000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
		}
	}
	@Test
	public void testFundTransferToCheckAmount() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("8781880192");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("9891023290");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(),0);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("Balance should be more than zero", e.getMessage());
		}
	}
	/*@Test
	public void testFundTransferToCheckAccountTransferPossiblity() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("8781880192");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("9891023290");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(),89000);
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals("Enter withdraw amount less than available balance", e.getMessage());
		}
	}*/
	@Test
	public void testFundTransferToCheckAccountTransferZero() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("8851023246");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("7851023245");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(),-9000);
		} catch (PaymentWalletException e) {

			Assert.assertEquals("Balance should be more than zero", e.getMessage());
		}
	}
	@Test
	public void testFundTransferToCheckAccountSame() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("7851023245");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("7851023245");
		try {
			paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(),9000);
		} catch (PaymentWalletException e) {
			
			Assert.assertEquals("ERROR: Fund transfer is not possible", e.getMessage());
		}
	}
	@Test
	public void testFundTransferToCheckAccountTransferSuccess() {
		PaymentWallet paymentWallet1 = new PaymentWallet();
		paymentWallet1.setMobileNo("8851023246");
		PaymentWallet paymentWallet2 = new PaymentWallet();
		paymentWallet2.setMobileNo("7851023245");
		try {
			double balance=paymentService.fundTransfer(paymentWallet1.getMobileNo(),paymentWallet2.getMobileNo(),21000);
		assertEquals(18000.0,balance,0.0);
		} catch (PaymentWalletException e) {
			
			System.out.println(e.getMessage());
		}
	}
		@Test
		public void TestPrintTransactionToCheckName(){
			PaymentWallet paymentWallet = new PaymentWallet();
			paymentWallet.setMobileNo("7851023245");
			try {
				PaymentWallet acc1 = paymentService.printTransaction(paymentWallet.getMobileNo());
				assertSame("Ratan", acc1.getCustomerName());
			} catch (PaymentWalletException e) {
				System.out.println(e.getMessage());
			}
		}
		@Test
		public void TestPrintTransactionToCheckBalance(){
			PaymentWallet paymentWallet = new PaymentWallet();
			paymentWallet.setMobileNo("7851023245");
			try {
				PaymentWallet acc1 = paymentService.printTransaction(paymentWallet.getMobileNo());
				Assert.assertEquals(78000.0,acc1.getBalance(),0.0);
			} catch (PaymentWalletException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		@Test
		public void TestPrintTransactionToCheckAccountNotNull(){
			PaymentWallet paymentWallet = new PaymentWallet();
			paymentWallet.setMobileNo("7851023245");
			try {
				PaymentWallet acc1 = paymentService.printTransaction(paymentWallet.getMobileNo());
				assertNotNull(acc1);
			} catch (PaymentWalletException e) {
				
				System.out.println(e.getMessage());
			}
		}
		@Test
		public void TestPrintTransactionToCheckMobileNo(){
			PaymentWallet paymentWallet = new PaymentWallet();
			paymentWallet.setMobileNo("785");
			try {
				PaymentWallet acc1 = paymentService.printTransaction(paymentWallet.getMobileNo());
				
			} catch (PaymentWalletException e) {
				Assert.assertEquals("Mobile Number should contain 10 digits", e.getMessage());
			}
		}
	

}
