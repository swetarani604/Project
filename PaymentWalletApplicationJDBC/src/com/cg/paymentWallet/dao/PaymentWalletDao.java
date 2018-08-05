package com.cg.paymentWallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.paymentWallet.bean.PaymentWallet;
import com.cg.paymentWallet.exception.PaymentWalletException;
import com.cg.paymentWallet.util.Dbutil;

public class PaymentWalletDao implements IPaymentWalletDao {

	public String createAccount(PaymentWallet paymentWallet)
			throws PaymentWalletException {
		Connection con = Dbutil.getConnection();
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(IQueryMapper.INSERT_QUERY);
			stat.setString(1, paymentWallet.getCustomerName());
			stat.setString(2, paymentWallet.getMobileNo());
			stat.setString(3, paymentWallet.getEmailID());
			stat.setDouble(4, paymentWallet.getBalance());

			int result = stat.executeUpdate();
			if (result == 1) {
				con.commit();
				System.out.println(result + "inserted");
				return paymentWallet.getMobileNo();
			} else {
				throw new PaymentWalletException("Account already exist");
			}

		} catch (SQLException e) {

			throw new PaymentWalletException(e.getMessage());
		}

	}

	@Override
	public double showBalance(String mobileNo) throws PaymentWalletException {

		Connection con = Dbutil.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement stat = con
					.prepareStatement(IQueryMapper.SHOW_BALANCE);
			stat.setString(1, mobileNo);
			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				con.commit();
				return rs.getDouble(1);
			} else {
				throw new PaymentWalletException("Mobile number does not exist");
			}

		} catch (SQLException e) {

			throw new PaymentWalletException(e.getMessage());
		}

	}

	@Override
	public double deposit(String mobileNo, double depositAmount)
			throws PaymentWalletException {
		Connection con = Dbutil.getConnection();
		PreparedStatement stat;

		try {
			double balance = showBalance(mobileNo);
			stat = con.prepareStatement(IQueryMapper.UPDATE);
			double newBalance = balance + depositAmount;
			stat.setDouble(1, newBalance);
			stat.setString(2, mobileNo);
			int rs = stat.executeUpdate();

			if (rs == 1) {
				con.commit();
				return newBalance;
			} else {
				throw new PaymentWalletException("balance not updated");
			}
		} catch (SQLException e) {
			throw new PaymentWalletException("Account does not exist");

		}

	}

	@Override
	public double withdraw(String mobileNo, double withdrawAmount)
			throws PaymentWalletException {
		Connection connect = Dbutil.getConnection();
		PreparedStatement statement;
		PreparedStatement statement1;
		double balance1 = 0;
		try {
			statement = connect.prepareStatement(IQueryMapper.GET_ALL_DETAILS);
			statement.setString(1, mobileNo);
			ResultSet res = statement.executeQuery();
			connect.commit();
			if (res != null) {

				res.next();
				PaymentWallet acc = new PaymentWallet();
				if (res.getDouble("balance") < withdrawAmount) {
					throw new PaymentWalletException(
							"Enter amount less than existing amount");
				} else {
					double balance = res.getDouble("balance") - withdrawAmount;
					acc.setCustomerName(res.getString("name"));
					acc.setEmailID(res.getString("email"));
					acc.setMobileNo(res.getString("mobileno"));
					acc.setBalance(balance);
					statement1 = connect.prepareStatement(IQueryMapper.UPDATE);
					statement1.setDouble(1, acc.getBalance());
					statement1.setString(2, acc.getMobileNo());
					int rs = statement1.executeUpdate();
					if (rs == 1) {
						connect.commit();
						balance1 = acc.getBalance();
					}

					else {
						throw new PaymentWalletException(
								"Account with this mobileno does not exist");
					}
					return balance1;
				}
			} else {
				throw new PaymentWalletException(
						"Account with this mobileno does not exist");
			}
		} catch (SQLException e) {

			throw new PaymentWalletException(e.getMessage());
		}
	}

	@Override
	public double fundTransfer(String firstMobileNo, String secondMobileNo,
			double amountToTransfer) throws PaymentWalletException {

		if (firstMobileNo == secondMobileNo || firstMobileNo == null
				|| secondMobileNo == null) {
			throw new PaymentWalletException(
					"ERROR: Fund transfer is not possible");
		}
		double balance = withdraw(firstMobileNo, amountToTransfer);
		deposit(secondMobileNo, amountToTransfer);
		System.out.println(balance);
		return balance;
	}

	@Override
	public PaymentWallet printTransaction(String mobileNo)
			throws PaymentWalletException {

		PaymentWallet pw = new PaymentWallet();
		Connection con = Dbutil.getConnection();
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(IQueryMapper.GET_ALL_DETAILS);
			stat.setString(1, mobileNo);
			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				con.commit();
				pw.setCustomerName(rs.getString(1));
				pw.setEmailID(rs.getString(2));
				pw.setMobileNo(rs.getString(3));
				pw.setBalance(rs.getDouble(4));
				return pw;
			} else {
				throw new PaymentWalletException(
						"Unable to perform transaction");
			}

		} catch (SQLException e) {
			throw new PaymentWalletException("Account not available");
		}

	}

}
