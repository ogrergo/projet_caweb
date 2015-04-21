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
    
    public Produit(String nom) {
        this.nomProduit = nom;
    }
    
    public String getNomProduit() {
        return this.nomProduit;
    }
}
