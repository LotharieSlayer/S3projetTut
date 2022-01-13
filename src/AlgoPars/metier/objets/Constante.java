package AlgoPars.metier.objets;

public class Constante {
	String nom;
	String valeurString;
	String type;
	int valeurInt;
	char valeurChar;
	double valeurDouble;
	boolean valeurBoolean;
	
	/**
	 * Constructeur de la constante ayant comme type int
	 * @param nom
	 * @param valeurInt
	 */
	public Constante(String nom, int valeurInt)
	{
        this.nom            = nom   ;
        this.valeurInt 		= valeurInt;
        this.type = "entier";
	}
	
	/**
	 * Constructeur de la constante ayant comme type String
	 * @param nom
	 * @param valeurString
	 */
	public Constante(String nom, String valeurString )
	{
        this.nom            = nom   	  ;
        this.valeurString 	= valeurString;
        this.type = "chaine de caractères";
	}
	
	/**
	 * Constructeur de la constante ayant comme type char
	 * @param nom
	 * @param valeurChar
	 */
	public Constante(String nom, char valeurChar )
	{
        this.nom            = nom   	  ;
        this.valeurChar 	= valeurChar;
        this.type = "caractère";
	}
	
	/**
	 * Constructeur de la constante ayant comme type double
	 * @param nom
	 * @param valeurDouble
	 */
	public Constante(String nom, double valeurDouble )
	{
        this.nom            = nom   	  ;
        this.valeurDouble 	= valeurDouble;
        this.type = "réel";
	}

	/**
	 * Constructeur de la constante ayant comme type booléen
	 * @param nom
	 * @param valeurBoolean
	 */
	public Constante(String nom, boolean valeurBoolean )
	{
        this.nom            = nom   	  ;
        this.valeurBoolean 	= valeurBoolean;
        this.type = "booléen";
	}
	
	
	/* ----------------------------------------------- */
	/* GETTERS / SETTERS                               */
	/* ----------------------------------------------- */

	public String getNom() { return this.nom; }
	
	public String getType() { return this.type; }
	
	public int getInt() { return this.valeurInt; }
	
	public double getDouble() { return this.valeurDouble; }
	
	public char getChar() { return this.valeurChar; }
	
	public boolean getBoolean() { return this.valeurBoolean; }
	
	public String getString() { return this.valeurString; }
	
	public String getValue() {
		switch (this.type) {
			case "entier":
				return String.valueOf(this.valeurInt);
		case "chaine de caractères":
				return this.valeurString;
			case "caractère":
				return "" + this.valeurChar;
			case "réel":
				return String.valueOf(this.valeurDouble);
			case "booléen":
				if (this.valeurBoolean == true) 
					{ return "Vrai"; }
				else { return "Faux"; }
		}
		return null;
	}
}
