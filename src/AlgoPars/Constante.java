package AlgoPars;

public class Constante {
	String nom;
	String valeurString;
	String type;
	int valeurInt;
	char valeurChar;
	double valeurDouble;
	boolean valeurBoolean;
	
	public Constante(String nom, int valeurInt)
	{
        this.nom            = nom   ;
        this.valeurInt 		= valeurInt;
        this.type = "entier";
	}
	
	public Constante(String nom, String valeurString )
	{
        this.nom            = nom   	  ;
        this.valeurString 	= valeurString;
        this.type = "chaine de caractères";
	}
	
	public Constante(String nom, char valeurChar )
	{
        this.nom            = nom   	  ;
        this.valeurChar 	= valeurChar;
        this.type = "caractère";
	}
	
	public Constante(String nom, double valeurDouble )
	{
        this.nom            = nom   	  ;
        this.valeurDouble 	= valeurDouble;
        this.type = "réel";
	}

	public Constante(String nom, boolean valeurBoolean )
	{
        this.nom            = nom   	  ;
        this.valeurBoolean 	= valeurBoolean;
        this.type = "booléen";
	}
	
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
