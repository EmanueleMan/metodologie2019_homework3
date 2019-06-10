package it.uniroma1.metodologie2019.hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Inizializzatore 
{
	/*
	 * metodo che crea i Synset
	 */
	public static Synset creaSynset(String riga) 
	{
		String stringaDxSbarra = riga.substring(0, riga.indexOf('|'));	
		String[] listaConOffset = stringaDxSbarra.split(" ");
		
		String offset = listaConOffset[0];
		
		String tipo = listaConOffset[2];
		
		Set<String> sinonimi = 
		Arrays.stream(listaConOffset)
				.skip(4)
				.filter(s -> (!contieneNumero(s) && s.length() != 1 && s.length() != 2))
				.collect(Collectors.toSet());
			
		List<String> relazioni = new ArrayList<String>();
		
		for (int k = 4; k < listaConOffset.length; k++)
			for (Puntatori i: Puntatori.values())
					if(listaConOffset[k].equals(Puntatori.getString(i)) )
						relazioni.add(listaConOffset[k] + " " + listaConOffset[k+1] + " " + listaConOffset[k+2]);
	    
		
		String stringaSxSbarra = riga.substring(riga.indexOf('|')+2);
		String[] listaConGlossa = stringaSxSbarra.split(";");
		
		List<String> glossa = Arrays.stream(listaConGlossa).filter(s -> !s.contains("\"")).collect(Collectors.toList());
		
		Set<String> esempi = new HashSet<String>(); 
		Arrays.stream(listaConGlossa)
				.filter(s -> s.contains("\""))
				.forEach(s -> esempi.add(s.replace("\"", "").strip()));
		
		return new Synset(offset, tipo, sinonimi, glossa, esempi, relazioni);
	}
	
	/*
	 * controlla se in una stringa non sono presenti numeri
	 */
	private static boolean contieneNumero(String s)
	{
		if(s.contains("0") || s.contains("1") || s.contains("2") || s.contains("3") || s.contains("4") || s.contains("5") || 
				s.contains("6") || s.contains("7") || s.contains("8") || s.contains("9")) return true;
		return false;
	}
	
	/*
	 * dato in input un percorso,
	 * legge il file, crea il synset di ogni riga
	 * e lo aggiunge ad un insieme.
	 * Infine ritorna l'insieme di tutti i synset creati
	 */
	public static Set<Synset> leggi(String percorso) 
	{
		Set<Synset> setSynset = new HashSet<>();
		try(BufferedReader br = new BufferedReader(new FileReader(percorso)))
		{
			while(br.ready())
			{	
				setSynset = br.lines().filter(riga -> riga.charAt(0)!=' ').map(Inizializzatore::creaSynset).collect(Collectors.toSet());; 
			}	
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		
		return setSynset;
	}

}
