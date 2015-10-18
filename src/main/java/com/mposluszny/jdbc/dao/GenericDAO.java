package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class GenericDAO<T> {
	
	public abstract int count() throws SQLException;

	protected final String tableName;
	protected Connection connection;
	
	protected GenericDAO(Connection connection, String tableName) {
		
		this.connection = connection;
		this.tableName = tableName;
	}
	
}
