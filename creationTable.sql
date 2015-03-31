DROP TABLE ResponsablePlanning;
DROP TABLE Permanence;
DROP TABLE Disponibilite;
DROP TABLE Semaine;
DROP TABLE Contrat;
DROP TABLE Production;
DROP TABLE Produit;
DROP TABLE Unite;
DROP TABLE Producteur;
DROP TABLE Consommateur;
DROP TABLE Utilisateur;
DROP TABLE Compte;

CREATE TABLE Compte (
    idCompte int,
    email varchar(60),
    mdp varchar(64),
CONSTRAINT Pk_Compte PRIMARY KEY (idCompte)
);

CREATE TABLE ResponsablePlanning (
    idRespo int,
CONSTRAINT Pk_ResponsablePlanning PRIMARY KEY (idRespo),
CONSTRAINT Fk_ResponsablePlanning_Compte FOREIGN KEY (idRespo) REFERENCES Compte(idCompte)
);

CREATE TABLE Utilisateur (
    idUtilisateur int,
    prenom varchar(50),
    nom varchar(50),
    adresse varchar(100),
CONSTRAINT Pk_Utilisateur PRIMARY KEY (idUtilisateur),
CONSTRAINT Fk_Utilisateur_Compte FOREIGN KEY (idUtilisateur) REFERENCES Compte(idCompte)
);

CREATE TABLE Producteur (
    idProducteur int,
    jourLivraison DATE,
CONSTRAINT Pk_Producteur PRIMARY KEY (idProducteur),
CONSTRAINT Fk_Producteur_Compte FOREIGN KEY (idProducteur) REFERENCES Compte(idCompte)
);

CREATE TABLE Consommateur (
    idConsommateur int,
CONSTRAINT Pk_Consommateur PRIMARY KEY (idConsommateur),
CONSTRAINT Fk_Consommateur_Compte FOREIGN KEY (idConsommateur) REFERENCES Compte(idCompte)
);

CREATE TABLE Unite (
    nomUnite varchar(20),
    CONSTRAINT Pk_Unite PRIMARY KEY (nomUnite)
);

CREATE TABLE Produit (
    nomProduit varchar(50),
    unite varchar(20),
CONSTRAINT Pk_Produit PRIMARY KEY (nomProduit),
CONSTRAINT Fk_Produit_Unite FOREIGN KEY (unite) REFERENCES Unite(nomUnite)
);

CREATE TABLE Production (
    idProduction int,
    produit varchar(50),
    idProducteur int,
    quantiteRestante int,
    duree int,
CONSTRAINT Pk_Production PRIMARY KEY (idProduction),
CONSTRAINT Fk_Production_Produit FOREIGN KEY (produit) REFERENCES Produit(nomProduit)
ON DELETE CASCADE,
CONSTRAINT Fk_Production_Producteur FOREIGN KEY (idProducteur) REFERENCES Producteur(idProducteur)
ON DELETE CASCADE
);

CREATE TABLE Contrat (
    idContrat int,
    production int,
    idConsommateur int,
    quantite int,
    dateDebut DATE,
    duree int,
    valide char(1),   --Y ou N
CONSTRAINT Pk_Contrat PRIMARY KEY (idContrat),
CONSTRAINT Fk_Contrat_Production FOREIGN KEY (production) REFERENCES Production(idProduction)
ON DELETE CASCADE,
CONSTRAINT Fk_Contrat_Consommateur FOREIGN KEY (idConsommateur) REFERENCES Consommateur(idConsommateur)
ON DELETE CASCADE
);

CREATE TABLE Semaine (
    idSemaine int,
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
