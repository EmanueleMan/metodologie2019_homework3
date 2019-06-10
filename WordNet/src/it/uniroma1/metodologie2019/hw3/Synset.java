package it.uniroma1.metodologie2019.hw3;

import java.util.List;
import java.util.Set;


public class Synset 
{
	/*
	 * campi che costruiscono il synset
	 */	
	private String offset;
	private String parteDiscorso;
	private Set<String> sinonimi;
	private List<String> glossa;
	private Set<String> esempi;
	private List<String> relazioni;
	
	public Synset(String offset, String parteDiscorso, Set<String> sinonimi, List<String> glossa, Set<String> esempi, List<String> relazioni) 
	{
		this.offset = offset;
		this.parteDiscorso = parteDiscorso;
		this.sinonimi = sinonimi;
		this.glossa = glossa;
		this.esempi = esempi;
		this.relazioni = relazioni;
	}
	
	/*
	 * ritorna la lista delle relazioni
	 */
	public List<String> getRelation() { return relazioni; }
	
	/*
	 * ritorna l'insiem dei sinonimi del synset
	 */
	public Set<String> getSynonyms() { return sinonimi; }
	
	/*
	 * data in input una parola
	 * ritorna true se il synset contiene tra i suoi sinonimi quella parola
	 * false altrimenti
	 */
	public boolean contains(String parola) 
	{
		for(String s : sinonimi)
			if (s.equals(parola)) return true;
		return false;
	}
	
	/*
	 * ritorna la glossa del synset
	 */
	public String getGloss() 
	{ 
		String newGlossa = "";
		
		for(String s : glossa)
			newGlossa += s + " ";
			
		return newGlossa.strip();
	}
	
	/*
	 * ritorna l’insieme degli esempi del synset
	 */
	public Set<String> getExamples() { return esempi; }
	
	/*
	 * ritorna l’offset del Synset
	 */
	public String getOffset () { return offset; }
	
	/*
	 * ritorna il POS corrispondente
	 */
	public POS getPOS() { return POS.getPosToString(parteDiscorso); }
	
	/*
	 * ritorna l’ID (offset + pos) del Synset; 
	 */
	public String getID() { return offset+parteDiscorso; }
	
}
