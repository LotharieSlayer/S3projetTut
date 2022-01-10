package AlgoPars;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class IhmCui {

	static int futureLine;
	static ArrayList<String> code;

	/**
	 * @param pseudoCode	Le pseudo-code en entrée pour l'afficher
	 */
	public IhmCui(ArrayList<String> pseudoCode) {
		code = pseudoCode;
		sortie(code);
		
	}

	public void sortie(ArrayList<String> pseudoCode){
		// Première partie code deuxième partie données
		System.out.println(  "┌──────────┐" + String.format ( "%84s", "┌─────────┐"));
		System.out.println(  "│   CODE   │" + String.format ( "%84s", "│ DONNÉES │" ));
		System.out.println(  "├──────────┴─────────────────────────────────────────────────────────────────────────┼─────────┴──────────┬────────────────────────┐" );

		for(int i = 0; i < pseudoCode.size(); i++)
		{
			// Partie code

			String color = "";
			String colorReset = "";
			if(futureLine == i){
				color = "\033[44m";
				colorReset = "\033[40m";
			}

			// Si plus de 80 caractères
			if (pseudoCode.get(i).length() > 80)
				System.out.print( color + "│" + String.format ( "%-2d", i ) + " " +  String.format ( "%-76.75s", pseudoCode.get(i) ) + " ... │" + colorReset);

            else { System.out.print( color + "│" + String.format ( "%-2d", i ) + " " +  String.format ( "%-80s", pseudoCode.get(i) ) + " " + "│" + colorReset); }


			// Si plus de 40 lignes
            if ( i >= 40 ){
				System.out.println(String.format("%46s", "│") + "\n" + String.format ( "%-40s", "│") + String.format ("%-45s", "...") + "│" + String.format("%46s", "│"));
				break;
			}



			// Partie données

			ArrayList<String> trace = Main.getInstance().getTrace();

			// Première ligne
			if ( i == 0 )	System.out.println( String.format( "%20s", "NOM         ") + String.format("%-10s", "│") + String.format("%7s", "VALEUR") + "        │" );
			if ( i != 0)	System.out.println( String.format("%-20s", " " +  trace.get(i) ) + "│" +  String.format("%25s", trace.get(i) + " │" ) );
		}
		
		System.out.println( "└────────────────────────────────────────────────────────────────────────────────────┴─────────────────────────────────────────────┘");
		
		System.out.println("\n");
		
		System.out.println("┌─────────────┐");
		System.out.println("│   CONSOLE   │");
		System.out.println("├─────────────┴──────────────────────────────────────────────────────────────────────┐");
		ArrayList<String> console = Main.getInstance().getConsole();
		for(int i = 0; i < futureLine; i++)
		{
			if(console.size() < futureLine) break;
			if(console.get(i).equals("")) continue;
			System.out.println("│" + String.format ( "%-83s", console.get(i) ) + " " + "│");
		}
		System.out.println("└────────────────────────────────────────────────────────────────────────────────────┘");
		
		getUserInput();

	}

	public void getUserInput() {

		System.out.println("Entrée      (avancer ligne par ligne)");
		System.out.println("B + Entrée  (reculer ligne par ligne)");
		System.out.println("LX + Entrée (aller à la ligne X)");

		Console console = System.console();
		String ligne = console.readLine();

		switch(ligne){
			case "" :
				System.out.println("Entrée");
				futureLine += 1;
				sortie(code);
				break;
			case "B" :
				System.out.println("B + Entrée");
				futureLine -= 1;
				sortie(code);
				break;
			// case "L" +  : en fait la faut vérifier l'int qui est après et genre c'est chiant et j'ai la flemme la
			// System.out.println("B + Entrée");
			// futureLine = l'int de mort du coup;
			// sortie(code);
			// break;
			case "Q" :
				System.out.println("Q + Entrée");
				// finDuProgramme = true;
				break;
			default:
				getUserInput();
		}
	}

	public int getFutureLine() {
		return futureLine;
	}
}
