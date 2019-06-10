package it.uniroma1.metodologie2019.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordNetMapping 
{
	/*
	 * WordNet sorgente
	 */
	private WordNet sorgente;
	
	/*
	 * WordNet destinazione
	 */
	private WordNet destinazione;
	
	public WordNetMapping(WordNet sorgente, WordNet destinazione)
	{
		this.sorgente = sorgente;
		this.destinazione = destinazione;
	}
	
	/*
	 * dato un Synset nella versione sorgente, 
	 * restituisce un Optional contenente il SynsetPairing che mappa il synset 
	 * sorgente con il miglior synset nella versione di destinazione
	 */
	public Optional<SynsetPairing> getMapping(Synset src)
	{
		
		if (sorgente.equals(destinazione)) return Optional.of(new SynsetPairing(src, src));
		
		List<Synset> listaSinonimi = new ArrayList<Synset>();
		src.getSynonyms().stream()
							.map(destinazione::getSynsets)
							.forEach(listaSinonimi::addAll);
		
		for (Synset r : listaSinonimi) 
		{
			SynsetPairing synsetParing = new SynsetPairing(src, r);
			
			if(synsetParing.getScore() == 1.0) return Optional.of(synsetParing);
			else continue;
		}
		
		return Optional.empty();
	}
	
}
