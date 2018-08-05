package com.cg.paymentWallet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.cg.paymentWallet.exception.PaymentWalletException;

public class Dbutil {
public static Connection getConnection() throws PaymentWalletException{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url,"system","orcl11g");
		
	} catch (ClassNotFoundException e) {
		
		throw new PaymentWalletException(e.getMessage());
	} catch (SQLException e) {
		
		throw new PaymentWalletException(e.getMessage());
	}
	
	
}
}
