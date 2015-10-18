package com.mposluszny.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mposluszny.jdbc.dao.GenericDAO;
import com.mposluszny.jdbc.dao.TeamDao;
import com.mposluszny.jdbc.model.Team;

public class TeamDaoImpl extends GenericDAO<Team> implements TeamDao {
	
	public TeamDaoImpl (Connection connection) {
		
		super(connection, "Team");
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			
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
					connection.prepareStatement("CREATE TABLE Team (idTeam BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,"
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
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Team> getAllTeams() {

		Statement statement = null;
		ResultSet rs = null;
		
			try {
			
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team;");
			List<Team> Teams = new ArrayList<Team>();
			
			while (rs.next()) {
				
				Team team = new Team(rs.getString("name"),
									 rs.getString("region"),
									 rs.getString("dateOfEstablishment"));
				team.setIdTeam(rs.getLong("idTeam"));
				Teams.add(team);
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
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Team getTeamById(long idTeam) {

		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team WHERE idTeam=" + idTeam + ";");
		
			if (rs.next()) {
				
				Team team = new Team(rs.getString("name"),
								     rs.getString("region"),
								     rs.getString("dateOfEstablishment"));
				team.setIdTeam(rs.getLong("idTeam"));
				
				return team;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (rs != null)
					rs.close();
				
				if (statement != null)
					statement.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Team getTeamByName(String name) {

		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Team WHERE name=\'" + name + "\';");
		
			if (rs.next()) {
				
				Team team = new Team(rs.getString("name"),
								     rs.getString("region"),
								     rs.getString("dateOfEstablishment"));
				team.setIdTeam(rs.getLong("idTeam"));
				
				return team;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (rs != null)
					rs.close();
				
				if (statement != null)
					statement.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public void updateTeam(Team team) {

		PreparedStatement preparedStatement = null;
		
		try {
			
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
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void addTeam(Team team) {
		
		Statement statement = null;
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(String.format("INSERT INTO Team (NAME, REGION, DATEOFESTABLISHMENT)"
					+ " VALUES (\'%s\', \'%s\', \'%s\');", team.getName(), team.getRegion(), team.getDateOfEstablishment())); 
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if (statement != null)
					statement.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
		}
		
	}

	@Override
	public void deleteTeam(Team team) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(String.format("DELETE FROM Team WHERE idTeam=%d", team.getIdTeam()));
			preparedStatement.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
		}
		
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
