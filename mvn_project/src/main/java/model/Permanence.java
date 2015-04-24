package model;

public class Permanence {
	
	private int idSemaine;
	private int idConsommateur1;
	private int idConsommateur2;
	
	
	public Permanence(int idSemaine, int idConsommateur1, int idConsommateur2) {
		super();
		this.idSemaine = idSemaine;
		this.idConsommateur1 = idConsommateur1;
		this.idConsommateur2 = idConsommateur2;
	}


	public int getIdSemaine() {
		return idSemaine;
	}


	public int getIdConsommateur1() {
		return idConsommateur1;
	}


	public int getIdConsommateur2() {
		return idConsommateur2;
	}

}
