package AlgoPars.ihm;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import AlgoPars.Main;

import java.util.HashMap;

public class IhmCui {

	static int futureLine;
	static ArrayList<String> code;
	static ArrayList<String> console = Main.getInstance().getConsole();

	static ArrayList<String> variablesATracer;
	static ArrayList<String> sTraceHashMap = new ArrayList<String>();
	static HashMap<String, String> varTrace = Main.getInstance().getTraceur();



	/**
	 * @param pseudoCode	Le pseudo-code en entrée pour l'afficher
	 */
	public IhmCui(ArrayList<String> pseudoCode, ArrayList<String> variables) {
		code = pseudoCode;
		variablesATracer = variables;


		sortie(code, variablesATracer);
		
	}

	public void sortie(ArrayList<String> pseudoCode, ArrayList<String> variables){

		// RELOAD
		Main.getInstance().setLimiteLine(futureLine);

		if(futureLine != 0)
			Main.getInstance().reloadInterpreteur();


		// TRACEUR
		sTraceHashMap.clear();
		// Toutes les variables récupéré dans le traceur
		for(String var : variablesATracer){
			// Boucle pour récupérer les variables à tracer et afficher celles qui sont spécifié dans le fichier ***.var.
			for (String j : varTrace.keySet()){
				if(j.equals(var)) {
					sTraceHashMap.add(String.format("%-20s", " " +  j) + "│" +  String.format("%23s", varTrace.get(j)) + " │" );
				}
			}	
		}
		
		// Première partie code deuxième partie données
		System.out.println(  "┌──────────┐" + String.format ( "%84s", "┌─────────┐"));
		System.out.println(  "│   CODE   │" + String.format ( "%84s", "│ DONNÉES │" ));
		System.out.println(  "├──────────┴─────────────────────────────────────────────────────────────────────────┼─────────┴──────────┬────────────────────────┐" );

		int limiteAffichage = 0;
		int cptLigne;
		if (futureLine > 20)
			cptLigne = futureLine - 20;
		else
			cptLigne = 0;

		for(int i = cptLigne; i < pseudoCode.size(); i++)
		{

			// Partie code

			String color = "";
			String colorReset = "";
			if(futureLine == i){
				color = "\033[44m";
				colorReset = "\033[40m";
				// System.out.println("IHM : " + Main.getInstance().getLimiteLine()); // log débug
				
			}

			// Si plus de 80 caractères
			if (pseudoCode.get(i).length() > 80)
				System.out.print( color + "│" + String.format ( "%-2d", i ) + " " + colorReset + String.format ( "%-80s", pseudoCode.get(i) ) + "     │");

			else { System.out.print( color + "│" + String.format ( "%-2d", i ) + " " + colorReset + String.format ( "%-80s", pseudoCode.get(i) ) + " " + "│"); }


			// Si plus de 40 lignes
            if ( limiteAffichage >= 40 ){
				System.out.println(String.format("%46s", "│") + "\n" + String.format ( "%-40s", "│") + String.format ("%-45s", "   ") + "│" + String.format("%46s", "│"));
				break;
			}



			// Partie données

			// Première ligne
			if ( i == 0 )	System.out.println( String.format( "%20s", "NOM         ") + String.format("%-10s", "│") + String.format("%7s", "VALEUR") + "        │" );
			
			
			if ( i != 0 ){
				if(i > sTraceHashMap.size()){
					System.out.println( String.format("%-20s", "" ) + "│" +  String.format("%25s", "" + " │" ) );
				}
				else {
					System.out.println(sTraceHashMap.get(i - 1));
				}

			}

			limiteAffichage++;
		}
		
		System.out.println( "└────────────────────────────────────────────────────────────────────────────────────┴─────────────────────────────────────────────┘");
		
		System.out.println("\n");
		
		System.out.println("┌─────────────┐");
		System.out.println("│   CONSOLE   │");
		System.out.println("├─────────────┴──────────────────────────────────────────────────────────────────────┐");
		for(int i = 0; i < console.size(); i++)
		{
			// if(console.size() < futureLine) break;
			if(console.get(i).equals("")) continue;
			System.out.println("│" + String.format ( "%-83s", console.get(i) ) + " " + "│");
		}
		System.out.println("└────────────────────────────────────────────────────────────────────────────────────┘");
		
		getUserInput();

	}

	public void getUserInput() {

		System.out.println("Entrée		(avancer ligne par ligne)");
		System.out.println("B + Entrée	(reculer ligne par ligne)");
		System.out.println("LX + Entrée	(aller à la ligne X)");
		System.out.println("Q + Entrée	(quitter)");

		Console console = System.console();
		String ligne = console.readLine();

		if(ligne.startsWith("L")){
			// split le L et le chiffre
			String[] temp = ligne.split("L");
			futureLine = Integer.parseInt(temp[1]);
			sortie(code, variablesATracer);
		}

		switch(ligne){
			case "" :
				// System.out.println("Entrée");
				futureLine += 1;
				// Si on arrive à la taille de l'algorithme on fait -1 pour rester sur le "FIN".
				if(futureLine >= code.size()) futureLine = code.size() - 1;
				sortie(code, variablesATracer);
				break;
			case "B" :
				// System.out.println("B + Entrée");
				futureLine -= 1;
				if(futureLine == -1) futureLine = 0;
				sortie(code, variablesATracer);
				break;
			case "Q" :
				// System.out.println("Q + Entrée");
				break;
			default:
				getUserInput();
		}
	}
}
