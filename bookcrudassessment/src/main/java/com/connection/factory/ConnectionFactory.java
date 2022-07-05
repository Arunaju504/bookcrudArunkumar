package com.connection.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	
	
	
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con= null;
		ResourceBundle label =ResourceBundle.getBundle("dbconnect");
		String url =label.getString("datasource.postgres.url");
		
		String userName =label.getString("datasource.postgres.username");
		String password =label.getString("datasource.postgres.password");
		String driverClass =label.getString("datasource.postgres.driverclass");
		Class.forName(driverClass);
		con =DriverManager.getConnection(url,userName,password);
				return con;
		
	}

}
