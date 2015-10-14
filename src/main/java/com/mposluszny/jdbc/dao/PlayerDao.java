package com.mposluszny.jdbc.dao;

import java.util.List;

import com.mposluszny.jdbc.model.Player;

public interface PlayerDao {

	public List<Player> getAllPlayers();
	public Player getPlayerById (long idPlayer);
	public void updatePlayer(Player player);
	public void addPlayer(Player player);
	public void deletePlayer(Player player);
}
