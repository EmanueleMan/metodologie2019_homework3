package it.uniroma1.metodologie2019.hw3;

public enum POS 
{
	NOUN, VERB, ADJECTIVE, ADVERB;
	
	public static POS getPosToString(String s)
	{
		switch (s)
		{
			case "n": 	return NOUN; 
			case "v": 	return VERB;
			case "a": 	return ADJECTIVE;
			case "s": 	return ADJECTIVE;
			case "r": 	return ADVERB;
			default : 	return null;
		}
	}
	
	
}