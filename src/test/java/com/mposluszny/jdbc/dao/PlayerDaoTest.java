package com.mposluszny.jdbc.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mposluszny.jdbc.dao.impl.PlayerDaoImpl;
import com.mposluszny.jdbc.dao.impl.TeamDaoImpl;
import com.mposluszny.jdbc.model.Player;
import com.mposluszny.jdbc.model.Team;

public class PlayerDaoTest {

	@Test
	public void checkAddPlayer() {
		
		TeamDao teamDao = new TeamDaoImpl();
		PlayerDao playerDao = new PlayerDaoImpl();
		teamDao.addTeam(new Team("CLG", "NA", "2012-10-10"));
		teamDao.addTeam(new Team("TSM", "NA", "2011-10-10"));
		
		int size = playerDao.getAllPlayers().size();
		
		Player player = new Player("Peter", "Peng", "Doublelift", "ADC", teamDao.getTeamByName("TSM").getIdTeam(), false);
		playerDao.addPlayer(player);

		assertTrue(playerDao.getAllPlayers().size() == size+1);
	}
	

	
}
