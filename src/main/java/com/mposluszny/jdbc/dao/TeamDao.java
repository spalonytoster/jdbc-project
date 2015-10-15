package com.mposluszny.jdbc.dao;

import java.util.List;

import com.mposluszny.jdbc.model.Team;

public interface TeamDao {

	public List<Team> getAllTeams();
	public Team getTeamById (long idTeam);
	public Team getTeamByName (String name);
	public void updateTeam(Team Team);
	public void addTeam(Team team);
	public void deleteTeam(Team team);
}
