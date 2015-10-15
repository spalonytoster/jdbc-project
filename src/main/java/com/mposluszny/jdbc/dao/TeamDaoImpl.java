package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mposluszny.jdbc.model.Team;

public class TeamDaoImpl implements TeamDao {
	
	private final String URL = "jdbc:hsqldb:hsql://localhost/";
	private final String USERNAME = "SA";
	private final String PASSWORD = "";
	
	public TeamDaoImpl () {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			rs = connection.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				
				if ("Team".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			
			if (!tableExists) {
				preparedStatement =
					connection.prepareStatement("CREATE TABLE Team (idTeam BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
																+ " name VARCHAR (30),"
																+ " region VARCHAR(4),"
																+ " dateOfEstablishment DATE);");
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

	public List<Team> getAllTeams() {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
			try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team;");
			List<Team> Teams = new LinkedList<Team>();
			
			while (rs.next()) {
				
				Teams.add(new Team(rs.getString("name"),
								   rs.getString("region"),
								   rs.getString("dateOfEstablishment")));
			}
			
			
			return Teams;
			
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

	public Team getTeamById(long idTeam) {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team WHERE idTeam=" + idTeam + ";");
		
			if (rs.next())
				return new Team(rs.getString("name"),
							      rs.getString("region"),
							      rs.getString("dateOfEstablishment"));
			
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

	public Team getTeamByName(String name) {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team WHERE name=\'" + name + "\';");
		
			if (rs.next())
				return new Team(rs.getString("name"),
							      rs.getString("region"),
							      rs.getString("dateOfEstablishment"));
			
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

	public void updateTeam(Team team) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(
											"UPDATE Team SET name=\'" + team.getName() + "\'" +
															  "region=\'" + team.getRegion() + "\'" +
															  "dateOfEstablishment=\'" + team.getDateOfEstablishment() + "\'" +
															  "WHERE idTeam=" + team.getIdTeam() + ";");
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

	public void addTeam(Team team) {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate(String.format("INSERT INTO Team (\"NAME\", \"REGION\", \"DATEOFESTABLISHMENT\")"
					+ " VALUES (\'%s\', \'%s\', \'%s\');", team.getName(), team.getRegion(), team.getDateOfEstablishment())); 
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if (statement != null)
					statement.close();
				
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
		}
		
	}

	public void deleteTeam(Team team) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(String.format("DELETE FROM Team WHERE idTeam=%d", team.getIdTeam()));
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
