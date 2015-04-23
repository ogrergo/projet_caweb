package model;

public class Disponibilite {

	private int idSemaine;
	private int idConsommateur;
		
	public Disponibilite(int idSemaine, int idConsommateur) {
		super();
		this.idSemaine = idSemaine;
		this.idConsommateur = idConsommateur;
	}
	public int getIdSemaine() {
		return idSemaine;
	}
	public int getIdConsommateur() {
		return idConsommateur;
	}
	
	
}
