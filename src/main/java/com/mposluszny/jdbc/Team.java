package com.mposluszny.jdbc;

import java.io.Serializable;

public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 946253365452265161L;
	
	private long idTeam;
	private String name;
	private String region;
	private String dateOfEstablishment;
	
	public Team (long idTeam, String name, String region, String dateOfEstablishment) {
		
		this.idTeam = idTeam;
		this.name = name;
		this.region = region;
		this.dateOfEstablishment = dateOfEstablishment;
	}
	
	public long getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDateOfEstablishment() {
		return dateOfEstablishment;
	}
	public void setDateOfEstablishment(String dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

}
