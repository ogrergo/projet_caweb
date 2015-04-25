-- suppression des tables existantes --

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



-- Creation des tables --

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
    valide char(1),   --1 : valide, 0: invalide
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

----------------------- AJOUT DONNEES -------------------

--Ajout de quelques producteurs
INSERT INTO Compte (email, mdp) VALUES
('Producteur1@gmail.com','750f7eda495bfdb2e6c52c5b7d767da4f33e2633683373a2f914e96c0a7407a8');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jean', 'Ulrich', '10 rue Gabriel Faure Grenoble');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Lundi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur2@gmail.com','d30c129a9fd5d36f098de00711066f9566aa1922e7296e3bab783d74edb61a64');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Salim', 'Guerande', '5 bis avenue Leon Blum Paris');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Mercredi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur3@gmail.com','bf61d3d0d22c65a86d72025d2d668b27c25426f864fd0459b543a151092369f8');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jean-Joseph', 'Placardeau', '9 place du Roi Versailles');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jeudi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur4@gmail.com','490fbe4167e975596ce088c4f6bb04265e2fce368c51ba56fe178288ef3d6359');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jauselie', 'Cauctaille', '987 Wallabie Sydney');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Dimanche');

INSERT INTO Compte (email, mdp) VALUES
('b@b','3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Yves', 'La Roche', '5 ter rue des Non Dit Carpentra');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Lundi');

--Ajout des utilisateurs
INSERT INTO Compte (email, mdp) VALUES
('a@a','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Mathilde', 'Dufeux', '48 avenue des Pierre Echirolles');
INSERT INTO Consommateur (idConsommateur) VALUES
((SELECT MAX(idCompte) FROM Compte));

INSERT INTO Compte (email, mdp) VALUES
('d@d','18ac3e7343f016890c510e93f935261169d9e3f565436429830faf0934f4f8e4');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Lea', 'Prulaux', '20 boulevard Farouche Esteralle');
INSERT INTO Consommateur (idConsommateur) VALUES
((SELECT MAX(idCompte) FROM Compte));

INSERT INTO Compte (email, mdp) VALUES
('e@e','3f79bb7b435b05321651daefd374cdc681dc06faa65e374e38337b88ca046dea');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jean-Michel', 'Patachou', '12 bis impasse des Bienommés');
INSERT INTO Consommateur (idConsommateur) VALUES
((SELECT MAX(idCompte) FROM Compte));

INSERT INTO Compte (email, mdp) VALUES
('f@f','252f10c83610ebca1a059c0bae8255eba2f95be4d1d7bcfa89d7248a82d9f111');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Francisco', 'San', '1492 route des Amériques');
INSERT INTO Consommateur (idConsommateur) VALUES
((SELECT MAX(idCompte) FROM Compte));

--Ajout d'un respo planning
INSERT INTO Compte (email, mdp) VALUES
('c@c','2e7d2c03a9507ae265ecf5b5356885a53393a2029d241394997265a1a25aefc6');
INSERT INTO ResponsablePlanning (idRespo) VALUES
((SELECT MAX(idCompte) FROM Compte));

--Ajout des unitées
INSERT INTO Unite (nomUnite) VALUES ('kg');
INSERT INTO Unite (nomUnite) VALUES ('litre');
INSERT INTO Unite (nomUnite) VALUES ('demi-panier');
INSERT INTO Unite (nomUnite) VALUES ('panier');
INSERT INTO Unite (nomUnite) VALUES ('piece');
INSERT INTO Unite (nomUnite) VALUES ('demi-douzaine');
INSERT INTO Unite (nomUnite) VALUES ('douzaine');
INSERT INTO Unite (nomUnite) VALUES ('barquette');
INSERT INTO Unite (nomUnite) VALUES ('botte');

--Ajout des produits
INSERT INTO Produit (nomProduit) VALUES ('oeuf');
INSERT INTO Produit (nomProduit) VALUES ('jambon');
INSERT INTO Produit (nomProduit) VALUES ('lait');
INSERT INTO Produit (nomProduit) VALUES ('salade');
INSERT INTO Produit (nomProduit) VALUES ('fraise');
INSERT INTO Produit (nomProduit) VALUES ('jus de pomme');
INSERT INTO Produit (nomProduit) VALUES ('vin rouge');
INSERT INTO Produit (nomProduit) VALUES ('mirabelles');
INSERT INTO Produit (nomProduit) VALUES ('melon');
INSERT INTO Produit (nomProduit) VALUES ('cerises');
INSERT INTO Produit (nomProduit) VALUES ('asperges');
INSERT INTO Produit (nomProduit) VALUES ('pastèque');
INSERT INTO Produit (nomProduit) VALUES ('pommes');
INSERT INTO Produit (nomProduit) VALUES ('saucisson');

--Ajout de production (lien entre produit et producteur)
INSERT INTO Production (produit, idProducteur, duree) VALUES
('oeuf', 1, 5);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('lait', 1, 10);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('oeuf', 2, 7);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('fraise', 3, 1);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('mirabelles', 5, 5);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('cerises', 5, 3);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('vin rouge', 5, 8);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('pastèque', 5, 2);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('saucisson', 3, 8);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('pommes', 5, 3);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('melon', 5, 5);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('jambon', 5, 9);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('fraise', 5, 5);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('jus de pomme', 5, 5);

--Ajout unités possible pour ces productions
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(1, 'demi-douzaine');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(1, 'douzaine');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(2, 'litre');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(3, 'douzaine');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(4, 'panier');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(5, 'panier');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(5, 'kg');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(6, 'panier');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(6, 'kg');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(7, 'litre');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(8, 'piece');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(9, 'piece');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(9, 'kg');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(10, 'panier');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(10, 'kg');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(11, 'piece');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(12, 'kg');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(13, 'barquette');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(13, 'panier');
INSERT INTO ProductionUnites (idProduction, nomUnite) VALUES
(14, 'litre');

--Ajout des 52 semaines d'une année
--Ajout des permanences pour toutes les semaines de l'année (avec initialement aucun Consommateur)
 BEGIN
      FOR v_Count IN 1..52 LOOP
	INSERT INTO Semaine(idSemaine)
       VALUES (v_count);
	INSERT INTO Permanence(idSemaine)
       VALUES (v_count);
    END LOOP;
    END;
   /

--Ajout des contrats
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (14, 6, 3, 17, 1, 'litre');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (13, 6, 7, 23, 1, 'barquette');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (10, 6, 2, 9, 1, 'kg');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (5, 6, 1, 19, 0, 'panier');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (6, 8, 3, 17, 1, 'kg');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (12, 7, 3, 26, 0, 'kg');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (8, 9, 3, 17, 1, 'piece');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (1, 6, 3, 17, 1, 'douzaine');
	INSERT INTO Contrat (idProduction, idConsommateur, quantite, dateDebut, valide, nomUnite) VALUES (7, 6, 8, 14, 1, 'litre');


