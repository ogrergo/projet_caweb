package model;

public class Production {
	
	private int idProduction;
	private String produit;
	private String nomProducteur;
	private String prenomProducteur;
	
	
	public String getPrenomProducteur() {
		return prenomProducteur;
	}

	private int duree;
	
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
	
}
