package model;

public class Production {
	
	private int idProduction;
	private String produit;
	private int idProducteur;
	private int duree;
	
	public int getIdProduction() {
		return idProduction;
	}

	public String getProduit() {
		return produit;
	}

	public int getIdProducteur() {
		return idProducteur;
	}

	public int getDuree() {
		return duree;
	}

	public Production(int idProduction, String produit, int idProducteur,
			int duree) {
		super();
		this.idProduction = idProduction;
		this.produit = produit;
		this.idProducteur = idProducteur;
		this.duree = duree;
	}
	
}
