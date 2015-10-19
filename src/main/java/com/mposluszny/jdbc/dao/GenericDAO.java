package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GenericDAO<T> {

	protected final String tableName;
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement preparedStatement;
	protected ResultSet rs;
	protected String query;
	
	protected GenericDAO(Connection connection, Statement statement, PreparedStatement preparedStatement, ResultSet rs, String query, String tableName) {
		
		this.connection = connection;
		this.statement = statement;
		this.preparedStatement = preparedStatement;
		this.rs = rs;
		this.query = query;
		this.tableName = tableName;
	}
	
	public int count() throws SQLException {
		
		try {
			
			query = "SELECT COUNT(*) FROM " + tableName + ";";
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			rs.next();
	
			return rs.getInt("C1");
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		throw new SQLException("Something went wrong while trying to count rows in " + tableName + " table;");
	}
	
}
