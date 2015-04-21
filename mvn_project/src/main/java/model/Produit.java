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
public class Produit {

    private String nomProduit;
    private Unite unite;
    
    public Produit(String nom, Unite u) {
        this.nomProduit = nom;
        this.unite = u;
    }
    
    public String getNomProduit() {
        return this.nomProduit;
    }
    
    public Unite getUnite() {
        return this.unite;
    }
}
