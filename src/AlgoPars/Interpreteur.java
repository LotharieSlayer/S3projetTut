package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.xpath.XPath;


public class Interpreteur {
	
	static ArrayList<String> console = new ArrayList<String>();
	static ArrayList<String> trace = new ArrayList<String>();

	static ArrayList<Constante> constantes = new ArrayList<Constante>();
	static ArrayList<Variable> variables = new ArrayList<Variable>();
	
	int numLigne = 0;
	int numLigneIgnorer = 0;
	
	public ArrayList<String> getConsole() {
		return console;	
	}
		
	public ArrayList<String> getTrace() {
		return trace;	
	}
	
	public int getNumLigne() {
		return numLigne;
	}
	
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
				}
				else
					constantes.add(new Constante(tempString[0], Double.parseDouble(tempString[1])));
			}
			else if (tempString[1].equals("vrai"))
			{
				constantes.add(new Constante(tempString[0], true));
				trace.add(numLigne, tempString[0] + " ");
			}
			else if (tempString[1].equals("faux"))
			{
				constantes.add(new Constante(tempString[0], false));
			}
			else
			{
				if(tempString[1].charAt(0) == '\'')
				{
					if(tempString[1].charAt(tempString[1].length() - 1) == '\'')
						constantes.add(new Constante(tempString[0], tempString[1]));
					else { console.add(numLigne, "Erreur de déclaration de constante"); }
				}

				else if(tempString[1].charAt(0) == '"')
				{
					if(tempString[1].charAt(tempString[1].length() - 1) == '\"')
						constantes.add(new Constante(tempString[0], tempString[1]));
					else { console.add(numLigne, "Erreur de déclaration de constante"); }
				}
				else
					console.add(numLigne, "Erreur de déclaration de constante");
			}
		}
		else
			console.add(numLigne, "Erreur de déclaration de constante");
	}
	
	public void declarerVariable(String variable) {
		String[] tempStringType = variable.split(":");
		String[] tempStringVariables = tempStringType[0].replaceAll(" ", "").split(",");

		for ( int i = 0 ; i < tempStringVariables.length ; i++)
		switch (tempStringType[1]) {
			case "entier":
				variables.add(new Variable(tempStringVariables[i], "entier"));
				break;

			case "double":
				variables.add(new Variable(tempStringVariables[i], "double"));
				break;

			case "chaine de caractères":
				variables.add(new Variable(tempStringVariables[i], "chaine de caractères"));
				break;
		
			case "char":
				variables.add(new Variable(tempStringVariables[i], "char"));
				break;

			case "booléen":
				variables.add(new Variable(tempStringVariables[i], "booléen"));
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
							variables.add(new Variable(tempStringVariables[i], "double", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "double", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "double", Integer.parseInt(tempStringTab[0])));
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
					if(tempString.contains("de chaine de caractère"))
					{
						if(detecterType(tempStringTab[2]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine de caractères", Integer.parseInt(tempStringTab[2]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[1]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine de caractères", Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[0])));
						}
						else if(detecterType(tempStringTab[0]).contains("entier"))
						{
							variables.add(new Variable(tempStringVariables[i], "chaine de caractères", Integer.parseInt(tempStringTab[0])));
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
					console.add(numLigne, "Type de base non reconnu");
				break;
		}
	}
	
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
					chaineTemp = chaineTemp.substring(indexCar[1] + 1, chaineTemp.length());
				}
			}
		}
		return tabTemp;
	}
	
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

	public String detecterType(String chaine)
	{
		String chaineTemp[] = new String[2];

		chaineTemp[0] = verifierCaractere('"', chaine);
		chaineTemp[1] = verifierCaractere('\'', chaine);

		chaine = chaine.replaceAll(" ", "");

		if(chaineTemp[0] != null)
		{
			return "chaine de caractères";
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

	public String calculateur(String chaine)
	{
		String chaineTemp = remplacerNomParValeur(chaine);
		String[][] valeurEntreParenthese= new String[2][2]; 

		for(int i = 0; i<2; i++)
		{
			if(chaineTemp.contains("(")|| chaineTemp.contains(")"))
			{
				String ecrireTemp;
				ecrireTemp = verifierCaractere('(', chaineTemp);
				if(ecrireTemp != null) {
					valeurEntreParenthese[i][0] = "ValeurEntreParenthese" + i;
					valeurEntreParenthese[i][1]= ecrireTemp;
					ecrireTemp = "\\(" + ecrireTemp + "\\)";
					chaineTemp = chaineTemp.replaceAll(ecrireTemp, valeurEntreParenthese[i][0]);
				}
			}
		}
		if(chaineTemp.contains("PLUS"))
		{
			String[] expression = chaineTemp.split("PLUS", 2); 

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = addition(expression[0], expression[1]);
			valeurEntreParenthese = null;
		}
		if(chaineTemp.contains("-"))
		{
			String[] expression = chaineTemp.split("-", 2);

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = soustraction(expression[0], expression[1]);
		}
		if(chaineTemp.contains("x"))
		{
			String[] expression = chaineTemp.split("x", 2);

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = multiplication(expression[0], expression[1]);
			valeurEntreParenthese = null;

		}
		if(chaineTemp.contains("/"))
		{
			String[] expression = chaineTemp.split("/", 2);

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = division(expression[0], expression[1]);
		}
		if(chaineTemp.contains("%"))
		{
			String[] expression = chaineTemp.split("%", 2);

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = modulo(expression[0], expression[1]);
		}
		if(chaineTemp.contains("^"))
		{
			String[] expression = chaineTemp.split("^", 2);

			for(int i = 0; i<2; i++)
			{
				if(valeurEntreParenthese[i][0] != null)
				{
					if(expression[0].contains(valeurEntreParenthese[i][0]))
					{
						expression[0] = expression[0].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
					else if(expression[1].contains(valeurEntreParenthese[i][0]))
					{
						expression[1] = expression[1].replaceAll(valeurEntreParenthese[i][0], valeurEntreParenthese[i][1]);
					}
				}
			}

			chaineTemp = puissance(expression[0], expression[1]);
		}

		
		return chaineTemp;
	}

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
	
	public String racineCarre(String a, String b)
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

	public boolean verifierCondition(String chaine)
	{
		String chaineTemp = remplacerNomParValeur(chaine);
		boolean Valeur = false;

		if(chaineTemp.contains("="))
		{
			String[] expression = chaineTemp.split("=", 2); 
			Valeur = egalite(calculateur(expression[0]), calculateur(expression[1]));
		}
		if(chaineTemp.contains(">"))
		{
			String[] expression = chaineTemp.split(">", 2);
			Valeur = superieur(calculateur(expression[0]), calculateur(expression[1]));
		}
		if(chaineTemp.contains("<"))
		{
			String[] expression = chaineTemp.split("<", 2);
			Valeur = inferieur(calculateur(expression[0]), calculateur(expression[1]));
		}
		if(chaineTemp.contains("<="))
		{
			String[] expression = chaineTemp.split("<=", 2);
			Valeur = superieurEgal(calculateur(expression[0]), calculateur(expression[1]));
		}
		if(chaineTemp.contains("/="))
		{
			String[] expression = chaineTemp.split("/=", 2);
			Valeur = pasEgal(calculateur(expression[0]), calculateur(expression[1]));
		}

		return Valeur;
	}

	public boolean egalite(String a, String b)
	{
		if (Double.parseDouble(a) == Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public boolean superieur(String a, String b)
	{
		if (Double.parseDouble(a) == Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public boolean inferieur(String a, String b)
	{
		if (Double.parseDouble(a) < Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public boolean superieurEgal(String a, String b)
	{
		if (Double.parseDouble(a) >= Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public boolean inferieurEgal(String a, String b)
	{
		if (Double.parseDouble(a) <= Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public boolean pasEgal(String a, String b)
	{
		if (Double.parseDouble(a) != Double.parseDouble(b))
		{  
			return true;
		}
		else
			return false;
	}

	public Interpreteur (ArrayList<String> pseudoCode)
	{
		// Initialisation des ArrayLists pour créer une grosse taille de vide afin de pouvoir le replace
		// Méthode à changer car non optimisé !
		for(int i = 0; i < 3852; i++){
			console.add(i, "");
			trace.add(i, "");
		}
		//Vérifie le Algorithme Nom de Classe
		
		String[] declareClass = pseudoCode.get(0).split(" ");
		if(declareClass[0].equals("Algorithme")) {
			
			console.add(numLigne, "Classe ajouté");
			
			numLigne++;
			
			boolean finData = false;
			
			//Méthode de shlag pour terminer le coin DATA
			for(int i = 0; i < 20; i++)
			{
				String ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
				switch(ligneTemp)
				{
					case "Constante:" :
						numLigne++;
						while(true)
						{
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Variable :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break;

								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								//Méthode pour ajouter la constante
								declarerConstante(ligneTemp);
								console.add(numLigne, "Constante ajouté");
							}
							numLigne++;
						}
						break;
					case "Variable:" :
						numLigne++;
						while(true)
						{
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Constante :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break;

								//Méthode pour ajouter la variable
								declarerVariable(ligneTemp);
								console.add(numLigne, "Variable ajouté");
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
					Interpretation(pseudoCode);
					numLigne++;
				}
			}
			else
				console.add(numLigne, "Erreur : Le programme ne démarre pas");
		}
		else
			console.add(numLigne, "Erreur : La classe n'est pas déclaré");
	}

	public String detecterFonction(ArrayList<String> pseudoCode, int numLigneTemp)
	{
		String[] ligneTemp = pseudoCode.get(numLigneTemp).split(" ", 2);
		return ligneTemp[0];
	}
	
	public void Interpretation(ArrayList<String> pseudoCode)
	{
		String[] ligneTemp = supprimerEspacesDebut(pseudoCode.get(numLigne)).split(" ", 2);
		if(numLigneIgnorer == 0)
		{
			switch(ligneTemp[0]) {
				case "si":
					String condition[] = ligneTemp[1].split("alors", 2);
					if(verifierCondition(condition[0]) == false)
					{
						for(int i=numLigne; !detecterFonction(pseudoCode, i).contains("fsi");i++)
						{
							numLigneIgnorer++;
						}
					}
					break;
				case "sinon":
					
					break;
				/**case "tq":
					String condition2[] = ligneTemp[1].split("alors", 2);
					while(verifierCondition(condition2[0]) == false)
					{
						for(int i=numLigne; !detecterFonction(pseudoCode, i).contains("fsi");i++)
						{
							numLigneIgnorer++;
						}
					}
					break;**/
				case "lire" :
					/*Scanner reader = new Scanner(System.in);
					String temp = reader.next();*/
					break;
				case "ecrire":
					String ecrireTemp;
					ecrireTemp = verifierCaractere('(', ligneTemp[1]);
					if(ecrireTemp != null) {

						String[] chaineConcatenation = ecrireTemp.split("PLUS");
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
						console.add(numLigne, chainePrint);						
					}
					else { console.add(numLigne, "Erreur absence de parenthèses"); }
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
									}
									else if(detecterType(tempString[1]).equals("expression"))
									{
										Double valeurTemp = Double.parseDouble(calculateur(tempString[1]));
				
										if(variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) != null)
										{
											variables.get(indexVariable).affecterVariable(String.valueOf(convertirDoubleInt(String.valueOf(valeurTemp))));
										}
										else if (variables.get(indexVariable).getType() == "entier" && convertirDoubleInt(String.valueOf(valeurTemp)) == null)
										{
											console.add(numLigne, "Impossible d'affecter un réel dans un entier");
										}
										else
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp));
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
											console.add(numLigne, "Impossible d'affecter un réel dans un entier");
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
											console.add(numLigne, "Impossible d'affecter un réel dans un entier");
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
											console.add(numLigne, "Impossible d'affecter un réel dans un entier");
										}
										else
											variables.get(indexVariable).affecterVariable(String.valueOf(valeurTemp), Integer.parseInt(tempStringTab[0]), Integer.parseInt(tempStringTab[1]),Integer.parseInt(tempStringTab[2]));
									}
									break;
						}
								
					}
					else
						console.add(numLigne, "La variable n'a pas été instancié");
				}
			}
		}
		else
			numLigneIgnorer--;
	}

	public static ArrayList<Constante> getConstantes(){
		return constantes;
	}

	public static ArrayList<Variable> getVariables(){
		return variables;
	}
}
