package it.uniroma1.metodologie2019.hw3;

public class Mapper 
{
	/*
	 * restituisce un oggetto di tipo WordNetMapping che fornisce 
	 * le mappature dalla wordnet di partenza a quella di destinazione.
	 */
	public static WordNetMapping map(WordNet sorgente, WordNet destinazione) 
	{
		return new WordNetMapping(sorgente, destinazione); 
	}
}
