
CREATE TABLE AnneeScolaire(
AnneeScolaireID int(11) NOT NULL  PRIMARY KEY

);

CREATE TABLE Trimestre(
id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
numero int(11) NOT NULL,
debut int(11) NOT NULL,
fin int(11) NOT NULL,
anneescolaireID int(11) NOT NULL,
FOREIGN KEY (anneescolaireID) REFERENCES anneescolaireID(id)
);

CREATE TABLE Niveau(
id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
nom varchar(255) NOT NULL
);

CREATE TABLE Classe(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
nom varchar(255) NOT NULL,
niveauId int(11) NOT NULL,
anneescolaireID int(11) NOT NULL,
FOREIGN KEY (niveauId) REFERENCES Niveau(id),
FOREIGN KEY (anneescolaireID) REFERENCES anneescolaireID(id)
);

CREATE TABLE Discipline(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
nom varchar(255) NOT NULL
);

CREATE TABLE Personne(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
nom varchar(255) NOT NULL,
prenom varchar(255) NOT NULL,
type varchar(255) NOT NULL
);

CREATE TABLE Enseignement(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
classeID varchar(255) NOT NULL,
disciplineId varchar(255) NOT NULL,
personneId varchar(255) NOT NULL,
FOREIGN KEY (classeID) REFERENCES Classe(id),
FOREIGN KEY (disciplineId) REFERENCES Discipline(id),
FOREIGN KEY (personneId) REFERENCES Personne(id)
);

CREATE TABLE Inscription(
id int(11) NOT NULL PRIMARY KEY  PRIMARY KEY AUTO_INCREMENT ,
classID int(11) NOT NULL,
personneID int(11) NOT NULL,
FOREIGN KEY (classID) REFERENCES Class(id),
FOREIGN KEY (personneID) REFERENCES Personne(id)
);

CREATE TABLE Bulletin(
id int(11) NOT NULL PRIMARY KEY  PRIMARY KEY AUTO_INCREMENT ,
appreciation varchar(255) NOT NULL,
trimestreID int(11) NOT NULL,
inscriptionID int(11) NOT NULL,
FOREIGN KEY (trimestreID) REFERENCES Trimestre(id),
FOREIGN KEY (inscriptionID) REFERENCES Inscription(id)
);

CREATE TABLE DetailBulletin(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
appreciation varchar(255) NOT NULL,
bulletinID int(11) NOT NULL,
enseignementID int(11) NOT NULL,
FOREIGN KEY (bulletinID) REFERENCES Bulletin(id),
FOREIGN KEY (enseignementID) REFERENCES Enseignement(id)
);


