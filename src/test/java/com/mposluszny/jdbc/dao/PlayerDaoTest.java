package com.mposluszny.jdbc.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mposluszny.jdbc.model.Player;
import com.mposluszny.jdbc.model.Team;

public class PlayerDaoTest {

	PlayerDao playerDao = new PlayerDaoImpl();

	@Test
	public void checkAddPlayer() {
		
		TeamDaoImpl teamDaoImpl = new TeamDaoImpl();
		PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
		teamDaoImpl.addTeam(new Team("CLG", "NA", "2012-10-10"));
		
		Player player = new Player("Peter", "Peng", "Doublelift", "ADC", 0L, false);
		playerDaoImpl.addPlayer(player);
		
		assertNotNull(playerDaoImpl.getPlayerById(player.getIdPlayer()));
	}
	

	
}
