package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {

	//dati INGRESSO
	private double prob;
	private double durata;
	private Graph <String, DefaultWeightedEdge> grafo;
	//dati USCITA
	private List<Condivisioni> condivisioniList;
	//MODELLO del MONDO
	private int giorniRimanenti;
	
	
	
	//CODA degli eventi
	private PriorityQueue<Event> queue;
	
	
	
	public Simulator(double prob, int durata, Graph<String, DefaultWeightedEdge> grafo) {
		super();
		this.prob = prob;
		this.durata = durata;
		this.grafo = grafo;
		condivisioniList= new ArrayList<>();
	}

	public void init() {
		giorniRimanenti=100;
		
	}
	
	public void run() {
		
	}
	
	public void processEvent() {
		
	}

	public List<Condivisioni> getCondivisioni() {
		return condivisioniList;
	}
}
