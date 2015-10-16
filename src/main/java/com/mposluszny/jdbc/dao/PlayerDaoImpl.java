package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mposluszny.jdbc.model.Player;

public class PlayerDaoImpl implements PlayerDao {

	private final String URL = "jdbc:hsqldb:hsql://localhost/";
	private final String USERNAME = "SA";
	private final String PASSWORD = "";
	
	public PlayerDaoImpl () {
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			rs = connection.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				
				if ("Player".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			
			if (!tableExists) {
				preparedStatement =
					connection.prepareStatement("CREATE TABLE Player (idPlayer BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
																+ " name VARCHAR (30) NOT NULL,"
																+ " surname VARCHAR(50) NOT NULL,"
																+ " ign VARCHAR(30) NOT NULL,"
																+ " idTeam BIGINT FOREIGN KEY REFERENCES Team(idTeam),"
																+ " isRetired BOOLEAN DEFAULT FALSE);");
				preparedStatement.execute();
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (rs != null)
					rs.close();
				
				if (preparedStatement != null)
					preparedStatement.close();
				
				if(connection != null)
					connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	public List<Player> getAllPlayers() {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Player;");
			List<Player> players = new LinkedList<Player>();
			
			while (rs.next()) {
				
				players.add(new Player(rs.getString("name"),
									   rs.getString("surname"),
									   rs.getString("ign"),
									   rs.getString("role"),
									   rs.getLong("idTeam"),
									   rs.getBoolean("isRetired")));
			}
			
			return players;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {

				if (statement != null)
					statement.close();
				
				if (rs != null)
					rs.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public Player getPlayerById(long idPlayer) {
				
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Player WHERE idPlayer=" + idPlayer + ";");
		
			if (rs.next())
				return new Player(rs.getString("name"),
							      rs.getString("surname"),
							      rs.getString("ign"),
							      rs.getString("role"),
							      rs.getLong("idTeam"),
							      rs.getBoolean("isRetired"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (rs != null)
					rs.close();
				
				if (statement != null)
					statement.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public void updatePlayer(Player player) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(
											"UPDATE Player SET name=\'" + player.getName() + "\'" +
															  "surname=\'" + player.getSurname() + "\'" +
															  "ign=\'" + player.getIgn() + "\'" +
															  "role=\'" + player.getRole() + "\'" +
															  "idTeam\'" + player.getIdTeam() + "\'" +
															  "isRetired=\'" + player.isRetired() + "\'" +
															  "WHERE idPlayer=" + player.getIdPlayer() + ";");
			preparedStatement.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}

	}

	public void addPlayer(Player player) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = 
					connection.prepareStatement(String.format("INSERT INTO Player (\"NAME\", \"SURNAME\", \"IGN\", \"ROLE\", \"IDTEAM\", \"ISRETIRED\")"
																+ "VALUES (\'%s\', \'%s\', \'%s\', \'%s\', %s, %b);", player.getName(), player.getSurname(),
																player.getIgn(), player.getRole(), (player.getIdTeam() == 0L ? "null" : String.valueOf(player.getIdTeam())), player.isRetired()));
			preparedStatement.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
		}
		
	}
	

	public void deletePlayer(Player player) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(String.format("DELETE FROM Player WHERE idPlayer=%d", player.getIdPlayer()));
			preparedStatement.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
		}
	}
}