package com.mposluszny.jdbc.dto;

import java.io.Serializable;

import com.mposluszny.jdbc.model.Player;
import com.mposluszny.jdbc.model.Team;

public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8763948645650962807L;
	private Player player;
	private Team team;
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
}
