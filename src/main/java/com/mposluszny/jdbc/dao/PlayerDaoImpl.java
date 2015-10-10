package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mposluszny.jdbc.Player;

public class PlayerDaoImpl implements PlayerDao {

	private Connection connection;
	private final String URL = "jdbc:hsqldb:mem/esportplayersdb";
	private final String USERNAME = "SA";
	private final String PASSWORD = "";
	
	public PlayerDaoImpl () {
		
		try {
			
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement =
					connection.prepareStatement("CREATE TABLE Player (idPlayer BIGINT,"
																+ " name VARCHAR (30),"
																+ " surname VARCHAR(50),"
																+ " ign VARCHAR(30),"
																+ " isRetired BOOLEAN);");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	public List<Player> getAllPlayers() {
		
		try {
			
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Player;");
			List<Player> players = new LinkedList<Player>();
			
			while (rs.next()) {
				
				players.add(new Player(rs.getLong("idPlayer"),
									   rs.getString("name"),
									   rs.getString("surname"),
									   rs.getString("ign"),
									   rs.getBoolean("isRetired")));
			}
			
			rs.close();
			statement.close();
			
			return players;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {

				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public Player getPlayerById(long idPlayer) {
				
		try {
			
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Player WHERE idPlayer=" + idPlayer + ";");
		
			if (rs.next())
				return new Player(rs.getLong("idPlayer"),
							   	  rs.getString("name"),
							      rs.getString("surname"),
							      rs.getString("ign"),
							      rs.getBoolean("isRetired"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	public void updatePlayer(Player player) {

		try {
			
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement prepStmnt = connection.prepareStatement(
											"UPDATE Player SET name=\'" + player.getName() + "\'" +
															  "surname=\'" + player.getSurname() + "\'" +
															  "ign=\'" + player.getIgn() + "\'" +
															  "isRetired=\'" + player.isRetired() + "\'" +
															  "WHERE idPlayer=" + player.getIdPlayer() + ";");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void deletePlayer(Player player) {
		// TODO Auto-generated method stub
		
	}


}
