package com.mposluszny.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mposluszny.jdbc.Team;

public class TeamDaoImpl implements TeamDao {
	
	private final String URL = "jdbc:hsqldb:mem/esportTeamsdb";
	private final String USERNAME = "SA";
	private final String PASSWORD = "";

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
				
				Teams.add(new Team(rs.getLong("idTeam"),
								   rs.getString("name"),
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
				return new Team(rs.getLong("idTeam"),
							   	  rs.getString("name"),
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
			rs = statement.executeQuery("SELECT * FROM Team WHERE name=" + name + ";");
		
			if (rs.next())
				return new Team(rs.getLong("idTeam"),
							   	  rs.getString("name"),
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
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = 
					connection.prepareStatement(String.format("INSERT INTO Team (\"NAME\", \"REGION\", \"DATEOFESTABLISHMENT\")"
																+ "VALUES (%s, %s, %s);", team.getName(), team.getRegion(),
																team.getDateOfEstablishment()));
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

	public void deleteTeam(Team team) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(String.format("DELETE FROM Team WHERE idTeam=%i", team.getIdTeam()));
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
