package it.uniroma1.metodologie2019.hw3;

public class SynsetPairing 
{
	/*
	 * Synset sorgente
	 */
	private Synset sorgente;
	
	/*
	 * Synset destinazione
	 */
	private Synset destinazione;
	
	public SynsetPairing(Synset sorgente, Synset destinazione)
	{
		this.sorgente = sorgente;
		this.destinazione = destinazione;
	}
	
	/*
	 * restituisce il Synset sorgente
	 */
	public Synset getSource() { return sorgente; } 
	
	/*
	 * restituisce il Synset destinazione
	 */
	public Synset getTarget() { return destinazione; }
	
	/*
	 * restituisce un valore di somiglianza dei due synset
	 */
	public double getScore() 
	{ 
		double score = 0.0;
		
		if (getSource().getGloss().equals(getTarget().getGloss()))  
			score += 0.5;
		
		if (getSource().getSynonyms().equals(getTarget().getSynonyms())) 
			score += 0.5;
		
		return score;
	}
}
