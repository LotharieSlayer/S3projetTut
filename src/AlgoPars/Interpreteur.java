package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		String[] tempString = constante.split("-->");
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
		
			// for ( Constante constant : constantes )
			// console.add(constant.getNom() + " : " + constant.getType());
			// System.out.println(constantes.get(1));
	}
	
	public Constante chercher (String nom)
	{
		for(int i=0; i < constantes.size();i++)
		{
			if(nom.equals(constantes.get(i).getNom()))
			{
				return constantes.get(i);
			};
		}
		console.add("La constante spécifié n'existe pas");
		return null;
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

			case "chainedecaractères":
				variables.add(new Variable(tempStringVariables[i], "chaine de caractères"));
				break;
		
			case "char":
				variables.add(new Variable(tempStringVariables[i], "char"));
				break;

			case "booléen":
				variables.add(new Variable(tempStringVariables[i], "booléen"));
				break;
		
			default:
				console.add("Type de base non reconnu");
				break boucle1;
		}
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
					String[] ligneTemp = pseudoCode.get(numLigne).split(" ");
					switch(ligneTemp[0]) {
						case "lire" :
							/*Scanner reader = new Scanner(System.in);
							String temp = reader.next();*/
							break;
						case "écrire":
							//A changer car ça prend uniquement quand y a des espaces
							if(ligneTemp[1].charAt(0) == '(' && ligneTemp[3].charAt(0) == ')')
							{
								if(ligneTemp[2].charAt(0) == '"')
								{
									console.add(ligneTemp[2].replaceAll("\"", null));
								}
								else
								{
									if(ligneTemp[2].charAt(0) <= 'a' && ligneTemp[2].charAt(0) <= 'z')
									{
										//cherche dans l'arraylist de variables si un nom correspond
										for(int i = 0; i < constantes.size(); i++)
										{
											if(constantes.get(i).getNom() == ligneTemp[2])
											{
												// console.add(constantes.get(i).);	
											}
										}
										
									}
									else if (ligneTemp[2].charAt(0) <= 'A' && ligneTemp[2].charAt(0) <= 'Z')
									{
										//Cherche dans l'arraylist de constante
										// console.add(chercher(ligneTemp[1]));
									}
								}
							}
							break;
					}
					numLigne++;
				}
			}
			else
				console.add("Erreur : Le programme ne démarre pas");
		}
		else
			console.add("Erreur : La classe n'est pas déclaré");

		/*
		for ( Constante constante : constantes )
		console.add(constante.getNom() + " : " + constante.getType());
		*/
		for ( Variable variable : variables )
		console.add(variable.nom + " : " + variable.type);
	}
}
