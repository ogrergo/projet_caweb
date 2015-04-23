--Ajout de quelques producteurs
INSERT INTO Compte (email, mdp) VALUES
('Producteur1@gmail.com','35f2cdb8924e8fcd12b86ad621304792');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'prenomProd1', 'nomProd1', 'adresseProd1');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Lundi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur2@gmail.com','b249561af7904727c4bceefed868c373');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'prenomProd2', 'nomProd2', 'adresseProd2');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Mercredi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur3@gmail.com','0bd69f1a8211f0ffc81216e5de6e3e77');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'prenomProd3', 'nomProd3', 'adresseProd3');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Jeudi');

INSERT INTO Compte (email, mdp) VALUES
('Producteur4@gmail.com','10022410286caf49eaf022a062ef45e1');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'prenomProd4', 'nomProd4', 'adresseProd4');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Dimanche');

INSERT INTO Compte (email, mdp) VALUES
('b@b','b');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'b', 'b', 'b');
INSERT INTO Producteur (idProducteur, jourLivraison) VALUES
((SELECT MAX(idCompte) FROM Compte), 'Lundi');

--Ajout des utilisateurs
INSERT INTO Compte (email, mdp) VALUES
('a@a','a');
INSERT INTO Utilisateur (idUtilisateur, prenom, nom, adresse) VALUES
((SELECT MAX(idCompte) FROM Compte), 'a', 'a', 'a');
INSERT INTO Consommateur (idConsommateur) VALUES
((SELECT MAX(idCompte) FROM Compte));

--Ajout d'un respo planning
INSERT INTO Compte (email, mdp) VALUES
('c@c','c');
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


