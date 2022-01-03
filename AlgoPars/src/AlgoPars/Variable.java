package AlgoPars;

public class Variable {
	String nom;
	String valeurString;
	String type;
	int valeurInt;
	char valeurChar;
	double valeurDouble;
	
	public Variable(String nom, int valeurInt)
	{
        this.nom            = nom   ;
        this.valeurInt 		= valeurInt;
        this.type = "entier";
	}
	
	public Variable(String nom, String valeurString )
	{
        this.nom            = nom   	  ;
        this.valeurString 	= valeurString;
        this.type = "chaine";
	}
	
	public Variable(String nom, char valeurChar )
	{
        this.nom            = nom   	  ;
        this.valeurChar 	= valeurChar;
        this.type = "caractere";
	}
	
	public Variable(String nom, float valeurDouble )
	{
        this.nom            = nom   	  ;
        this.valeurDouble 	= valeurDouble;
        this.type = "reel";
	}
	
	public String getNom() { return this.nom; }
	
	public String getType() { return this.type; }
	
	public int getEntier() { return this.valeurInt; }
	
	public double getReel() { return this.valeurDouble; }
	
	public char getCaractere() { return this.valeurChar; }
	
	public String getChaine() { return this.valeurString; }
}
