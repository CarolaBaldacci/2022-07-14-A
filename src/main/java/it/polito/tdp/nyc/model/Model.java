package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenzeList;

	public Model() {
		super();
		this.dao = new NYCDao();
		this.adiacenzeList= new ArrayList<>();
	}
	
	public List<String> getBorghi(){
		return this.dao.getAllBorghi();
	}
	
	public String creaGrafo(String b) {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//aggiungo vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici(b));
		//aggiungo archi
		for(Adiacenza a : dao.getArchi(b)) {
			if(!grafo.containsEdge(a.getNomeZ2(), a.getNomeZ1()) &&
					a.getPeso()>0) {
				Graphs.addEdgeWithVertices(this.grafo, a.getNomeZ1(), a.getNomeZ2(), a.getPeso());
				adiacenzeList.add(a);
			}
		}
		
		return "Grafo creato #Vertici: "+this.grafo.vertexSet().size()+"#Archi: "+this.grafo.edgeSet().size();
	}
	
	public double pesoMedio() {
		double media =0;
		double pesoTot=0;
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			pesoTot+= this.grafo.getEdgeWeight(e);
		}
		media =pesoTot/this.grafo.edgeSet().size();
		return media;
	}
	
	public List<Adiacenza> getArchiPesoMinore(){
		List<Adiacenza> result= new ArrayList<>() ;
		double media = pesoMedio();
		for(Adiacenza a: adiacenzeList) {
			if(a.getPeso()>media) {
				result.add(a);
			}
		}
    	Collections.sort(result, new Comparator<Adiacenza>() {
			@Override
			public int compare(Adiacenza a1, Adiacenza a2) {
				// TODO Auto-generated method stub
				return (int) (a2.getPeso()-a1.getPeso());
			}});
    	
		return result;
	}
	
}
