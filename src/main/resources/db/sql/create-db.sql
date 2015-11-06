--DROP TABLE CHObjects IF EXISTS;

CREATE TABLE CHObjects (
  id INTEGER,
  title VARCHAR(65535),
  dateObject VARCHAR(65535),
  medium VARCHAR(65535),
  creditline VARCHAR(65535),
  description VARCHAR(65535),
  gallery_text VARCHAR(65535),
  
  PRIMARY KEY (id),
);

CREATE TABLE CHObjects_Images ( 
  chObject_id INTEGER NOT NULL,
  
  image_id INTEGER NOT NULL,
  size VARCHAR(65535),
  
  PRIMARY KEY (chObject_id, image_id, size)
  /* PRIMARY KEY (chObject_id, image_id) */
);

CREATE TABLE Images (
  id INTEGER,
  size VARCHAR(65535),
  
  url VARCHAR(65535),
  width INTEGER,
  height INTEGER,
  is_primary INTEGER,
  
  PRIMARY KEY (id, size)
);

CREATE TABLE CHObjects_Participants_Roles (
  chObject_id INTEGER NOT NULL, 
  participant_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  
  PRIMARY KEY (chObject_id, participant_id, role_id)
);

CREATE TABLE Participants (
  id INTEGER,
  name VARCHAR(65535),
  birth VARCHAR(65535),
  url VARCHAR(65535),  
  
  PRIMARY KEY (id),
);

CREATE TABLE Roles (
  id INTEGER,
  name VARCHAR(65535),
  display_name VARCHAR(65535),
  url VARCHAR(65535),  

  PRIMARY KEY (id),
);
