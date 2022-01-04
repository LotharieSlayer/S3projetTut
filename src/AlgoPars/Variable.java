package AlgoPars;

public class Variable {
	String nom;
	String valeurString;
	String type;
	int valeurInt;
	char valeurChar;
	double valeurDouble;

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
	
	public int getEntier() { return this.valeurInt; }
	
	public double getReel() { return this.valeurDouble; }
	
	public char getCaractere() { return this.valeurChar; }
	
	public String getChaine() { return this.valeurString; }
}
