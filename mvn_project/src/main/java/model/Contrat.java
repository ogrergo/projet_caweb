package model;


public class Contrat {
	private int idContrat;
	private int idProduction;
	private int idConsomateur;
	private int quantite;
	private int dateDebut;
	private boolean valide;
	
	
	public Contrat(int idContrat, int idProduction, int idConsomateur,
			int quantite, int dateDebut, int duree, boolean valide) {
		super();
		this.idContrat = idContrat;
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.valide = valide;
	}
	
	public Contrat(int idProduction, int idConsomateur, int quantite,
			int dateDebut, boolean valide) {
		super();
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
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


	public int getDateDebut() {
		return dateDebut;
	}


	public boolean getValide() {
		return valide;
	}

	public void validate() {
		valide = true;
	}
	
	public void setDateDebut(int date) {
		dateDebut = date;
	}
	
}
