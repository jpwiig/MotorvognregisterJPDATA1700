CREATE TABLE Reg
(
    id INT AUTO_INCREMENT NOT NULL,
    skiltNr VARCHAR(255) NOT NULL ,
    bilmerke VARCHAR(255) NOT NULL ,
    biltype VARCHAR(255) NOT NULL ,
    personnr varchar(255) NOT NULL ,
    navn VARCHAR(255) NOT NULL ,
    adresse VARCHAR(255) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE Biler
(
id int auto_increment NOT NULL,
Biltype VARCHAR(255) NOT NULL ,
Bilmerke VARCHAR(255) NOT NULL ,
PRIMARY KEY (id)
);

CREATE TABLE user (
    id int auto_increment not null,
    brukernavn VARCHAR (50),
    lastord VARCHAR (250)
);