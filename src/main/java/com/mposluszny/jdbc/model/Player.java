package com.mposluszny.jdbc.model;

public class Player {

	private long idPlayer;
	private String name;
	private String surname;
	private String ign;
	private String role;
	private long idTeam;
	private String teamName;
	private boolean isRetired;
	
	public Player(String name, String surname, String ign, String role, long idTeam, boolean isRetired) {
		
		this.name = name;
		this.surname = surname;
		this.ign = ign;
		this.setRole(role);
		this.idTeam = idTeam;
		this.isRetired = isRetired;
	}
	
	public Player(String name, String surname, String ign, String role, String teamName, boolean isRetired) {
		
		this.name = name;
		this.surname = surname;
		this.ign = ign;
		this.role = role;
		this.setTeamName(teamName);
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public long getIdTeam() {
		return idTeam;
	}
	
	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}
	
	public boolean isRetired() {
		return isRetired;
	}
	
	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
