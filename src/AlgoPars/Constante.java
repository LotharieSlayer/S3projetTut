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
        this.type = "caractere";
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
	
	public String getValue() {
		String valeur;
		switch (this.type) {
			case "entier":
				valeur = String.valueOf(this.valeurInt);
				break;

			case "chaine de caractères":
				valeur = this.valeurString;
				break;

			case "caractère":
				valeur = "" + this.valeurChar;
				break;

			case "réel":
				valeur = String.valueOf(this.valeurDouble);
				break;

			case "booléen":
				if (this.valeurBoolean) valeur = "vrai";
				else { valeur = "faux"; }
				break;
		
			default:
				valeur = "Erreur";
				break;
		}
		return valeur;
	}
}
