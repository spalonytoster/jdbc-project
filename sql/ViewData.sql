SELECT p.name, p.surname, p.ign, p.role, t.name, p.isretired FROM
Player p LEFT JOIN Team t
ON p.idTeam=t.idTeam;