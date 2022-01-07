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

			case "caractère":
				this.type = "caractère";
				break;

			case "chaine de caractères":
				this.type = "chaine de caractères";
				break;
		
			case "booléen":
				this.type = "booléen";
				break;
		
			default:
				this.type = "Erreur de déclaration du type";
				break;
		}
	}
	
	public String getNom() { return this.nom; }
	
	public String getType() { return this.type; }
	
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
