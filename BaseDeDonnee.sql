CREATE TABLE Ecole(
id_ecole varchar(255) NOT NULL PRIMARY KEY,
nom_ecole varchar(255) NOT NULL
);

CREATE TABLE AnnéeScolaire(
id int(11) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE Trimestre(
id int(11) NOT NULL PRIMARY KEY,
numero int(11) NOT NULL,
debut int(11) NOT NULL,
fin int(11) NOT NULL,
AnnéeScolaireId int(11) NOT NULL,
FOREIGN KEY (AnnéeScolaireId) REFERENCES AnnéeScolaire(id)
);

CREATE TABLE Niveau(
id int(11) NOT NULL,
nom varchar(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE Classe(
id int(11) NOT NULL,
nom varchar(255) NOT NULL,
EcoleId varchar(255) NOT NULL,
NiveauId int(11) NOT NULL,
AnnéeScolaireId int(11) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (EcoleId) REFERENCES Ecole(id_ecole),
FOREIGN KEY (NiveauId) REFERENCES Niveau(id),
FOREIGN KEY (AnnéeScolaireId) REFERENCES AnnéeScolaire(id)
);

CREATE TABLE Discipline(
id varchar(255) NOT NULL,
nom varchar(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE Personne(
id int(11) NOT NULL,
nom varchar(255) NOT NULL,
prenom varchar(255) NOT NULL,
type varchar(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE Enseignement(
id varchar(255) NOT NULL,
classeID varchar(255) NOT NULL,
disciplineId varchar(255) NOT NULL,
personneId varchar(255) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (classeID) REFERENCES Classe(id),
FOREIGN KEY (disciplineId) REFERENCES Discipline(id),
FOREIGN KEY (personneId) REFERENCES Personne(id)
);

CREATE TABLE Inscription(
id int(11) NOT NULL PRIMARY KEY,
classID int(11) NOT NULL,
personneID int(11) NOT NULL,
FOREIGN KEY (classID) REFERENCES Class(id),
FOREIGN KEY (personneID) REFERENCES Personne(id)
);

CREATE TABLE Bulletin(
id int(11) NOT NULL PRIMARY KEY,
appreciation varchar(255) NOT NULL,
trimestreID int(11) NOT NULL,
inscriptionID int(11) NOT NULL,
FOREIGN KEY (trimestreID) REFERENCES Trimestre(id),
FOREIGN KEY (inscriptionID) REFERENCES Inscription(id)
);

CREATE TABLE DetailBulletin(
id int(11) NOT NULL PRIMARY KEY,
appreciation varchar(255) NOT NULL,
bulletinID int(11) NOT NULL,
enseignementID int(11) NOT NULL,
FOREIGN KEY (bulletinID) REFERENCES Bulletin(id),
FOREIGN KEY (enseignementID) REFERENCES Enseignement(id)
);


CREATE TABLE Evaluation(
id int(11) NOT NULL PRIMARY KEY,
appreciation varchar(255) NOT NULL,
note int(11) NOT NULL,
detailBulletinID int(11) NOT NULL,
FOREIGN KEY (detailBulletinID) REFERENCES DetailBulletin(id)
);
