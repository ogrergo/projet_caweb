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

