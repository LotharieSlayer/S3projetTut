package AlgoPars;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class IhmCui {


	/**
	 * 
	 * @param pseudoCode	Le pseudo-code en entrée pour l'afficher
	 */
	public IhmCui(ArrayList<String> pseudoCode, int indexConsole) {

		// Première partie code deuxième partie données
		System.out.println(  "------------"
		                   + String.format ( "%84s", "-----------"));

		System.out.println(  "|   CODE   |"
		                   + String.format ( "%84s", "| DONNEES |" ));

		System.out.println(  "------------------------------------------------------------------------------------- "
		                   + "----------------------------------------------"                                         );

		boucle1:
		for(int i = 1; i < pseudoCode.size(); i++)
		{
			// Partie code

			// Si plus de 80 caractères
			if (pseudoCode.get(i).length() > 80)
				System.out.print(  "|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-76.75s", pseudoCode.get(i) ) + " ... |");

            else { System.out.print("|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-80s", pseudoCode.get(i) ) + " " + "|"); }

			// Si plus de 40 lignes
            if ( i >= 40 ){
				System.out.println(String.format("%46s", "|") + "\n" + String.format ( "%-40s", "|") + String.format ("%-45s", "...") + "|" + String.format("%46s", "|"));
				break boucle1;
			}



			// Partie données

			// Première ligne
			if ( i == 0 ) System.out.println( String.format( "%20s", "NOM         ") + String.format("%-10s", "|") + String.format("%7s", "VALEUR") + "        |" );
			// Les constantes
			if ( i - 1 < Interpreteur.getConstantes().size())
				System.out.println( String.format("%-20s", " " +  Interpreteur.getConstantes().get(i - 1).nom ) + "|" +  String.format("%25s", Interpreteur.getConstantes().get(i - 1).getValue() + " |" ) );
			else{
				if ( i - 1 == Interpreteur.getConstantes().size() )
					// Espace entre les constantes et les variables
					System.out.println( String.format("%21s", "|") + String.format("%25s", "|"));
				else{
					// Les variables
					if ( i  < ( Interpreteur.getVariables().size() + Interpreteur.getConstantes().size()) )
						System.out.println( String.format("%-20s", " " +  Interpreteur.getVariables().get(i - Interpreteur.getConstantes().size()).nom ) + "|" +  String.format("%25s", Interpreteur.getVariables().get(i - Interpreteur.getConstantes().size()).getValue() + " |" ) );
					// Lignes vierges
					else{
						System.out.println( String.format("%46s", "|"));
					}
				}
			}
		}
		
		System.out.println( "--------------------------------------------------------------------------------------"
		                  + "----------------------------------------------"                                        );
		
		System.out.println("\n");
		
		System.out.println("--------------");
		System.out.println("|   CONSOLE   |");
		System.out.println("--------------------------------------------------------------------------------------");
		ArrayList<String> console = Main.getInstance().getConsole();
		for(int i = 0; i < console.size(); i++)
		{
			System.out.println("|" + String.format ( "%-83s", console.get(i) ) + " " + "|");
		}
		System.out.println("--------------------------------------------------------------------------------------");
		
		//getUserInput();

		
	}

	public void getUserInput() {

		System.out.println("Entrée      (avancer ligne par ligne)");
		System.out.println("B + Entrée  (reculer ligne par ligne)");
		System.out.println("LX + Entrée (aller à la ligne X)");

		Scanner reader = new Scanner(System.in);
		System.out.println(reader);
		String input = reader.next();

		switch(input){
			case "" :
				System.out.println("Entrée");
				nextLine();
				break;
			case "B" :
				System.out.println("B + Entrée");
				previousLine();
				break;
		}

	}

	private void previousLine() {
	}

	private void nextLine() {
	}
	

}
