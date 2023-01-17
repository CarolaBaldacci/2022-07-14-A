package it.polito.tdp.nyc.model;

public class Adiacenza {

	private String nomeZ1;
	private String nomeZ2;
	private double peso;
	public Adiacenza(String nomeZ1, String nomeZ2, double peso) {
		super();
		this.nomeZ1 = nomeZ1;
		this.nomeZ2 = nomeZ2;
		this.peso = peso;
	}
	public String getNomeZ1() {
		return nomeZ1;
	}
	public String getNomeZ2() {
		return nomeZ2;
	}
	public double getPeso() {
		return peso;
	}
	
	

}
