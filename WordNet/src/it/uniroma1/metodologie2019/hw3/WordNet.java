package it.uniroma1.metodologie2019.hw3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WordNet implements Iterable<Synset>
{
	/*
	 * Array dei <Pos>
	 */
	private static final String[] ESTENSIONI_FILE_DATA = {"adj", "verb", "adv", "noun"};
	
	/*
	 * Percorso base da cui partire
	 */
	private static final String PERCORSO = "wordnet-releases/releases/WordNet-";
	
	/*
	 * Array di tutte le versioni di WordNet
	 */
	private static final String[] versioni= new File("wordnet-releases", "releases").list();
	
	/*
	 * versioni di WordNet già create
	 */
	private static List<WordNet> versioniCreate = new ArrayList<>();
	
	/*
	 * lista di tutti i synset
	 */
	private List<Synset> listaSynset = new ArrayList<Synset>();
	
	/*
	 * mappa dei synset (tipo di synset = insieme di synset di quel tipo
	 */
	private Map<String, Set<Synset>> setPosSynset = new HashMap<>();
	
	/*
	 * versione di WordNet 
	 */
	private String versione;
	
	
	private WordNet(String versione) 
	{ 	
		this.versione = versione;
		
		for (String s : ESTENSIONI_FILE_DATA)
 		{
			String percorso = PERCORSO + versione + File.separator + "dict" + File.separator + "data." + s;
			setPosSynset.put(s, Inizializzatore.leggi(percorso));
			versioniCreate.add(this);
		}
		
		creaListSynset();
	}
		
	/*
	 * ritorna la corrispondente istanza di WordNet,
	 * null se questa non esiste.
	 */
	public static WordNet getInstance(String s)
	{
		String sdc = "WordNet-" + s;
		for (String versione : versioni)
			if (versione.equals(sdc))					
			{
				for(WordNet wd : versioniCreate)		
					if(wd.getVersion().equals(s)) return wd;
				return new WordNet(s);
			}	
		return null;
	}
	
	/*
	 * Rende la lista dei Synset Iterabile
	 */
	@Override
	public Iterator<Synset> iterator()
	{
		return new Iterator<>()
				{
					private int k;
					
					@Override
					public boolean hasNext() { return k < listaSynset.size(); }
					
					@Override
					public Synset next() { return hasNext() ? listaSynset.get(k++) : null; }
				};
	}
	
	/*
	 * crea la lista di tutti i synset
	 */
	public void creaListSynset()
	{
		for (String s : ESTENSIONI_FILE_DATA)
		{
			Set<Synset> dizPerChiave = setPosSynset.get(s);
			dizPerChiave.stream().forEach(listaSynset::add);
		}
	}
	
	/*
	 * dato in input un lemma
	 * restituisce la lista dei synset che contengono quel lemma
	 */
	public List<Synset> getSynsets(String lemma) 
	{
		return listaSynset.stream()
				.filter(synset -> synset.contains(lemma))
				.collect(Collectors.toList());
	}
	
	/*
	 * dato in input un lemma e un POS
	 * restituisce la lista dei synset che contengono quel lemma e quel POS
	 */
	public List<Synset> getSynsets(String lemma, POS parteDelDiscorso) 
	{
		return listaSynset.stream()
				.filter(syn -> syn.getPOS().equals(parteDelDiscorso) && syn.contains(lemma))
				.collect(Collectors.toList());
	}
	
	/*
	 * dato in input l’ID, 
	 * restituisce il synset corrispondente, null se non presente.
	 */
	public Synset getSynsetFromID(String ID) 
	{	
		for (Synset syn : listaSynset)
			if (syn.getID().equals(ID)) return syn;
		return null;
		
	}
	
	/*
	 * restituisce uno stream di synset
	 */
	public Stream<Synset> stream() { return listaSynset.stream(); }
	
	/*
	 * restituisce la versione del Wordnet analizzato
	 */
	public String getVersion() { return versione; }
	
	
	/*
	 * dato in input un synset e una String, 
	 * restituisce una collezione di synset correlati
	 */
	public Collection<Synset> getRelatedSynsets(Synset syn, String s) 
	{
		Collection<Synset> collezione = new HashSet<>();

		for (String str : syn.getRelation())
		
			if (str.contains(s))
			{
				String appoggio = str.split(" ")[1] + str.split(" ")[2];
				listaSynset.stream()
						.filter(synset -> synset.getID().contentEquals(appoggio))
						.forEach(collezione::add);
			}
		
		return collezione;				
	}
	
	/*
	 * dato in input un synset e un WordNetRelation, 
	 * restituisce una collezione di synset correlati
	 */
	public Collection<Synset> getRelatedSynsets(Synset syn, WordNetRelation wdn) 
	{ return getRelatedSynsets(syn, Puntatori.getString((Puntatori)wdn)); }

}
