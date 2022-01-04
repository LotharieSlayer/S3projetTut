package AlgoPars;

public class Variable {
	String nom;
	String valeurString;
	String type;
	int valeurInt;
	char valeurChar;
	double valeurDouble;
	boolean valeurBoolean;

	public Variable(String nom, String type)
	{
		this.nom = nom;

		switch (type) {
			case "entier":
				this.type = "entier";
				break;

			case "double":
				this.type = "double";
				break;

			case "char":
				this.type = "char";
				break;

			case "chaine de caractères":
				this.type = "chaine de caractères";
				break;
		
			case "booléen":
				this.type = "booléen";
				break;
		
			default:
				this.type = "erreur de type";
				break;
		}
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
