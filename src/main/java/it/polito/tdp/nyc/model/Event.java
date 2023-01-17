package it.polito.tdp.nyc.model;

public class Event implements Comparable<Event> {

	public enum EventType{
		CONDIVISIONE,    //condivisione nuovo file
		RICONDIVISIONE,  //ricondivisione file con vertici adiacenti
		FILE_ELIMINATO   //eliminazione file
	}

	private String codiceNTA;
	private int durata;
	
	
	@Override
	public int compareTo(Event o) {
		return this.durata-o.durata;
	}
}
