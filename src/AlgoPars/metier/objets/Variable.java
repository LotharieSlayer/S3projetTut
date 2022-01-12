package AlgoPars.metier.objets;

public class Variable {
	String nom;
	String valeurString;
	String type;
	int dimensions;
	int valeurInt;
	char valeurChar;
	double valeurDouble;
	boolean valeurBoolean;

	String[] tabStringUneDim;
	String[][] tabStringDeuxDims;
	String[][][] tabStringTroisDims;

	int[] tabIntUneDim;
	int[][] tabIntDeuxDims;
	int[][][] tabIntTroisDims;

	char[] tabCharUneDim;
	char[][] tabCharDeuxDims;
	char[][][] tabCharTroisDims;

	double[] tabDoubleUneDim;
	double[][] tabDoubleDeuxDims;
	double[][][] tabDoubleTroisDims;

	boolean[] tabBoolUneDim;
	boolean[][] tabBoolDeuxDims;
	boolean[][][] tabBoolTroisDims;

	public Variable(String nom, String type)
	{
		this.nom = nom;

		this.dimensions = 0;
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

	public Variable(String nom, String type, int couches, int colonnes, int lignes)
	{
		this.nom = nom;
		this.dimensions = 3;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntTroisDims = new int[couches][colonnes][lignes];
				break;

			case "double":
				this.type = "double";
				this.tabDoubleTroisDims = new double[couches][colonnes][lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharTroisDims = new char[couches][colonnes][lignes];
				break;

			case "chaine de caractères":
				this.type = "chaine de caractères";
				this.tabStringTroisDims = new String[couches][colonnes][lignes];
				break;
		
			case "booléen":
				this.type = "booléen";
				this.tabBoolTroisDims = new boolean[couches][colonnes][lignes];
				break;
		
			default:
				this.type = "Erreur de déclaration du type";
				break;
		}
	}

	public Variable(String nom, String type, int colonnes, int lignes)
	{
		this.nom = nom;
		this.dimensions = 2;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntDeuxDims = new int[colonnes][lignes];
				break;

			case "double":
				this.type = "double";
				this.tabDoubleDeuxDims = new double[colonnes][lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharDeuxDims = new char[colonnes][lignes];
				break;

			case "chaine de caractères":
				this.type = "chaine de caractères";
				this.tabStringDeuxDims = new String[colonnes][lignes];
				break;
		
			case "booléen":
				this.type = "booléen";
				this.tabBoolDeuxDims = new boolean[colonnes][lignes];
				break;
		
			default:
				this.type = "Erreur de déclaration du type";
				break;
		}
	}

	public Variable(String nom, String type, int lignes)
	{
		this.nom = nom;
		this.dimensions = 1;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntUneDim = new int[lignes];
				break;

			case "double":
				this.type = "double";
				this.tabDoubleUneDim = new double[lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharUneDim = new char[lignes];
				break;

			case "chaine de caractères":
				this.type = "chaine de caractères";
				this.tabStringUneDim = new String[lignes];
				break;
		
			case "booléen":
				this.type = "booléen";
				this.tabBoolUneDim = new boolean[lignes];
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

	public String getValue( int couches, int colonnes, int lignes ) {
		switch (this.type) {
			case "entier":
				return String.valueOf(this.tabIntTroisDims[couches][colonnes][lignes]);
		case "chaine de caractères":
				return this.tabStringTroisDims[couches][colonnes][lignes];
			case "caractère":
				return "" + this.tabCharTroisDims[couches][colonnes][lignes];
			case "réel":
				return String.valueOf(this.tabDoubleTroisDims[couches][colonnes][lignes]);
			case "booléen":
				if (this.tabBoolTroisDims[couches][colonnes][lignes] == true) 
					{ return "Vrai"; }
				else { return "Faux"; }
		}
		return null;
	}

	public String getValue(int colonnes, int lignes) {
		switch (this.type) {
			case "entier":
				return String.valueOf(this.tabIntTroisDims[colonnes][lignes]);
		case "chaine de caractères":
				return this.tabStringDeuxDims[colonnes][lignes];
			case "caractère":
				return "" + this.tabCharDeuxDims[colonnes][lignes];
			case "réel":
				return String.valueOf(this.tabDoubleDeuxDims[colonnes][lignes]);
			case "booléen":
				if (this.tabBoolDeuxDims[colonnes][lignes] == true) 
					{ return "Vrai"; }
				else { return "Faux"; }
		}
		return null;
	}

	public String getValue(int lignes) {
		switch (this.type) {
			case "entier":
				return String.valueOf(this.tabIntTroisDims[lignes]);
		case "chaine de caractères":
				return this.tabStringUneDim[lignes];
			case "caractère":
				return "" + this.tabCharUneDim[lignes];
			case "réel":
				return String.valueOf(this.tabDoubleUneDim[lignes]);
			case "booléen":
				if (this.tabBoolUneDim[lignes] == true) 
					{ return "Vrai"; }
				else { return "Faux"; }
		}
		return null;
	}

	public int getDimensions() { return this.dimensions; }

	public void affecterVariable(String valeur)
	{
		switch (this.type) {
			case "entier":
			valeur = valeur.replaceAll(" ", "");
			this.valeurInt = Integer.parseInt(valeur);
				break;
		case "chaine de caractères":
				this.valeurString = valeur;
				break;
			case "caractère":
				this.valeurChar = valeur.charAt(0);
				break;
			case "réel":
				valeur = valeur.replaceAll(" ", "");
				this.valeurDouble = Double.parseDouble(valeur);
				break;
			case "booléen":
				valeur = valeur.replaceAll(" ", "");
				if (valeur == "vrai") 
					{ this.valeurBoolean = true;
					  break; 
					}
				else { this.valeurBoolean = false;
					   break; 
				  }
		}
	}

	public void affecterVariable(String valeur, int couches, int colonnes, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntTroisDims[couches][colonnes][lignes] = Integer.parseInt(valeur);
				break;
		case "chaine de caractères":
				this.tabStringTroisDims[couches][colonnes][lignes] = valeur;
				break;
			case "caractère":
				this.tabCharTroisDims[couches][colonnes][lignes] = valeur.charAt(0);
				break;
			case "réel":
				valeur = valeur.replaceAll(" ", "");
				this.tabDoubleTroisDims[couches][colonnes][lignes] = Double.parseDouble(valeur);
				break;
			case "booléen":
				valeur = valeur.replaceAll(" ", "");
				if (valeur == "vrai") 
					{ this.tabBoolTroisDims[couches][colonnes][lignes] = true;
					  break; 
					}
				else { this.tabBoolTroisDims[couches][colonnes][lignes] = false;
					   break; 
				  }
		}
	}

	public void affecterVariable(String valeur, int colonnes, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntDeuxDims[colonnes][lignes] = Integer.parseInt(valeur);
				break;
		case "chaine de caractères":
				this.tabStringDeuxDims[colonnes][lignes] = valeur;
				break;
			case "caractère":
				this.tabCharDeuxDims[colonnes][lignes] = valeur.charAt(0);
				break;
			case "réel":
				valeur = valeur.replaceAll(" ", "");
				this.tabDoubleDeuxDims[colonnes][lignes] = Double.parseDouble(valeur);
				break;
			case "booléen":
				valeur = valeur.replaceAll(" ", "");
				if (valeur == "vrai") 
					{ this.tabBoolDeuxDims[colonnes][lignes] = true;
					  break; 
					}
				else { this.tabBoolDeuxDims[colonnes][lignes] = false;
					   break; 
				  }
		}
	}

	public void affecterVariable(String valeur, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntUneDim[lignes] = Integer.parseInt(valeur);
				break;
		case "chaine de caractères":
				this.tabStringUneDim[lignes] = valeur;
				break;
			case "caractère":
				this.tabCharUneDim[lignes] = valeur.charAt(0);
				break;
			case "réel":
				valeur = valeur.replaceAll(" ", "");
				this.tabDoubleUneDim[lignes] = Double.parseDouble(valeur);
				break;
			case "booléen":
				valeur = valeur.replaceAll(" ", "");
				if (valeur == "vrai") 
					{ this.tabBoolUneDim[lignes] = true;
					  break; 
					}
				else { this.tabBoolUneDim[lignes] = false;
					   break; 
				  }
		}
	}
}
