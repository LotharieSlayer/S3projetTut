package AlgoPars;

public class Constante {
	static String nom;
	static String valeurString;
	static String type;
	static int valeurInt;
	static char valeurChar;
	static double valeurDouble;
	
	public Constante(String nom, int valeurInt)
	{
        Constante.nom            = nom   ;
        Constante.valeurInt 		= valeurInt;
        Constante.type = "entier";
	}
	
	public Constante(String nom, String valeurString )
	{
        Constante.nom            = nom   	  ;
        Constante.valeurString 	= valeurString;
        Constante.type = "chaine";
	}
	
	public Constante(String nom, char valeurChar )
	{
        Constante.nom            = nom   	  ;
        Constante.valeurChar 	= valeurChar;
        Constante.type = "caractere";
	}
	
	public Constante(String nom, double valeurDouble )
	{
        Constante.nom            = nom   	  ;
        Constante.valeurDouble 	= valeurDouble;
        Constante.type = "reel";
	}
	
	public String getNom() { return Constante.nom; }
	
	public String getType() { return Constante.type; }
	
	public int getEntier() { return Constante.valeurInt; }
	
	public double getReel() { return Constante.valeurDouble; }
	
	public char getCaractere() { return Constante.valeurChar; }
	
	public String getChaine() { return Constante.valeurString; }
	
	/*public String getValeur() { 
		switch(Constante.type)
		{
			
		}
	}*/
}