CREATE TABLE Evaluation(
id int(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
appreciation varchar(255) NOT NULL,
note int(11) NOT NULL,
detailBulletinID int(11) NOT NULL,
FOREIGN KEY (detailBulletinID) REFERENCES DetailBulletin(id)
);


INSERT INTO `AnneeScolaire`(`AnneeScolaireID`) VALUES
(1998),
(1999),
(2021),
(2010),
(3100),
(2007),
(2012),
(2015),
(1963)
;

INSERT INTO `Trimestre`(`id`, `numero`, `debut`,`fin`,`anneescolaireID`) VALUES
(1,1,3,18,1998),
(2,8,4,7,1999),
(3,2,1,1,2021),
(4,5,3,12,2000),
(5,6,3,8,2010),
(6,4,4,7,3100),
(7,10,3,10,2007),
(8,1,4,7,2012),
(9,2,49,55,2015),
(10,3,4,7,1963)
;


INSERT INTO `Niveau`(`id`,`nom`) VALUES
(1,'cp'),
(2,'ce1'),
(3,'ce2'),
(4,'cm1'),
(5,'cm2'),
(6,'seconde'),
(7,'premiere'),
(8,'terminale'),
(9,'ING2'),
(10,'ING3')
;


INSERT INTO `Classe`(`id`,`nom`,`niveauId`,`anneescolaireID`) VALUES
(1,'cpA',1,1998),
(2,'ce1B',2,1999),
(3,'ce2C',3,2021),
(4,'cm1A',4,2000),
(5,'cm2A',5,2010),
(6,'seconde1',6,3100),
(7,'premiere4',7,2007),
(8,'terminale4',8,2012),
(9,'TD9',9,2015),
(10,'TD1'10,1963)
;

INSERT INTO `Discipline`(`id`,`nom`) VALUES
(1,'maths'),
(2,'physique'),
(3,'elec'),
(4,'java'),
(5,'web'),
(6,'anglais'),
(7,'espagnol'),
(8,'thermo'),
(9,'fourier'),
(10,'traitement du signal')
;

INSERT INTO `Personne`(`id`,`nom`,`prenom`,`type`) VALUES
(1,'Mulliez','Quentin','Etudiant'),
(2,'Pied','Nelly','Etudiant'),
(3,'Carlier Gubler','Helene','Etudiant'),
(4,'Hassan','Momo','Etudiant'),
(5,'L hote','Guigui','Etudiant'),
(6,'Mouhali','Waleed','Prof'),
(7,'Segado','Jean Pierre','Prof'),
(8,'Chaari','Chaari','Prof'),
(9,'Manolo','Hina','Prof'),
(10,'Leonard','Miss','Prof'),
(11,'Lopes','Nicolas','Prof'),
(12,'Escobar','Escobar','Prof'),
(13,'jsaisplus','jsaisplus','Prof'),
(14,'Coudray','Fabienne','Prof'),
(15,'Guicharnaud','Leo','Etudiant')
(16,'TOTO','LELE','Etudiant')
(17,'Tanti','Frate','Etudiant')
(18,'Jess','Marseillais','Etudiant')
(19,'Maaaaarsseillais','Paga','Etudiant')
;

INSERT INTO `Enseignement`(`id`,`classID`,`disciplineId`,`personneId`) VALUES
(1,1,1,8),
(2,2,2,6),
(3,3,3,11),
(4,4,4,7),
(5,5,5,9),
(6,6,6,10),
(7,7,7,12),
(8,8,8,13),
(9,9,9,14),
(10,10,10,11)
;


INSERT INTO `Inscription`(`id`,`classID`,`personneId`) VALUES
(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5),
(6,6,15),
(7,7,16),
(8,8,17),
(9,9,18),
(10,10,19)
;

INSERT INTO `Bulletin`(`id`,`appreciation`,`trimestreID`, `inscriptionID`) VALUES
(1,'tres biennnn',1,1),
(2,'pas bien',2,2),
(3,'moyen ',3,3),
(4,'tres tres biennnn',4,4),
(5,' nul',5,5),
(6,' mauvais',6,6),
(7,' cool',7,7),
(8,' pas cool',8,8),
(9,' bof',9,9),
(10,'attentif',10,10)
;

INSERT INTO `DetailBulletin`(`id`,`appreciation`,`bulletinID`,`enseignementID`) VALUES
(1,'tres serieux et attentif',1,1),
(2,'pas serieux et inatention',2,2),
(3,'continue tes efforts ',3,3),
(4,'trop de retard',4,4),
(5,' fais n importe quoi',5,5),
(6,' a abandonne',6,6),
(7,' est extrement intelligent',7,7),
(8,' un genie',8,8),
(9,' precis et methodique',9,9),
(10,'sympathique mais dois faire plus d efforts',10,10)
;

INSERT INTO `Evaluation`(`id`,`appreciation`,`note`,`detailBulletinID`) VALUES
(1,'tres serieux et attentif',20,1),
(2,'pas serieux et inatention',2,2),
(3,'continue tes efforts ',12,3),
(4,'trop de retard',4,4),
(5,' fais n importe quoi',6,5),
(6,' a abandonne',0,6),
(7,' est extrement intelligent',22,7),
(8,' un genie',23,8),
(9,' precis et methodique',17,9),
(10,'sympathique mais dois faire plus d efforts',10,10)
;