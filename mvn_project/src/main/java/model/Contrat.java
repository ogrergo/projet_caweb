package model;

import java.sql.Date;

public class Contrat {
	private int idContrat;
	private int idProduction;
	private int idConsomateur;
	private int quantite;
	private Date dateDebut;
	private int duree;
	private char valide;
	
	
	public Contrat(int idContrat, int idProduction, int idConsomateur,
			int quantite, Date dateDebut, int duree, char valide) {
		super();
		this.idContrat = idContrat;
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.duree = duree;
		this.valide = valide;
	}


	public int getIdContrat() {
		return idContrat;
	}


	public int getIdProduction() {
		return idProduction;
	}


	public int getIdConsomateur() {
		return idConsomateur;
	}


	public int getQuantite() {
		return quantite;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public int getDuree() {
		return duree;
	}


	public char getValide() {
		return valide;
	}
	
	
}
