package com.mposluszny.jdbc.dao;

import java.util.List;

import com.mposluszny.jdbc.Player;

public interface PlayerDao {

	public List<Player> getAllPlayers();
	public void updatePlayer(long idPlayer, String name, String surname, String ign, boolean isRetired);
	public void addPlayer(String name, String surname, String ign, boolean isRetired);
	public void deletePlayer(long idPlayer);
	public void deletePlayer(String ign);
}
