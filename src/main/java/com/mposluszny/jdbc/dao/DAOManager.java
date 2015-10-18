package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager {

	private final String URL = "jdbc:hsqldb:hsql://localhost/";
	private final String USERNAME = "mposluszny";
	private final String PASSWORD = "admin";
	Connection connection;
	
	private DAOManager() {
		
		
	}
	
	public static DAOManager getInstance() {
		
		return DAOManagerSingleton.INSTANCE.get();
	}
	
	public void open() {
		
		try {
			
			if (this.connection == null || this.connection.isClosed()) {
				
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void close() {
		
		try {
			
			if (connection != null && !this.connection.isClosed()) {
				
				connection.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        
        static {
        	
            ThreadLocal<DAOManager> daoManager;
            
            try {
            	
                daoManager = new ThreadLocal<DAOManager>() {
                	
                    @Override
                    protected DAOManager initialValue() {
                    	
                        try {
                            return new DAOManager();
                        }
                        
                        catch(Exception e) {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e) {
            	
            	daoManager = null;
            }
            
            INSTANCE = daoManager;
        }        

    }
}
