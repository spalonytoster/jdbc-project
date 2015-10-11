package com.mposluszny.jdbc;

import com.mposluszny.jdbc.dao.PlayerDaoImpl;
import com.mposluszny.jdbc.dao.TeamDaoImpl;

/**
 * Hello world!
 *
 */
public class App  {
	
    public static void main(String[] args) {
    	
        System.out.println( "Hello World!" );
        
        TeamDaoImpl teamDaoImpl = new TeamDaoImpl();
        PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();

        teamDaoImpl.addTeam(new Team("TSM", "NA", "2010-05-30"));
        //playerDaoImpl.addPlayer(new Player("Soren", "Bjerg", "Bjergsen", new TeamDaoImpl().getTeamByName("TSM").getIdTeam(), false));
        
        //teamDaoImpl.addTeam(new Team("Fnatic", "EU", "2011-03-14"));
        
        
        //teamDaoImpl.addTeam(new Team("Elements", "EU", "2015-01-01"));

        
    }
}
