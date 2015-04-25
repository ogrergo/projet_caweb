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

--Ajout des produits
INSERT INTO Produit (nomProduit) VALUES ('oeuf');
INSERT INTO Produit (nomProduit) VALUES ('jambon');
INSERT INTO Produit (nomProduit) VALUES ('lait');
INSERT INTO Produit (nomProduit) VALUES ('salade');
INSERT INTO Produit (nomProduit) VALUES ('fraise');
INSERT INTO Produit (nomProduit) VALUES ('jus de pomme');

--Ajout de production (lien entre produit et producteur)
INSERT INTO Production (produit, idProducteur, duree) VALUES
('oeuf', 1, 5);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('lait', 1, 10);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('oeuf', 2, 7);
INSERT INTO Production (produit, idProducteur, duree) VALUES
('fraise', 3, 1);

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

--Ajout des 52 semaines d'une année
--Ajout des permanences pour toutes les semaines de l'année (avec initialement aucun Consommateur)
 BEGIN
      FOR v_Count IN 1..52 LOOP
	INSERT INTO Semaine(idSemaine)
       VALUES (v_count);
	INSERT INTO Permanance(idSemaine)
       VALUES (v_count);
    END LOOP;
    END;
   /


