CREATE TABLE Team (idTeam BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name VARCHAR(20) NOT NULL,
 region VARCHAR(4) NOT NULL, dateOfEstablishment DATE);
 
CREATE TABLE Player (idPlayer BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name VARCHAR(30) NOT NULL, 
surname VARCHAR(50) NOT NULL, ign VARCHAR(30) NOT NULL, role VARCHAR(10) NOT NULL, idTeam BIGINT FOREIGN KEY REFERENCES Team(idTeam),
 isRetired BOOLEAN DEFAULT FALSE);