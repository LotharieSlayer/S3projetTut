package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

public class Interpreteur {
	
	static ArrayList<String> console = new ArrayList<String>();
	static ArrayList<Constante> constantes = new ArrayList<Constante>();
	static ArrayList<Variable> variables = new ArrayList<Variable>();
	
	int numLigne = 0;
	
	public ArrayList<String> getConsole() {
		return console;	
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
					else { console.add("Erreur de déclaration de constante"); }
				}

				else if(tempString[1].charAt(0) == '"')
				{
					if(tempString[1].charAt(tempString[1].length() - 1) == '\"')
						constantes.add(new Constante(tempString[0], tempString[1]));
					else { console.add("Erreur de déclaration de constante"); }
				}
				else
					console.add("Erreur de déclaration de constante");
			}
		}
		else
			console.add("Erreur de déclaration de constante");
	}
	
	public void declarerVariable(String variable) {
		String[] tempStringType = variable.split(":");
		String[] tempStringVariables = tempStringType[0].replaceAll(" ", "").split(",");
		
		boucle1 :
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
		
			case "caractère":
				variables.add(new Variable(tempStringVariables[i], "caractère"));
				break;

			case "booléen":
				variables.add(new Variable(tempStringVariables[i], "booléen"));
				break;
		
			default:
				console.add("Type de base non reconnu");
				break boucle1;
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
        
        if(indexCar[0] < indexCar[1]) { return chaine.substring(indexCar[0] + 1,indexCar[1] - 1);}
		return null;
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

		if(chaineTemp[0] != null)
		{
			return "chaine de caractères";
		}
		else if (chaineTemp[1] != null)
		{
			return "caractère";
		}
		else if(chaine.charAt(0) >= '0' && chaine.charAt(0) <= '9')
		{
			String[] valeur = chaine.split(".", 2);
			if(valeur[1] == null)
			{
				return "entier";
			}
			else
				return "réel";
		}
		else if (chaine.replaceAll("" , "").equals("vrai") || chaine.equals("faux"))
		{
			return "booléen";
		}
		else
		{
			return "expression";
		}
	}

	public String calculateur(String chaine)
	{
		//Pour l'instant je m'occupe de remplacer les variables et constantes par leurs valeurs
		String chaineTemp = chaine.replaceAll(" ", "");

		int indexConstante = chercherConstante(chaine);
		int indexVariable = chercherVariable(chaine);

		for(int i = 0; i < constantes.size(); i++)
		{
			if(chaineTemp.contains(constantes.get(i).getNom()))
			{
				chaineTemp.replaceAll(constantes.get(i).getNom(), constantes.get(i).getValue());
			}
		}

		for(int i = 0; i < variables.size(); i++)
		{
			if(chaineTemp.contains(variables.get(i).getNom()))
			{
				chaineTemp.replaceAll(variables.get(i).getNom(), variables.get(i).getValue());
			}
		}

		if(chaineTemp.contains("+"))
		{
			String[] expression = chaineTemp.split("+", 2);
			chaineTemp = addition(expression[0], expression[1]);
		}
		if(chaineTemp.contains("×"))
		{
			String[] expression = chaineTemp.split("×", 2);
			chaineTemp = multiplication(expression[0], expression[1]);
		}

		return null;
	}

	public String addition(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble + bDouble);
	}

	public String soustraction(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble - bDouble);
	}

	public String multiplication(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble * bDouble);
	}

	public String division(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble / bDouble);
	}

	public String modulo(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(aDouble % bDouble);
	}

	public String puissance(String a, String b)
	{
		double aDouble = 0, bDouble = 0;

		aDouble = Double.parseDouble(a);
		bDouble = Double.parseDouble(b);

		return String.valueOf(Math.pow(aDouble,bDouble));
	}
	
	public String racineCarre(String a, String b)
	{
		Double aDouble = Double.parseDouble(a);

		return String.valueOf(Math.sqrt(aDouble));
	}

	public Interpreteur (ArrayList<String> pseudoCode)
	{
		//Vérifie le Algorithme Nom de Classe
		
		String[] declareClass = pseudoCode.get(0).split(" ");
		if(declareClass[0].equals("Algorithme")) {
			
			console.add("Classe ajouté");
			
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
						boucle1 :
						while(true)
						{
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Variable :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break boucle1;

								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								//Méthode pour ajouter la constante
								declarerConstante(ligneTemp);
								console.add("Constante ajouté");
							}
							numLigne++;
						}
						break;
					case "Variable:" :
						numLigne++;
						boucle1 :
						while(true)
						{
							if(!pseudoCode.get(numLigne).replaceAll(" ", "").equals("")){
								ligneTemp = pseudoCode.get(numLigne).replaceAll(" ", "");
								if(pseudoCode.get(numLigne).equals("Constante :") || pseudoCode.get(numLigne).equals("DEBUT"))
									break boucle1;

								//Méthode pour ajouter la variable
								declarerVariable(ligneTemp);
								console.add("Variable ajouté");
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
					String[] ligneTemp = pseudoCode.get(numLigne).split(" ", 2);
					switch(ligneTemp[0]) {
						case "lire" :
							/*Scanner reader = new Scanner(System.in);
							String temp = reader.next();*/
							break;
						case "ecrire":
							String chaineTemp[] = new String[3];
							chaineTemp[0] = verifierCaractere('(', ligneTemp[1]);
							if(chaineTemp[0] != null) {
								chaineTemp[1] = verifierCaractere('"', ligneTemp[1]);
								chaineTemp[2] = verifierCaractere('\'', ligneTemp[1]);
								int indexConstante = chercherConstante(chaineTemp[0]);
								int indexVariable = chercherVariable(chaineTemp[0]);
								
								if(chaineTemp[1] != null || chaineTemp[2] != null) 
								{ 
									console.add(chaineTemp[1]);
								}
								else if(indexConstante > -1)
								{
									console.add(constantes.get(indexVariable).getValue());
								}
								else if(indexVariable > -1)
								{
									console.add(variables.get(indexVariable).getValue());
								}
								else
									console.add("La valeur n'est pas instancié");
							}
							else { console.add("Erreur absence de parenthèses"); }
							break;
					}
					//Divise en fontion de la flèche d'instanciation
					String[] tempString = pseudoCode.get(numLigne).split("<--");
					if(tempString[1] != null)
					{
						int indexVariable = chercherVariable(tempString[0].replaceAll(" ", ""));

						if(indexVariable > -1)
						{
							if(detecterType(tempString[1]).equals(variables.get(indexVariable).getType()))
							{
								variables.get(indexVariable).affecterVariable(tempString[1]);
							}
							else if(detecterType(tempString[1]).equals("expression"))
							{
								variables.get(indexVariable).affecterVariable(calculateur(tempString[1]));
							}
						}
						else
							console.add("La variable n'a pas été instancié");
					}
					numLigne++;
				}
			}
			else
				console.add("Erreur : Le programme ne démarre pas");
		}
		else
			console.add("Erreur : La classe n'est pas déclaré");
	}
	

	public static ArrayList<Constante> getConstantes(){
		return constantes;
	}

	public static ArrayList<Variable> getVariables(){
		return variables;
	}
}
