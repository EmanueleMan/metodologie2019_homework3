package it.uniroma1.metodologie2019.hw3;

public enum Puntatori implements WordNetRelation
{
	ANTONYM("!"),
	HYPERNYM("@"), 
	INSTANCE_HYPERNYM("i"),
	HYPONYM("~"),
	INSTANCE_HYPONYM("~i"),
	MEMBER_HOLONYM("#m"),
	SUBSTANCE_HOLONYM("#s"), 
	PART_HOLONYM("#p"), 
	MEMBER_MERONYM("%m"), 
	SUBSTANCE_MERONYM("%s"), 
	PART_MERONYM("%p"), 
	ATTRIBUTE("="), 
	DERIVATIONALLY_RELATED_FORM("+"),         
	DOMAIN_OF_SYNSET_TOPIC(";c"), 
	MEMBER_OF_THIS_DOMAIN_TOPIC("-c"), 
	DOMAIN_OF_SYNSET_REGION(";r"), 
	MEMBER_OF_THIS_DOMAIN_REGION("-r"), 
	DOMAIN_OF_SYNSET_USAGE(";u"), 
	MEMBER_OF_THIS_DOMAIN_USAGE("-u"),

	ENTAILMENT("*"),
	CAUSE(">"),
	ALSO_SEE("^"),
	VERB_GROUP("$"),
    DERIVED_FROM_ADJECTIVE("\\"),
	
    SIMILAR_TO("&"),
    PARTICIPLE_OF_VERB("<"),
    PERTAINYM_PERTAINS_TO_NOUN("\\");

	private String puntatore;
	
	Puntatori(String puntatore) { this.puntatore = puntatore; }
	
	public static String getString(Puntatori s)
	{
		return s.puntatore;
	}
	
}


