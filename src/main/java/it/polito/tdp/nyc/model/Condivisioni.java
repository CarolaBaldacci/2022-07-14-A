package it.polito.tdp.nyc.model;

public class Condivisioni {

	private String codiceNTA;
	private int condivisioni;
	
	public Condivisioni(String codiceNTA, int condivisioni) {
		super();
		this.codiceNTA = codiceNTA;
		this.condivisioni = condivisioni;
	}
	public String getCodiceNTA() {
		return codiceNTA;
	}
	public int getCondivisioni() {
		return condivisioni;
	}
	@Override
	public String toString() {
		return "CodiceNTA=" + codiceNTA + ", #condivisioni=" + condivisioni + "\n";
	}
	
	
	
	
}
