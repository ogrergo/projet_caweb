package model;

import java.util.ArrayList;

public class Production {

    private int idProduction;
    private String produit;
    private int idProducteur;
    private int duree;
    private ArrayList<Unite> listUnites;
    
    private String nomProducteur;
    private String prenomProducteur;

    public String getPrenomProducteur() {
        return prenomProducteur;
    }


    public ArrayList<Unite> getListUnites() {
        return listUnites;
    }
    
    public int getIdProduction() {
        return idProduction;
    }

    public String getProduit() {
        return produit;
    }

    public String getNomProducteur() {
        return nomProducteur;
    }

    public int getDuree() {
        return duree;
    }

    public Production(int idProduction, String produit, String nomProducteur,
            String prenomProducteur, int duree) {
        super();
        this.idProduction = idProduction;
        this.produit = produit;
        this.nomProducteur = nomProducteur;
        this.prenomProducteur = prenomProducteur;
        this.duree = duree;
    }

    public Production(int idProduction, int idProducteur, String produit,  int duree) {
        super();
        this.idProduction = idProduction;
        this.idProducteur = idProducteur;
        this.duree = duree;
        this.produit = produit;
    }
    
    public Production(int idProduction, int idProducteur, String produit, String nomProducteur,
            String prenomProducteur, int duree) {
        super();
        this.idProduction = idProduction;
        this.idProducteur = idProducteur;
        this.produit = produit;
        this.nomProducteur = nomProducteur;
        this.prenomProducteur = prenomProducteur;
        this.duree = duree;
    }
    
    
    public void setListUnites(ArrayList<Unite> listUnites) {
        this.listUnites = listUnites;
    }


	public int getIdProducteur() {
		return idProducteur;
	}

}
