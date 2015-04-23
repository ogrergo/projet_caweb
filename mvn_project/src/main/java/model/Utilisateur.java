/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author robin
 */
public abstract class Utilisateur extends Compte {

    private String prenom;
    private String nom;
    private String adresse;

    public Utilisateur(int id, String email, String mdp, String prenom, String nom,
            String adresse) {
        super(id, email, mdp);
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Utilisateur(int idCompte) {
		super(idCompte);
	}

	public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

}
