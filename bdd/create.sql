CREATE SEQUENCE id_periph ;
CREATE SEQUENCE id_apps;
CREATE SEQUENCE id_maj ;



CREATE TABLE Droit (
id_droit INT NOT NULL UNIQUE,
type_droit INT,
PRIMARY KEY (id_droit)
);

CREATE TABLE Fabriquant (
id_fabriquant INT NOT NULL UNIQUE,
nom_fabriquant VARCHAR (100) NOT NULL,
pays VARCHAR (40) NOT NULL,
PRIMARY KEY (id_fabriquant)
);

CREATE TABLE Mot_cle (
id_mot INT NOT NULL UNIQUE,
nom_mot_cle text 
);

CREATE TABLE OS (
id_os INT NOT NULL UNIQUE,
nom_os VARCHAR (30) CHECK (nom_os IN ('Cyborg','Predator','Bionic')),
release_os INT NOT NULL,
version_os INT NOT NULL,
id_apps INT,
PRIMARY KEY (id_os)
);

CREATE TABLE Users (
id_users INT NOT NULL UNIQUE,
nom VARCHAR (25) NOT NULL,
prenom VARCHAR (25) NOT NULL,
email VARCHAR (200),
proba_elstar INT,
proba_coms INT,
adresse VARCHAR (100),
raison_sociale CHAR (1) CHECK (raison_sociale  IN ('G','D','C')),
id_login VARCHAR (40) NOT NULL UNIQUE,
mdp VARCHAR (40) NOT NULL,
PRIMARY KEY (id_users)
);

CREATE TABLE Apps (
id_apps INT NOT NULL UNIQUE DEFAULT nextval ('id_apps'),
nom_apps text NOT NULL,
prix DECIMAL,
payante INT,
mensuel INT,
droits INT,
release_apps VARCHAR (10) NOT NULL,
version_apps VARCHAR (10) NOT NULL,
nom_os VARCHAR (30) ,
release_os INT,
version_os INT,
id_dev INT REFERENCES users (id_users),
categorie VARCHAR (15) NOT NULL,
mot_cle TEXT,
point_mela INT,
PRIMARY KEY (id_apps)
);



CREATE TABLE Peripherique (
id_periph INT NOT NULL UNIQUE DEFAULT nextval ('id_periph'),
nb_apps INT,
nom_os VARCHAR (30),
release_os INT,
version_os INT ,
nom_periph VARCHAR (30) NOT NULL,
type_periph VARCHAR (30) CHECK (type_periph IN ('telephone','tablette','lunette','montre')),
id_os INT,
id_fabriquant INT,
id_users INT,
PRIMARY KEY (id_periph),
FOREIGN KEY (id_os) REFERENCES OS (id_os),
FOREIGN KEY (id_fabriquant) REFERENCES Fabriquant (id_fabriquant)
);


CREATE TABLE Info_Paiement (
id_info INT NOT NULL UNIQUE,
id_users INT REFERENCES Users (id_users),
type VARCHAR (25) CHECK (type IN ('cb','paypal')),
numero_carte VARCHAR UNIQUE,
date_expiration VARCHAR (40),
PRIMARY KEY (id_info)
);


--Demande (apps droit)
CREATE TABLE Demande (
id_apps INT,
id_droit INT,
FOREIGN KEY (id_apps) REFERENCES Apps (id_apps),
FOREIGN KEY (id_droit) REFERENCES Droit (id_droit),
PRIMARY KEY (id_apps,id_droit)
);

--Installe (apps users)
CREATE TABLE Installe (
id_apps INT,
id_users INT,
coms TEXT,
elstar varchar (10) CHECK (elstar IN ('0','1','2','3','4','5',null)),
FOREIGN KEY (id_apps) REFERENCES Apps (id_apps),
FOREIGN KEY (id_users) REFERENCES Users (id_users),
PRIMARY KEY (id_apps,id_users)
);



--A_Mot_cle (apps mot cle)
CREATE TABLE A_Mot_cle (
id_apps INT,
id_mot INT,
FOREIGN KEY (id_apps) REFERENCES Apps (id_apps),
FOREIGN KEY (id_mot) REFERENCES Mot_cle (id_mot),
PRIMARY KEY (id_apps,id_mot)
); 


CREATE TABLE MAJ (
id_maj INT NOT NULL UNIQUE DEFAULT nextval('id_maj'),
date_maj DATE NOT NULL,
id_apps INT,
id_users INT,
PRIMARY KEY (id_maj),
FOREIGN KEY (id_apps) REFERENCES Apps (id_apps),
FOREIGN KEY (id_users) REFERENCES Users (id_users)
);


