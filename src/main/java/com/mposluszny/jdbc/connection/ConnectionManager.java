package com.mposluszny.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection connection;
	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;
	
	public ConnectionManager() {
		
		URL = "jdbc:hsqldb:mem/esportplayersdb";
		USERNAME = "SA";
		PASSWORD = "";
	}
	
	public Connection getConnection () {
		
		return this.connection;
	}
	
	public void connect() {
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
