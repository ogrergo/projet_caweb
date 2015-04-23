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
public abstract class Compte {

    private int idCompte;
    private String email;
    private String mdp;

    public Compte(int id, String email, String mdp) {
        this.idCompte = id;
        this.email = email;
        this.mdp = mdp;
    }

    public Compte(int idCompte) {
		this.idCompte = idCompte;
	}



	public int getId() {
        return this.idCompte;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMdp() {
        return this.mdp;
    }
}
