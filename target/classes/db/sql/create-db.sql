--DROP TABLE users IF EXISTS;
/*
CREATE TABLE Participations (
  id INTEGER,
  
  title VARCHAR(65535),
  dateObject VARCHAR(30),
  medium VARCHAR(65535),
  creditline VARCHAR(30),
  description VARCHAR(65535),
  gallery_text VARCHAR(30),
  
  PRIMARY KEY (id)
);
*/

CREATE TABLE CHObjects (
  id INTEGER,
  
  
  title VARCHAR(65535),
  dateObject VARCHAR(65535),
  medium VARCHAR(65535),
  creditline VARCHAR(65535),
  description VARCHAR(65535),
  gallery_text VARCHAR(65535),
  
  /*
 participation_id INTEGER,
  */
  
  PRIMARY KEY (id),
  
  /*
	FOREIGN KEY (participation_id) REFERENCES Participations(id)
  */
);
