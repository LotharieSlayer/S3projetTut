package AlgoPars.metier;

import java.util.ArrayList;
import java.util.HashMap;

import AlgoPars.Main;
import AlgoPars.metier.objets.Constante;
import AlgoPars.metier.objets.Variable;

import java.io.Console;

public class Interpreteur {
	
	static ArrayList<String> console = new ArrayList<String>();
	static HashMap<String, String> traceur = new HashMap<String, String>();

	static HashMap<String, String> hmLire = new HashMap<String, String>();

	static ArrayList<Constante> constantes = new ArrayList<Constante>();
	static ArrayList<Variable> variables = new ArrayList<Variable>();

	ArrayList<Boolean> conditionsListe = new  ArrayList<Boolean>();

	int numCondition = 0;
	int numLigneBoucle = 0;
	int numLigne = 0;
	int numLigneIgnorer = 0;

	/**
	 * getConsole()
	 * @return Récupère la console se trouvant dans l'interprèteur
	 */
	public ArrayList<String> getConsole() {
		return console;	
	}
		
	/**
	 * getTraceur()
	 * @return Récupère le traceur dans l'interprèteur
	 */
	public HashMap<String, String> getTraceur() {
		return traceur;
	}
	
	/**
	 * getNumLigne()
	 * @return Récupère le numéro de la ligne en exécution
	 */
	public int getNumLigne() {
		return numLigne;
	}

	/**
	 * Affceter le numéro de la ligne en exécution
	 * @param numLigne
	 * @return
	 */
	public int setNumLigne(int numLigne) {
		return this.numLigne = numLigne;
	}

	/**
	 * Récupère toutes les constantes
	 * @return Arraylist de constantes
	 */
	public static ArrayList<Constante> getConstantes(){
		return constantes;
	}

	/**
	 * Récupère toutes les variables
	 * @return Arraylist de variables
	 */
	public static ArrayList<Variable> getVariables(){
		return variables;
	}
	
	/**
	 * Permet de déclarer la constante avec la String pris en paramètre
	 * @param constante
	 */
	public void declarerConstante(String constante) {
		String[] tempString = constante.split("<--");
		if(tempString[1] != null)
		{
			if(tempString[1].charAt(0) >= '0' && tempString[1].charAt(0) <= '9')
			{
				String[] valeur = tempString[1].split(".", 2);
				if(valeur[1] == null)
				{
					constantes.add(new Constante(tempString[0], Integer.parseInt(tempString[1])));
					traceur.put(tempString[0], tempString[1]);
				}
				else{
					constantes.add(new Constante(tempString[0], Double.parseDouble(tempString[1])));
					traceur.put(tempString[0], tempString[1]);
				}
			}
			else if (tempString[1].equals("vrai"))
			{
				constantes.add(new Constante(tempString[0], true));
				traceur.put(tempString[0], "VRAI");
			}
			else if (tempString[1].equals("faux"))
			{
				constantes.add(new Constante(tempString[0], false));
				traceur.put(tempString[0], "FAUX");
			}
			else
			{
				if(tempString[1].charAt(0) == '\'')
				{
					if(tempString[1].charAt(tempString[1].length() - 1) == '\'')
						constantes.add(new Constante(tempString[0], tempString[1]));
					else { console.add("Erreur de déclaration de constante"); }
				}

				else if(tempString[1].charAt(0) == '"')
				{
					if(tempString[1].charAt(tempString[1].length() - 1) == '\"'){
						constantes.add(new Constante(tempString[0], tempString[1]));
						traceur.put(tempString[0], tempString[1]);
					}
					else { console.add("Erreur de déclaration de constante"); }
				}
				else
					console.add("Erreur de déclaration de constante");
			}
		}
		else
			console.add("Erreur de déclaration de constante");
	}
	
