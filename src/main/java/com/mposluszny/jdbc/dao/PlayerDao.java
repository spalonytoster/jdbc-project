package com.mposluszny.jdbc.dao;

import java.util.List;

import com.mposluszny.jdbc.Player;

public interface PlayerDao {

	public List<Player> getAllPlayers();
	public void updatePlayer();
	public void addPlayer();
	public void deletePlayer();
}
