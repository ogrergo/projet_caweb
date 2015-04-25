DROP TABLE ResponsablePlanning;
DROP TABLE Permanence;
DROP TABLE Disponibilite;
DROP TABLE Semaine;
DROP TABLE Contrat;
DROP TABLE ProductionUnites;
DROP TABLE Production;
DROP TABLE Produit;
DROP TABLE Unite;
DROP TABLE Producteur;
DROP TABLE Consommateur;
DROP TABLE Utilisateur;
DROP TABLE Compte;

DROP SEQUENCE id_seqCompte;
DROP SEQUENCE id_seqProduction;
DROP SEQUENCE id_seqContrat;

CREATE SEQUENCE id_seqCompte;
CREATE SEQUENCE id_seqProduction;
CREATE SEQUENCE id_seqContrat;

CREATE TABLE Compte (
    idCompte number(6) DEFAULT id_seqCompte.nextval,
    email varchar(60) UNIQUE,
    mdp varchar(64),
CONSTRAINT Pk_Compte PRIMARY KEY (idCompte)
);

CREATE TABLE ResponsablePlanning (
    idRespo number(6),
CONSTRAINT Pk_ResponsablePlanning PRIMARY KEY (idRespo),
CONSTRAINT Fk_ResponsablePlanning_Compte FOREIGN KEY (idRespo) REFERENCES Compte(idCompte)
);

CREATE TABLE Utilisateur (
    idUtilisateur number(6),
    prenom varchar(50),
    nom varchar(50),
    adresse varchar(100),
CONSTRAINT Pk_Utilisateur PRIMARY KEY (idUtilisateur),
CONSTRAINT Fk_Utilisateur_Compte FOREIGN KEY (idUtilisateur) REFERENCES Compte(idCompte)
);

CREATE TABLE Producteur (
    idProducteur number(6),
    jourLivraison varchar(8),
CONSTRAINT Pk_Producteur PRIMARY KEY (idProducteur),
CONSTRAINT Fk_Producteur_Compte FOREIGN KEY (idProducteur) REFERENCES Compte(idCompte)
);

CREATE TABLE Consommateur (
    idConsommateur number(6),
CONSTRAINT Pk_Consommateur PRIMARY KEY (idConsommateur),
CONSTRAINT Fk_Consommateur_Compte FOREIGN KEY (idConsommateur) REFERENCES Compte(idCompte)
);

CREATE TABLE Unite (
    nomUnite varchar(20),
    CONSTRAINT Pk_Unite PRIMARY KEY (nomUnite)
);

CREATE TABLE Produit (
    nomProduit varchar(50),
CONSTRAINT Pk_Produit PRIMARY KEY (nomProduit)
);

CREATE TABLE Production (
    idProduction number(6) DEFAULT id_seqProduction.nextval,
    produit varchar(50),
    idProducteur int,
    duree int,
CONSTRAINT Pk_Production PRIMARY KEY (idProduction),
CONSTRAINT Fk_Production_Produit FOREIGN KEY (produit) REFERENCES Produit(nomProduit)
ON DELETE CASCADE,
CONSTRAINT Fk_Production_Producteur FOREIGN KEY (idProducteur) REFERENCES Producteur(idProducteur)
ON DELETE CASCADE
);

CREATE TABLE ProductionUnites (
    idProduction number(6),
    nomUnite varchar(20),
CONSTRAINT Pk_ProductionUnites PRIMARY KEY (idProduction, nomUnite),
CONSTRAINT Fk_ProductionUnites_Production FOREIGN KEY (idProduction) REFERENCES Production(idProduction)
ON DELETE CASCADE,
CONSTRAINT Fk_ProductionUnites_Unite FOREIGN KEY (nomUnite) REFERENCES Unite(nomUnite)
ON DELETE CASCADE
);

CREATE TABLE Contrat (
    idContrat number(6) DEFAULT id_seqContrat.nextval,
    idProduction int,
    idConsommateur int,
    quantite int,
    dateDebut int,
    valide char(1),   --Y ou N
    nomUnite varchar(20),
CONSTRAINT Pk_Contrat PRIMARY KEY (idContrat),
CONSTRAINT Fk_Contrat_Production FOREIGN KEY (idProduction) REFERENCES Production(idProduction)
ON DELETE CASCADE,
CONSTRAINT Fk_Contrat_Consommateur FOREIGN KEY (idConsommateur) REFERENCES Consommateur(idConsommateur)
ON DELETE CASCADE,
CONSTRAINT Fk_Contrat_Unite FOREIGN KEY (nomUnite) REFERENCES Unite(nomUnite)
ON DELETE CASCADE
);

CREATE TABLE Semaine (
    idSemaine number(6),
CONSTRAINT Pk_Semaine PRIMARY KEY (idSemaine)
);

CREATE TABLE Disponibilite (
    idSemaine int,
    idConsommateur int,
CONSTRAINT Pk_Disponibilite PRIMARY KEY (idSemaine, idConsommateur),
CONSTRAINT Fk_Disponibilite_Semaine FOREIGN KEY (idSemaine) REFERENCES Semaine(idSemaine)
ON DELETE CASCADE,
CONSTRAINT Fk_Disponibilite_Consommateur FOREIGN KEY (idConsommateur) REFERENCES Consommateur(idConsommateur)
ON DELETE CASCADE
);

CREATE TABLE Permanence (
    idSemaine int,
    idConsommateur1 int,
    idConsommateur2 int,
CONSTRAINT Pk_Permanence PRIMARY KEY (idSemaine),
CONSTRAINT Fk_Permanence_Semaine FOREIGN KEY (idSemaine) REFERENCES Semaine(idSemaine)
ON DELETE CASCADE,
CONSTRAINT Fk_Disponibilite_Consommateur1 FOREIGN KEY (idConsommateur1) REFERENCES Consommateur(idConsommateur)
ON DELETE CASCADE,
CONSTRAINT Fk_Disponibilite_Consommateur2 FOREIGN KEY (idConsommateur2) REFERENCES Consommateur(idConsommateur)
ON DELETE CASCADE
);
