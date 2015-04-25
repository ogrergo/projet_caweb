package model;


public class Contrat {
	private int idContrat;
	private int idProduction;
	private int idConsomateur;
	private int quantite;
	private int dateDebut;
	private String nomUnite;
	private boolean valide;
	
	
	/*public Contrat(int idContrat, int idProduction, int idConsomateur,
			int quantite, int dateDebut, int duree, boolean valide) {
		super();
		this.idContrat = idContrat;
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.valide = valide;
	}*/
	
	public Contrat(int idContrat, int idProduction, int idConsomateur,
			int quantite, int dateDebut, int duree, String nomUnite, boolean valide) {
		super();
		this.idContrat = idContrat;
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.nomUnite = nomUnite;
		this.valide = valide;
	}
	
	public Contrat(int idProduction, int idConsomateur, int quantite,
			int dateDebut, boolean valide, String nomUnite) {
		super();
		this.idProduction = idProduction;
		this.idConsomateur = idConsomateur;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.valide = valide;
		this.nomUnite = nomUnite;
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

	public String getNomUnite(){
		return this.nomUnite;
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