	/**
	 * Permet de déclarer la variable avec la String pris en paramètre
	 * @param variable
	 */
	public void declarerVariable(String variable) {
		String[] tempStringType = variable.split(":");
		String[] tempStringVariables = tempStringType[0].replaceAll(" ", "").split(",");

		for ( int i = 0 ; i < tempStringVariables.length ; i++)
		switch (tempStringType[1]) {
			case "entier":
				variables.add(new Variable(tempStringVariables[i], "entier"));
				traceur.put(tempStringVariables[i], "NULL");
				break;

			case "réel":
				variables.add(new Variable(tempStringVariables[i], "réel"));
				traceur.put(tempStringVariables[i], "NULL");
				break;

			case "chaine":
				variables.add(new Variable(tempStringVariables[i], "chaine"));
				traceur.put(tempStringVariables[i], "NULL");
				break;
		
			case "caractère":
				variables.add(new Variable(tempStringVariables[i], "caractère"));
				traceur.put(tempStringVariables[i], "NULL");
				break;

			case "booléen":
				variables.add(new Variable(tempStringVariables[i], "booléen"));
				traceur.put(tempStringVariables[i], "NULL");
				break;
			default:
				if(tempStringType[1].contains("tableau"))
				{
					String tempString = tempStringType[1].replaceAll("tableau", "");
					String[] tempStringTab = verifierCaractereTab(tempString);

					if(tempString.contains("d'entier"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "entier", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "entier", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "entier", Integer.parseInt(tempStringTab[0])));
						}
					}
					if(tempString.contains("de réel"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "réel", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "réel", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "réel", Integer.parseInt(tempStringTab[0])));
						}
					}
					if(tempString.contains("de caractère"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "caractère", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "caractère", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "caractère", Integer.parseInt(tempStringTab[0])));
						}
					}
					if(tempString.contains("de chaine"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine", Integer.parseInt(tempStringTab[0])));
						}
					}
					if(tempString.contains("de booléen"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "booléen", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "booléen", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "booléen", Integer.parseInt(tempStringTab[0])));
						}
					}
				}
				else
					console.add("Type de base non reconnu");
				break;
		}
	}
	
	/**
	 * Permet de vérifier si ya un caractère
	 * @param car
	 * @param chaine
	 * @return la chaine sans les caractères sinon null
	 */
	public String verifierCaractere(char car, String chaine)
	{
		char[] tabCar = new char[2];
		int[] indexCar = new int[2];
		if(car == '(') { tabCar[0] = '('; tabCar[1] = ')'; }
		else if(car == '"') { tabCar[0] = '"'; tabCar[1] = '"'; }
		else if(car == '\'') { tabCar[0] = '\''; tabCar[1] = '\''; }
		
        for(int i=0; i<chaine.length(); i++) 
        {
            char chr = chaine.charAt(i);
                if(tabCar[0] == chr)
                {  
                	indexCar[0] = i;
					break;
                }
        }
        
        for(int i=0; i<chaine.length(); i++) 
        {
            char chr = chaine.charAt(i);
                if(tabCar[1] == chr)
                {  
                	indexCar[1] = i;
                }
        }
        
        if(indexCar[0] < indexCar[1]) { return chaine.substring(indexCar[0] + 1,indexCar[1]);}
		return null;
	}

	/**
	 * Permet de vérifier si ya un caractère pour les tableaux
	 * @param chaine
	 * @return la chaine sans les caractères sinon null
	 */
	public String[] verifierCaractereTab(String chaine)
	{
		char[] tabCar = new char[2];
		int[] indexCar = new int[2];

		indexCar[0]= 0;
		indexCar[1]= 0;

		String[] tabTemp = new String[3];
		String chaineTemp = chaine;

		tabCar[0] = '['; tabCar[1] = ']';

		for(int i = 0; i<3;i++)
		{
			if(chaineTemp.contains("["))
			{
				for(int j=0; j<chaineTemp.length(); j++) 
				{
					char chr = chaineTemp.charAt(j);
						if(tabCar[0] == chr)
						{  
							indexCar[0] = j;
							break;
						}
				}
				
				for(int j=0; j<chaineTemp.length(); j++) 
				{
					char chr = chaineTemp.charAt(j);
						if(tabCar[1] == chr)
						{  
							indexCar[1] = j;
							break;
						}
				}
				if(indexCar[0] < indexCar[1]) { 
					tabTemp[i] = chaineTemp.substring(indexCar[0] + 1, indexCar[1]);
					chaineTemp = chaineTemp.substring(indexCar[1] + 1);
				}
			}
		}
		return tabTemp;
	}
	
	/**
	 * Permet de chercher une constante dans l'Arraylist des constantes
	 * @param chaine
	 * @return l'index si réussi sinon -1
	 */
	public int chercherConstante(String chaine)
	{
		String chaineTemp = chaine.replaceAll(" ", "");
		for(int i = 0; i < constantes.size(); i++)
		{
			if(constantes.get(i).getNom().equals(chaineTemp))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Permet de chercher une constante dans l'Arraylist des variables
	 * @param chaine
	 * @return l'index si réussi sinon -1
	 */
	public int chercherVariable(String chaine)
	{
		String chaineTemp = chaine.replaceAll(" ", "");
		for(int i = 0; i < variables.size(); i++)
		{
			if(variables.get(i).getNom().equals(chaineTemp))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Permet de détecter le type de la chaine passé en paramètre.
	 * @param chaine
	 * @return le type
	 */
	public String detecterType(String chaine)
	{
		String chaineTemp[] = new String[2];

		chaineTemp[0] = verifierCaractere('"', chaine);
		chaineTemp[1] = verifierCaractere('\'', chaine);

		chaine = chaine.replaceAll(" ", "");

		if(chaineTemp[0] != null)
		{
			return "chaine";
		}
		else if (chaineTemp[1] != null)
		{
			return "caractère";
		}
		else if(chaine.matches("^[0-9]+$"))
		{
			return "entier";
		}
		else if(chaine.matches("^[0-9]+$") && chaine.contains("."))
		{
			return "réel";
		}
		else if (chaine.equals("vrai") || chaine.equals("faux"))
		{
			return "booléen";
		}
		else
		{
			return "expression";
		}
	}

	/**
	 * Permet de supprimer les espaces en début de chaine
	 * @param chaine
	 * @return string sans espace au début
	 */
	public String supprimerEspacesDebut(String chaine)
	{
		//Supprimer les espaces du début
		int indice = 0;
		for(int i=0; i< chaine.length(); i++) 
		{
			char chr = chaine.charAt(i);
				if(' ' != chr)
				{  
					indice = i;
					break;
				}
		}

		return chaine.substring(indice,chaine.length());
	}

	/**
	 * Règle les problèmes de regex si fausses en ajoutant des caractères
	 * @param chaine
	 * @return la regex
	 */
	public String regexFriendly(String chaine)
	{
		String chaineTemp = chaine;

		for(int i=0; i < chaine.length(); i++)
		{
			char[] chr = new char[3];
			chr[2] = chaine.charAt(i);
			if((chr[2] == '+' || chr[2] == '×') && chr[0] != '\\'  &&  chr[1] != '\\')
			{
				chaineTemp = chaineTemp.substring(0,i) + "\\" + chaineTemp.substring(i, chaineTemp.length());
			}
		}
		return chaineTemp;
	}

	/**
	 * Permet de remplacer le nom de la constante/variable par sa valeur
	 * @param chaine
	 * @return valeur
	 */
	public String remplacerNomParValeur(String chaine){

		String chaineTemp = chaine.replaceAll(" ", "");

		//Méthode pour remplacer les variables et constantes par leur valeur
		for(int i = 0; i < constantes.size(); i++)
		{
			if(chaineTemp.contains(constantes.get(i).getNom()))
			{
				chaineTemp = chaineTemp.replaceAll(constantes.get(i).getNom(), constantes.get(i).getValue());
			}
		}

		for(int i = 0; i < variables.size(); i++)
		{
			if(chaineTemp.contains(variables.get(i).getNom()))
			{
				int[] index = new int[3];
				String chaineTemporaire;
				String[] tempStringTab;
				switch(variables.get(i).getDimensions())
				{
					case 0:
						chaineTemp = chaineTemp.replaceAll(variables.get(i).getNom(), variables.get(i).getValue());
						break;
					case 1:
						index[0] = chaineTemp.indexOf(variables.get(i).getNom());

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if('[' == chr)
							{  
								index[1] = j;
								break;
							}
						}

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if(']' == chr)
							{  
								index[2] = j;
							}
						}

						chaineTemporaire = chaineTemp.substring(index[0], index[2]);
						tempStringTab = verifierCaractereTab(chaineTemporaire);
						chaineTemporaire = chaineTemp.substring(index[0], index[1] - 1);
						chaineTemp = chaineTemp.replaceAll(chaineTemporaire + "\\[" + tempStringTab[0] + "\\]", variables.get(i).getValue(Integer.parseInt(tempStringTab[0])));
					break;
					case 2:
						index[0] = chaineTemp.indexOf(variables.get(i).getNom());

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if('[' == chr)
							{  
								index[1] = j;
								break;
							}
						}

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if(']' == chr)
							{  
								index[2] = j;
							}
						}

						chaineTemporaire = chaineTemp.substring(index[0], index[2]);
						tempStringTab = verifierCaractereTab(chaineTemporaire);
						chaineTemporaire = chaineTemp.substring(index[0], index[1] - 1);
						chaineTemp = chaineTemp.replaceAll(chaineTemporaire + "\\[" + tempStringTab[0] + "\\]" + "\\[" + tempStringTab[1] + "\\]", variables.get(i).getValue(Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1])));
					break;
					case 3:
						index[0] = chaineTemp.indexOf(variables.get(i).getNom());

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if('[' == chr)
							{  
								index[1] = j;
								break;
							}
						}

						for(int j=0; j < chaineTemp.length() + 1; j++) 
						{
							char chr = chaine.charAt(j);
							if(']' == chr)
							{  
								index[2] = j;
							}
						}

						chaineTemporaire = chaineTemp.substring(index[0], index[2]);
						tempStringTab = verifierCaractereTab(chaineTemporaire);
						chaineTemporaire = chaineTemp.substring(index[0], index[1] - 1);
						chaineTemp = chaineTemp.replaceAll(chaineTemporaire + "\\[" + tempStringTab[0] + "\\]" + "\\[" + tempStringTab[1] + "\\]" + "\\[" + tempStringTab[2] + "\\]", variables.get(i).getValue(Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]), Integer.parseInt(tempStringTab[2])));
						break;
				}
			}
		}
		return chaineTemp;	
	}

	/**
	 * Permet de réaliser les différents calculs
	 * @param chaine
	 * @return résultat
	 */
	public String calculateur(String chaine)
	{
		String chaineTemp = remplacerNomParValeur(chaine);
		String[][] valeurEntreParenthese= new String[2][2]; 
		int[] indexOperateur = new int[16];

		//Priorité 0
		
		for(int i = 0; i<2; i++)
		{
			indexOperateur[0] = chaineTemp.lastIndexOf("(");
			if(indexOperateur[0] != - 1 && chaineTemp.contains(")"))
			{
				String ecrireTemp;
				ecrireTemp = verifierCaractere('(', chaineTemp.substring(indexOperateur[0]));
				if(ecrireTemp != null) {
					valeurEntreParenthese[i][0] = "ValeurEntreParenthese" + i;
					valeurEntreParenthese[i][1]= ecrireTemp;
					chaineTemp = chaineTemp.replaceAll("\\(" + regexFriendly(ecrireTemp) + "\\)", valeurEntreParenthese[i][0]);
				}
			}
		}

		//Priorité 1
		indexOperateur[1] = chaineTemp.lastIndexOf("<");
		indexOperateur[2] = chaineTemp.lastIndexOf(">");
		indexOperateur[3] = chaineTemp.lastIndexOf("<=");
		indexOperateur[4] = chaineTemp.lastIndexOf(">=");
		indexOperateur[5] = chaineTemp.lastIndexOf("/=");
		indexOperateur[6] = chaineTemp.lastIndexOf("=");

		int OperationLaMoinsPrioritaire = 0;

		//Gestion des conflits entre opérateurs
		if(indexOperateur[1] == indexOperateur[3]){ indexOperateur[1] = 0;}
		if(indexOperateur[2] == indexOperateur[4]){ indexOperateur[2] = 0;}
		if(indexOperateur[6] == indexOperateur[3] + 1 || indexOperateur[6] == indexOperateur[4] + 1 || indexOperateur[6] == indexOperateur[5] + 1){ indexOperateur[6] = 0;}


		//Détermination de l'opérateur le plus à droite
		int[] ValeurMax = new int[3];
		ValeurMax[0] = 0;
		for(int i = 1; i < 7; i++)
		{
			if(indexOperateur[i] > ValeurMax[0])
			{
				ValeurMax[0] = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		//Exécution récursive de l'opération la moins prioritaire
		String[] expression;
		switch(OperationLaMoinsPrioritaire)
		{
			/*case 1:
				expression = chaineTemp.split("\\<", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = inferieur(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 2:
				expression = chaineTemp.split("\\>", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = superieur(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 3:
				expression = chaineTemp.split("\\<\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = inferieurEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 4:
				expression = chaineTemp.split("\\>\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = superieurEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 5:
				expression = chaineTemp.split("\\/\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = pasEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 6:
				expression = chaineTemp.split("\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = egalite(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;*/
		}
		OperationLaMoinsPrioritaire = 0;

		//Priorité 2
		indexOperateur[7] = chaineTemp.lastIndexOf("OU");
		indexOperateur[8] = chaineTemp.lastIndexOf("+");
		indexOperateur[9] = chaineTemp.lastIndexOf("-");

		//Détermination de l'opérateur le plus à droite
		ValeurMax[1] = 0;
		for(int i = 7; i < 10; i++)
		{
			if(indexOperateur[i] > ValeurMax[1])
			{
				ValeurMax[1] = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		switch(OperationLaMoinsPrioritaire)
		{
			/*case 7:
				expression = chaineTemp.split("OU", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = ouCondition(verifierCondition(calculateur(expression[0])), verifierCondition(calculateur(expression[1])));
				valeurEntreParenthese = null;
				break;*/
			case 8:
				expression = chaineTemp.split("\\+", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = addition(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 9:
				expression = chaineTemp.split("\\-", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = soustraction(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
		}

		//Priorité 3
		indexOperateur[10] = chaineTemp.lastIndexOf("ET");
		indexOperateur[11] = chaineTemp.lastIndexOf("×");
		indexOperateur[12] = chaineTemp.lastIndexOf("/");
		indexOperateur[13] = chaineTemp.lastIndexOf("mod");

		//Détermination de l'opérateur le plus à droite
		
		ValeurMax[2] = 0;
		for(int i = 10; i < 13; i++)
		{
			if(indexOperateur[i] > ValeurMax[2])
			{
				ValeurMax[2] = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		switch(OperationLaMoinsPrioritaire)
		{
			/*case 10:
				expression = chaineTemp.split("ET", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = etCondition(verifierCondition(calculateur(expression[0])), verifierCondition(calculateur(expression[1])));
				valeurEntreParenthese = null;
				break;*/
			case 11:
				expression = chaineTemp.split("\\×", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = multiplication(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 12:
				expression = chaineTemp.split("\\/", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = division(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 13:
				expression = chaineTemp.split("mod", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = modulo(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
		}
		
		indexOperateur[14] = chaineTemp.lastIndexOf("^");

		if(indexOperateur[14] != -1)
		{
			expression = chaineTemp.split("\\^", 2);
			if(valeurEntreParenthese != null)
			{
				expression = remplacerValeur(expression, valeurEntreParenthese);
			}
			chaineTemp = puissance(calculateur(expression[0]), calculateur(expression[1]));
			valeurEntreParenthese = null;
		}

		//Priorité 4
		indexOperateur[15] = chaineTemp.lastIndexOf("NON");

		/*if(indexOperateur[15] != -1)
		{
			expression = chaineTemp.split("NON", 2);
			if(valeurEntreParenthese != null)
			{
				expression = remplacerValeur(expression, valeurEntreParenthese);
			}
			Valeur = nonCondition(verifierCondition(calculateur(expression[1])));
			valeurEntreParenthese = null;
		}*/
		if(ValeurMax[0] == 0 && ValeurMax[1] == 0 && ValeurMax[2] == 0)
		{
			if(valeurEntreParenthese != null)
			{
				for(int i = 0; i<2; i++)
				{
					if(valeurEntreParenthese[i][0] != null)
					{
						if(chaineTemp.contains(valeurEntreParenthese[i][0]))
						{
							chaineTemp = calculateur(chaineTemp.replaceAll(regexFriendly(valeurEntreParenthese[i][0]), valeurEntreParenthese[i][1]));
						}
					}
				}
			}
		}

		return chaineTemp;
	}

	/**
	 * Convertir double en int
	 * @param expression
	 * @return int sinon null
	 */
	public String convertirDoubleInt(String expression)
	{
		//Converti les double en int si c'est possible
		if( Double.parseDouble(expression) % 1 == 0)
		{
			int temp = (int) Double.parseDouble(expression) ;
			return String.valueOf(temp);	
		}
		else
			return null;
	}

	/**
	 * Additione deux paramètres
	 * @param a
	 * @param b
	 * @return Résultat de l'addition
	 */
	public String addition(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble + bDouble);
	}

	/**
	 * Soustrait deux paramètres
	 * @param a
	 * @param b
	 * @return Résultat de la soustraction
	 */
	public String soustraction(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble - bDouble);
	}

	/**
	 * Multiplie deux paramètres
	 * @param a
	 * @param b
	 * @return Résultat de la multiplication
	 */
	public String multiplication(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble * bDouble);
	}

	/**
	 * Divise le premier paramètre par le deuxième
	 * @param a
	 * @param b
	 * @return Résultat de la division
	 */
	public String division(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble / bDouble);
	}

	/**
	 * Donne le reste de la division des deux paramètres
	 * @param a
	 * @param b
	 * @return Résultat du reste
	 */
	public String modulo(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble % bDouble);
	}

	/**
	 * Fait la puissance (paramètre 2) sur le paramètre 1
	 * @param a
	 * @param b
	 * @return Résultat de la puissance
	 */
	public String puissance(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		if(detecterType(b) == "expression")
		{
			bDouble = Double.parseDouble(calculateur(b));
		}
		else
			bDouble = Double.parseDouble(b);

		return String.valueOf(Math.pow(aDouble,bDouble));
	}
	
	/**
	 * Fait la racine carré de paramètre 1
	 * @param a
	 * @param b
	 * @return Résultat de la racine carré
	 */
	public String racineCarre(String a)
	{
		Double aDouble = Double.parseDouble(a);

		if(detecterType(a) == "expression")
		{
			aDouble = Double.parseDouble(calculateur(a));
		}
		else
			aDouble = Double.parseDouble(a);

		return String.valueOf(Math.sqrt(aDouble));
	}

	/**
	 * Met en chaîne un entier
	 * @param a
	 * @return l'entier
	 */
	public String enChaine(int a){

		return String.valueOf(a) ;
	}

	/**
	 * Met en entier une chaîne
	 * @param a
	 * @return la string
	 */
	public String enEntier(String a){

		int    aInt ;
		double aDouble = 0 ;

		if(detecterType(a) == "entier"){ return a ;}
		else if(detecterType(a) == "réel"){
			aDouble = Double.parseDouble(a);
			aInt = (int)aDouble ;

			return String.valueOf(aInt) ;
		}
		else{ return null; }
	}

	// Si la chaîne mise en caractère correspond à un entier, retourne l'entier sous format réel'
	// Si la chaîne mise en caractère correspond à un réel  , retourne le reel
	public String enReel(String a){

		double aDouble = 0 ;

		if(detecterType(a) == "réel"){ return a ;}
		else if(detecterType(a) == "entier"){
			aDouble = Double.parseDouble(a);
			return String.valueOf(aDouble);
		}
		else{ return null;}
	}

	// Retourne le caractère correspondant au code ASCII de l'entier mis en paramètre
	public String car(int a){return Character.toString(a) ;}

	// Retourne le code ASCII du caractère mis en paramètre
	public String ord(char a){return String.valueOf((int) a) ;}

	// Arrondi le réel mis en paramètre à l'entier inférieur le plus proche
	public String plancher(double a){return String.valueOf(Math.floor(a));}

	// Arrondi le réel mis en paramètre à l'entier supérieur le plus proche
	public String plafond(double a){return String.valueOf(Math.ceil(a));}

	// Arrondi le reel mis en paramètre à l'entier le plus proche
	public String arrondi(double a){return String.valueOf(Math.round(a));}

	// Récupère la date courante au format AAAA-MM-JJ
	public String aujourdhui(){return String.valueOf(java.time.LocalDate.now());}

	// Récupère le jour de la date courante
	public String jour(String date){
		if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
			String[] aujourdhui = date.split("-");
			return aujourdhui[2] ;
		}
		else {return null;}
	}

	// Récupère le mois de la date courante
	public String mois(String date){
		if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
			String[] aujourdhui = date.split("-");
			return aujourdhui[1] ;
		}
		else {return null;}
	}

	// Récupère l'année de la date courante
	public String annee(String date){
		if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
			String[] aujourdhui = date.split("-");
			return aujourdhui[0] ;
		}
		else {return null;}
	}

	// Renvoie true si la chaine mise en paramètre correspond à un réel
	public Boolean estReel(String chaine){
		if(chaine.matches("^[0-9]+$") && chaine.contains(".")){return true;}
		else{return false;}
	}

	// Renvoie true si la chaine mise en paramètre correspond à un entier
	public Boolean estEntier(String chaine){
		if(chaine.matches("^[0-9]+$") && !(chaine.contains("."))){return true;}
		else{return false;}
	}

	// Génère un aléatoire allant de 0 (compris) à maximum (non compris)
	public static String hasard(int maximum){return String.valueOf((int)(Math.random()*maximum));}


	/**
	 * Gestion des parenthèses, remplace le String "valeurEntreParenthese" + num par la valeur
	 * @param expression
	 * @param valeurEntreParenthese
	 * @return les deux parties de l'expression
	 */
	public String[] remplacerValeur(String[] expression, String[][] valeurEntreParenthese)
	{
		for(int i = 0; i<2; i++)
		{
			if(valeurEntreParenthese[i][0] != null)
			{
				if(expression[0].contains(valeurEntreParenthese[i][0]))
				{
					expression[0] = expression[0].replaceAll(regexFriendly(valeurEntreParenthese[i][0]), valeurEntreParenthese[i][1]);

					System.out.println("Valeur" + valeurEntreParenthese[i][0]);
					System.out.println("Valeur" + valeurEntreParenthese[i][1]);
				}
				else if(expression[1].contains(valeurEntreParenthese[i][0]))
				{
					expression[1] = expression[1].replaceAll(regexFriendly(valeurEntreParenthese[i][0]), valeurEntreParenthese[i][1]);
				}
			}
		}
		return expression;
	}

	/**
	 * Vérifier une condition prise en paramètre
	 * @param chaine
	 * @return boolean
	 */
	public boolean verifierCondition(String chaine)
	{
		String chaineTemp = remplacerNomParValeur(chaine);
		boolean Valeur = false;

		String[][] valeurEntreParenthese= new String[2][2]; 

		int[] indexOperateur = new int[14];

		/*//Priorité 0
		indexOperateur[0] = chaineTemp.lastIndexOf("(");

		for(int i = 0; i<2; i++)
		{
			if(indexOperateur[0] != - 1|| chaineTemp.contains(")"))
			{
				String ecrireTemp;
				ecrireTemp = verifierCaractere('(', chaineTemp.substring(indexOperateur[0]));
				if(ecrireTemp != null) {
					valeurEntreParenthese[i][0] = "ValeurEntreParenthese" + i;
					valeurEntreParenthese[i][1]= ecrireTemp;
					chaineTemp = chaineTemp.replaceAll(regexFriendly(ecrireTemp), valeurEntreParenthese[i][0]);
				}
			}
		}*/

		//Priorité 1
		indexOperateur[1] = chaineTemp.lastIndexOf("<");
		indexOperateur[2] = chaineTemp.lastIndexOf(">");
		indexOperateur[3] = chaineTemp.lastIndexOf("<=");
		indexOperateur[4] = chaineTemp.lastIndexOf(">=");
		indexOperateur[5] = chaineTemp.lastIndexOf("/=");
		indexOperateur[6] = chaineTemp.lastIndexOf("=");

		int OperationLaMoinsPrioritaire = 0;

		//Gestion des conflits entre opérateurs
		if(indexOperateur[1] == indexOperateur[3]){ indexOperateur[1] = 0;}
		if(indexOperateur[2] == indexOperateur[4]){ indexOperateur[2] = 0;}
		if(indexOperateur[6] == indexOperateur[3] + 1 || indexOperateur[6] == indexOperateur[4] + 1 || indexOperateur[6] == indexOperateur[5] + 1){ indexOperateur[6] = 0;}

		//Détermination de l'opérateur le plus à droite
		int ValeurMax = 0;
		for(int i = 1; i < 7; i++)
		{
			if(indexOperateur[i] > ValeurMax)
			{
				ValeurMax = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		//Exécution récursive de l'opération la moins prioritaire
		String[] expression;
		switch(OperationLaMoinsPrioritaire)
		{
			case 1:
				expression = chaineTemp.split("\\<", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = inferieur(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 2:
				expression = chaineTemp.split("\\>", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = superieur(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 3:
				expression = chaineTemp.split("\\<\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = inferieurEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 4:
				expression = chaineTemp.split("\\>\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = superieurEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 5:
				expression = chaineTemp.split("\\/\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = pasEgal(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 6:
				expression = chaineTemp.split("\\=", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = egalite(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
		}
		OperationLaMoinsPrioritaire = 0;

		//Priorité 2
		indexOperateur[7] = chaineTemp.lastIndexOf("OU");
		indexOperateur[8] = chaineTemp.lastIndexOf("+");
		indexOperateur[9] = chaineTemp.lastIndexOf("-");

		//Détermination de l'opérateur le plus à droite
		ValeurMax = 0;
		for(int i = 7; i < 10; i++)
		{
			if(indexOperateur[i] > ValeurMax)
			{
				ValeurMax = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		switch(OperationLaMoinsPrioritaire)
		{
			case 7:
				expression = chaineTemp.split("OU", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = ouCondition(verifierCondition(calculateur(expression[0])), verifierCondition(calculateur(expression[1])));
				valeurEntreParenthese = null;
				break;
			/*case 8:
				expression = chaineTemp.split("\\+", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = addition(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 9:
				expression = chaineTemp.split("\\-", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = soustraction(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;*/
		}

		//Priorité 3
		indexOperateur[10] = chaineTemp.lastIndexOf("ET");
		indexOperateur[11] = chaineTemp.lastIndexOf("×");
		indexOperateur[12] = chaineTemp.lastIndexOf("/");

		//Détermination de l'opérateur le plus à droite
		
		ValeurMax = 0;
		for(int i = 10; i < 13; i++)
		{
			if(indexOperateur[i] > ValeurMax)
			{
				ValeurMax = indexOperateur[i];
				OperationLaMoinsPrioritaire = i;
			}
		}

		switch(OperationLaMoinsPrioritaire)
		{
			case 10:
				expression = chaineTemp.split("ET", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				Valeur = etCondition(verifierCondition(calculateur(expression[0])), verifierCondition(calculateur(expression[1])));
				valeurEntreParenthese = null;
				break;
			/*case 11:
				expression = chaineTemp.split("\\×", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = multiplication(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;
			case 12:
				expression = chaineTemp.split("\\/", 2);
				if(valeurEntreParenthese != null)
				{
					expression = remplacerValeur(expression, valeurEntreParenthese);
				}
				chaineTemp = division(calculateur(expression[0]), calculateur(expression[1]));
				valeurEntreParenthese = null;
				break;*/
		}
		
		//Priorité 4
		indexOperateur[13] = chaineTemp.lastIndexOf("NON");

		if(indexOperateur[13] != -1)
		{
			expression = chaineTemp.split("NON", 2);
			if(valeurEntreParenthese != null)
			{
				expression = remplacerValeur(expression, valeurEntreParenthese);
			}
			Valeur = nonCondition(verifierCondition(calculateur(expression[1])));
			valeurEntreParenthese = null;
		}
		return Valeur;
	}

	/* ----------------------------------------------- */
	/* METHODES BOOLÉENNES                             */
	/* ----------------------------------------------- */

	public boolean egalite(String a, String b)
	{
		return Double.parseDouble(a) == Double.parseDouble(b);
	}

	public boolean superieur(String a, String b)
	{
		return Double.parseDouble(a) > Double.parseDouble(b);
	}

	public boolean inferieur(String a, String b)
	{
		return Double.parseDouble(a) < Double.parseDouble(b);
	}

	public boolean superieurEgal(String a, String b)
	{
		return Double.parseDouble(a) >= Double.parseDouble(b);
	}

	public boolean inferieurEgal(String a, String b)
	{
		return Double.parseDouble(a) <= Double.parseDouble(b);
	}

	public boolean pasEgal(String a, String b)
	{
		return Double.parseDouble(a) != Double.parseDouble(b);
	}

	public boolean etCondition(boolean a, boolean b)
	{
		return a && b;
	}

	public boolean ouCondition(boolean a, boolean b)
	{
		return a || b;
	}

	public boolean xouCondition(boolean a, boolean b)
	{
		return a ^ b;
	}

	public boolean nonCondition(boolean a)
	{
		return !a;
	}

	/**
	 * Constructeur de Interpreteur, prend en paramètre l'Arraylist contenant le pseudo code.
	 * @param pseudoCode
	 */
	public Interpreteur (ArrayList<String> pseudoCode)
	{
		// Initialisation des ArrayLists pour créer une grosse taille de vide afin de pouvoir le replace

		console.clear();
		traceur.clear();

		int limiteLine = Main.getInstance().getLimiteLine();
		// int limiteLine = preLimiteLine + 1;
		// System.out.println("INTERPRETEUR : " + limiteLine ); // log débug
		// System.out.println("LIGNE : " + numLigne); // log débug

		//Vérifie le Algorithme Nom de Classe
		String[] declareClass = pseudoCode.get(0).split(" ");
		if(declareClass[0].equals("Algorithme")) {
			numLigne++;

			boolean finData = false;
			
			//Méthode pour terminer le coin DATA
			for(int i = 0; i < 200; i++)
			{
				if(numLigne > limiteLine) continue;
				String ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
				switch(ligneTemp)
				{
					case "Constante:" :
						numLigne++;
						while(true)
						{
							if(numLigne > limiteLine) break;
							String commentaire = pseudoCode.get(numLigne).strip();
							if(commentaire.startsWith("//")){
								numLigne++;
								continue;
							} 
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Variable :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break;

								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								//Méthode pour ajouter la constante
								declarerConstante(ligneTemp);
							}
							numLigne++;
						}
						break;
					case "Variable:" :
						numLigne++;
						while(true)
						{
							if(numLigne > limiteLine) break;
							String commentaire = pseudoCode.get(numLigne).strip();
							if(commentaire.startsWith("//")){
								numLigne++;
								continue;
							} 
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Constante :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break;

								//Méthode pour ajouter la variable
								declarerVariable(ligneTemp);
							}
							numLigne++;
						}
						break;
					case "DEBUT" : 
						finData = true;
						break;
					  default:
						  numLigne++;
						  break;		
				}			
			}
			if(finData == true)
			{
				while (numLigne < pseudoCode.size())
				{
					interpretation(pseudoCode);
					numLigne++;
					if(numLigne > limiteLine) break;
				}
			}
			else if (numLigne <= limiteLine)
				console.add("Erreur : Le programme ne démarre pas");
		}
		else if (numLigne <= limiteLine)
			console.add("Erreur : La classe n'est pas déclaré");
	}

	/**
	 * Permet de détécter les fonctions sur la ligne prise en paramètre
	 * @param pseudoCode
	 * @param numLigneTemp
	 * @return String de la ligne
	 */
	public String detecterFonction(ArrayList<String> pseudoCode, int numLigneTemp)
	{
		String[] ligneTemp = supprimerEspacesDebut(pseudoCode.get(numLigneTemp)).split(" ", 2);
		return ligneTemp[0];
	}
	
	/**
	 * Interprète le pseudo code
	 * @param pseudoCode
	 */
	public void interpretation(ArrayList<String> pseudoCode)
	{
		String condition[];

		String[] ligneTemp = supprimerEspacesDebut(pseudoCode.get(numLigne)).split(" ", 2);
		if(numLigneIgnorer == 0)
		{
			switch(ligneTemp[0]) {
				case "si":
					condition = ligneTemp[1].split("alors", 2);
					conditionsListe.add(numCondition,verifierCondition(condition[0]));
					if(conditionsListe.get(numCondition) == false)
					{
						int nbSi = 1;
						for(int i=numLigne+1; i < pseudoCode.size();i++)
						{
							if((!detecterFonction(pseudoCode, i).contains("si") &&!detecterFonction(pseudoCode, i).contains("fsi") && !detecterFonction(pseudoCode, i).contains("sinon")))
							{
								numLigneIgnorer++;
							}
							else if(detecterFonction(pseudoCode, i).contains("si") && !detecterFonction(pseudoCode, i).contains("fsi") && !detecterFonction(pseudoCode, i).contains("sinon"))
							{
								nbSi++;
								numLigneIgnorer++;
							}
							else if(detecterFonction(pseudoCode, i).contains("sinon"))
							{
								if(nbSi == 1)
								{
									break;
								}
								else
									numLigneIgnorer++;
							}
							else if(detecterFonction(pseudoCode, i).contains("fsi"))
							{
								if(nbSi == 1)
								{
									break;
								}
								else
								{
									nbSi--;
									numLigneIgnorer++;
								}
							}
						}
					}
					numCondition++;
					break;
				case "sinon":
					if(conditionsListe.get(numCondition-1) == true)
					{
						int nbSi = 1;
						for(int i=numLigne+1; i < pseudoCode.size();i++)
						{
							if(!detecterFonction(pseudoCode, i).contains("si") &&!detecterFonction(pseudoCode, i).contains("fsi") && !detecterFonction(pseudoCode, i).contains("sinon"))
							{
								numLigneIgnorer++;
							}
							else if(detecterFonction(pseudoCode, i).contains("si") && !detecterFonction(pseudoCode, i).contains("fsi") && !detecterFonction(pseudoCode, i).contains("sinon"))
							{
								nbSi++;
								numLigneIgnorer++;
							}
							else if(detecterFonction(pseudoCode, i).contains("fsi"))
							{
								if(nbSi == 1)
								{
									break;
								}
								else
								{
									nbSi--;
									numLigneIgnorer++;
								}
							}
						}
					}
					break;
				case "tq":
					condition = ligneTemp[1].split("faire", 2);
					// Vérifie si vrai ou faux dans l'arraylist
					conditionsListe.add(numCondition,verifierCondition(condition[0]));
					System.out.println("TQ " + conditionsListe.get(numCondition));
					if(conditionsListe.get(numCondition) == true)
					{
						for(int i=numLigne; !detecterFonction(pseudoCode, i).contains("ftq");i++)
						{
							numLigneBoucle++;
						}
						System.out.println(numLigneBoucle);
					}
					else
					{
						//Si faux
						for(int i=numLigne; (!detecterFonction(pseudoCode, i).contains("ftq"));i++)
						{
							numLigneIgnorer++;
						}
					}
					numCondition++;
					break;
				case "ftq":
					numLigne = numLigne - numLigneBoucle;
					// int limiteLine = Main.getInstance().getLimiteLine();
					// Main.getInstance().setLimiteLine(numLigne);
					
					numLigneBoucle = 0;
					break;
				case "lire":
					String lireTemp = verifierCaractere('(', ligneTemp[1]);
					if(hmLire.get(lireTemp) == null){
						Console reader = System.console();
						System.out.println("Veuillez entrer la valeur de la variable " + lireTemp + " :");
						String ligne = reader.readLine();
						hmLire.put(lireTemp, ligne);
						
						int indexVariable = chercherVariable(lireTemp.replaceAll(" ", ""));

						if(indexVariable > -1)
						{
							if(detecterType(ligne).equals(variables.get(indexVariable).getType()))
							{
								variables.get(indexVariable).affecterVariable(ligne);
								traceur.put(variables.get(indexVariable).getNom(), variables.get(indexVariable).getValue());
							}
						}

					}
					else {
						int indexVariable = chercherVariable(lireTemp.replaceAll(" ", ""));

						if(indexVariable > -1)
						{
							if(detecterType(hmLire.get(lireTemp)).equals(variables.get(indexVariable).getType()))
							{
								variables.get(indexVariable).affecterVariable(hmLire.get(lireTemp));
								traceur.put(variables.get(indexVariable).getNom(), variables.get(indexVariable).getValue());
							}
						}
						break;
					}
				case "ecrire":
					String ecrireTemp;
					ecrireTemp = verifierCaractere('(', ligneTemp[1]);
					if(ecrireTemp != null) {

						String[] chaineConcatenation = ecrireTemp.split("\\©");
						String chainePrint = "";

						for ( int i = 0 ; i < chaineConcatenation.length ; i++)
						{
							String chaineTemp[] = new String[2];
							chaineTemp[0] = verifierCaractere('"', chaineConcatenation[i]);
							chaineTemp[1] = verifierCaractere('\'', chaineConcatenation[i]);
							
							if(chaineTemp[0] != null) 
							{ 
								chainePrint = chainePrint + chaineTemp[0];
							}
							else if (chaineTemp[1] != null)
							{
								chainePrint = chainePrint + chaineTemp[1];
							}
							else
								chainePrint = chainePrint + calculateur(chaineConcatenation[i]);
						}
						console.add(chainePrint);						
					}
					else { console.add("Erreur absence de parenthèses"); }
					break;
			}
			//Divise en fontion de la flèche d'instanciation
			if(pseudoCode.get(numLigne).contains("<--"))
			{
				String[] tempString = pseudoCode.get(numLigne).split("<--");

				if(tempString[1] != null)
				{
					String[] tempStringTab = verifierCaractereTab(tempString[0]);
					if(tempString[0].contains("[") && tempString[0].contains("]"))
					{
						int indexCar = 0;
						for(int i=0; i< tempString[0].length();i++)
						{
							if(tempString[0].charAt(i) == '['){ indexCar = i; break;}
						}
						tempString[0] = tempString[0].substring(0,indexCar);
					}

					int indexVariable = chercherVariable(tempString[0].replaceAll(" ", ""));

					if(indexVariable > -1)
					{
						switch(variables.get(indexVariable).getDimensions())
						{
							case 0:
									if(detecterType(tempString[1]).equals(variables.get(indexVariable).getType()))
									{
										variables.get(indexVariable).affecterVariable(tempString[1]);
										traceur.put(variables.get(indexVariable).getNom(), variables.get(indexVariable).getValue());
									}
									else if(detecterType(tempString[1]).equals("expression"))
									{
										Double valeurTemp = Double.parseDouble(calculateur(tempString[1]));
				
										if(variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) != null)
										{
											variables.get(indexVariable).affecterVariable(String.valueOf(convertirDoubleInt(String.valueOf(valeurTemp))));
											traceur.put(variables.get(indexVariable).getNom(), variables.get(indexVariable).getValue());
										}
										else if (variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) == null)
										{
											console.add("Impossible d'affecter un réel dans un entier");
										}
										else{
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp));
											traceur.put(variables.get(indexVariable).getNom(), variables.get(indexVariable).getValue());
										}

									}
									break;
								case 1:
									if(detecterType(tempString[1]).equals(variables.get(indexVariable).getType()))
									{
										variables.get(indexVariable).affecterVariable(tempString[1], Integer.parseInt(tempStringTab[0]));
									}
									else if(detecterType(tempString[1]).equals("expression"))
									{
										Double valeurTemp = Double.parseDouble(calculateur(tempString[1]));
				
										if(variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) != null)
										{
											variables.get(indexVariable).affecterVariable(String.valueOf(convertirDoubleInt(String.valueOf(valeurTemp))), Integer.parseInt(tempStringTab[0]));
										}
										else if (variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) == null)
										{
											console.add("Impossible d'affecter un réel dans un entier");
										}
										else
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp), Integer.parseInt(tempStringTab[0]));
									}
									break;
								case 2:
									if(detecterType(tempString[1]).equals(variables.get(indexVariable).getType()))
									{
										variables.get(indexVariable).affecterVariable(tempString[1], Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]));
									}
									else if(detecterType(tempString[1]).equals("expression"))
									{
										Double valeurTemp = Double.parseDouble(calculateur(tempString[1]));
				
										if(variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) != null)
										{
											variables.get(indexVariable).affecterVariable(String.valueOf(convertirDoubleInt(String.valueOf(valeurTemp))), Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]));
										}
										else if (variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) == null)
										{
											console.add("Impossible d'affecter un réel dans un entier");
										}
										else
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp), Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]));
									}
									break;
								case 3:
									if(detecterType(tempString[1]).equals(variables.get(indexVariable).getType()))
									{
										variables.get(indexVariable).affecterVariable(tempString[1], Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[2]));
									}
									else if(detecterType(tempString[1]).equals("expression"))
									{
										Double valeurTemp = Double.parseDouble(calculateur(tempString[1]));
				
										if(variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) != null)
										{
											variables.get(indexVariable).affecterVariable(String.valueOf(convertirDoubleInt(String.valueOf(valeurTemp))), Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]), Integer.parseInt(tempStringTab[2]));
										}
										else if (variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) == null)
										{
											console.add("Impossible d'affecter un réel dans un entier");
										}
										else
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp), Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[2]));
									}
									break;
						}
								
					}
					else
						console.add("La variable n'a pas été instancié");
				}
			}
		}
		else
			numLigneIgnorer--;
	}

}
