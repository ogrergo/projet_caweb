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
public class Consommateur extends Utilisateur{

    public Consommateur(int id, String email, String mdp, String prenom, String nom, String adresse) {
        super(id, email, mdp, prenom, nom, adresse);
    }
    
}
