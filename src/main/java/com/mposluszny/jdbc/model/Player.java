package com.mposluszny.jdbc.model;

import com.mposluszny.jdbc.dao.TeamDaoImpl;

public class Player {

	private long idPlayer;
	private String name;
	private String surname;
	private String ign;
	private long idTeam;
	private boolean isRetired;
	
	public Player(String name, String surname, String ign, long idTeam, boolean isRetired) {
		
		this.name = name;
		this.surname = surname;
		this.ign = ign;
		this.idTeam = idTeam;
		this.isRetired = isRetired;
	}
	
	public Player(String name, String surname, String ign, String teamName, boolean isRetired) {
		
		this.name = name;
		this.surname = surname;
		this.ign = ign;
		this.idTeam = new TeamDaoImpl().getTeamByName(teamName).getIdTeam();
		this.isRetired = isRetired;
	}
	
	public long getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getIgn() {
		return ign;
	}
	public void setIgn(String ign) {
		this.ign = ign;
	}
	public boolean isRetired() {
		return isRetired;
	}
	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	public long getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}
}
