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

	/**
	 * Constructeur de la variable en fonction du type donné en paramètre
	 * @param nom Nom de la variable
	 * @param type Type de la variable
	 */
	public Variable(String nom, String type)
	{
		this.nom = nom;

		this.dimensions = 0;
		switch (type) {
			case "entier":
				this.type = "entier";
				break;

			case "réel":
				this.type = "réel";
				break;

			case "caractère":
				this.type = "caractère";
				break;

			case "chaine":
				this.type = "chaine";
				break;
		
			case "booléen":
				this.type = "booléen";
				break;
		
			default:
				this.type = "Erreur de déclaration du type";
				break;
		}
	}

	
	/**
	 * Constructeur de la variable en fonction du type donné en paramètre
	 * Tableau dimension : 1
	 * @param nom Nom de la variable
	 * @param type Type de la variable
	 */
	public Variable(String nom, String type, int lignes)
	{
		this.nom = nom;
		this.dimensions = 1;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntUneDim = new int[lignes];
				break;

			case "réel":
				this.type = "réel";
				this.tabDoubleUneDim = new double[lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharUneDim = new char[lignes];
				break;

			case "chaine":
				this.type = "chaine";
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

	/**
	 * Constructeur de la variable en fonction du type donné en paramètre
	 * Tableau dimension : 2
	 * @param nom Nom de la variable
	 * @param type Type de la variable
	 */
	public Variable(String nom, String type, int colonnes, int lignes)
	{
		this.nom = nom;
		this.dimensions = 2;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntDeuxDims = new int[colonnes][lignes];
				break;

			case "réel":
				this.type = "réel";
				this.tabDoubleDeuxDims = new double[colonnes][lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharDeuxDims = new char[colonnes][lignes];
				break;

			case "chaine":
				this.type = "chaine";
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

	
	/**
	 * Constructeur de la variable en fonction du type donné en paramètre
	 * Tableau dimension : 3
	 * @param nom Nom de la variable
	 * @param type Type de la variable
	 */
	public Variable(String nom, String type, int couches, int colonnes, int lignes)
	{
		this.nom = nom;
		this.dimensions = 3;

		switch (type) {
			case "entier":
				this.type = "entier";
				this.tabIntTroisDims = new int[couches][colonnes][lignes];
				break;

			case "réel":
				this.type = "réel";
				this.tabDoubleTroisDims = new double[couches][colonnes][lignes];
				break;

			case "caractère":
				this.type = "caractère";
				this.tabCharTroisDims = new char[couches][colonnes][lignes];
				break;

			case "chaine":
				this.type = "chaine";
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

	
	/* ----------------------------------------------- */
	/* GETTERS / SETTERS                               */
	/* ----------------------------------------------- */

	public String getNom() { return this.nom; }
	
	public String getType() { return this.type; }
	
	public String getValue() {
		switch (this.type) {
			case "entier":
				return String.valueOf(this.valeurInt);
		case "chaine":
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
		case "chaine":
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
		case "chaine":
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
		case "chaine":
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

	/**
	 * Méthode qui retourne les dimensions du tableau contenu dans Variable
	 * @return les dimensions du tableau
	 */
	public int getDimensions() { return this.dimensions; }

	public void affecterVariable(String valeur)
	{
		switch (this.type) {
			case "entier":
			valeur = valeur.replaceAll(" ", "");
			this.valeurInt = Integer.parseInt(valeur);
				break;
		case "chaine":
				this.valeurString = valeur;
				break;
			case "caractère":
				this.valeurChar = valeur.charAt(2);
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

	
	/**
	 * Méthode qui permet l'affectation de la variable (tableau 1 dimension)
	 * @param valeur 
	 * @param colonnes Y
	 * @param lignes X
	 */
	public void affecterVariable( String valeur, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntUneDim[lignes] = Integer.parseInt(valeur);
				break;
		case "chaine":
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

	/**
	 * Méthode qui permet l'affectation de la variable (tableau 2 dimensions)
	 * @param valeur 
	 * @param colonnes Y
	 * @param lignes X
	 */
	public void affecterVariable( String valeur, int colonnes, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntDeuxDims[colonnes][lignes] = Integer.parseInt(valeur);
				break;
		case "chaine":
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

	
	/**
	 * Méthode qui permet l'affectation de la variable (tableau 3 dimensions)
	 * @param valeur 
	 * @param couches Z
	 * @param colonnes Y
	 * @param lignes X
	 */
	public void affecterVariable( String valeur, int couches, int colonnes, int lignes )
	{
		switch (this.type) {
			case "entier":
				valeur = valeur.replaceAll(" ", "");
				this.tabIntTroisDims[couches][colonnes][lignes] = Integer.parseInt(valeur);
				break;
		case "chaine":
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
}
