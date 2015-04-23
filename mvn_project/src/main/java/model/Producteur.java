/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Producteur extends Utilisateur {

    //Voir comment on stocke les dates
    //private date jourLivraison ;

    public Producteur(int id, String email, String mdp, String prenom, String nom,
            String adresse) {
        super(id, email, mdp, prenom, nom, adresse);
    }

	public Producteur(int idCompte) {
		super(idCompte);
	}
}
