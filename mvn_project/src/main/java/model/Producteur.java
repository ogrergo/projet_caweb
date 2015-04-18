/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Producteur {

    private int idProducteur;
    //Voir comment on stocke les dates
    //private date jourLivraison ;
    private String email;
    private String mdp;

    public Producteur(int id, String email, String mdp) {
        this.idProducteur = id;
        this.email = email;
        this.mdp = mdp;
    }

    public int getId() {
        return this.idProducteur;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMdp() {
        return this.mdp;
    }
}
