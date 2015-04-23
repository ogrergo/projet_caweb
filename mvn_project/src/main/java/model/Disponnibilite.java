package model;

public class Disponnibilite {

	private int idSemaine;
	private int idConsommateur;
		
	public Disponnibilite(int idSemaine, int idConsommateur) {
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
