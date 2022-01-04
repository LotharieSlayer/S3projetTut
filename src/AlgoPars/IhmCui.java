package AlgoPars;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class IhmCui {


	/**
	 * 
	 * @param pseudoCode	Le pseudo-code en entrée pour l'afficher
	 * @param indexConsole	indexConsole va être utilisé ici pour sélectionner la ligne où l'affichage va devoir se déplacer 
	 */
	public IhmCui(ArrayList<String> pseudoCode, int indexConsole) {
		System.out.println("-----------");
		System.out.println("|   CODE   |");
		System.out.println("----------------------------------------------------------------------------");
		for(int i = 0; i < pseudoCode.size(); i++)
		{
			// Vérification si + de 40 lignes
			if(i > 40){
				System.out.println("|" + String.format ( "%-70s", "..." ) + "    " + "|");
				break;
			}
			// Vérification si + de 80 caractères
			if( pseudoCode.get(i).length() > 80){
				String stringTemp = pseudoCode.get(i).substring(0, Math.min(80, pseudoCode.get(i).length()));
				stringTemp = stringTemp + "...";
				System.out.println("|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-70s", stringTemp ) + " " + "|");
				continue;
			}
			System.out.println("|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-70s", pseudoCode.get(i) ) + " " + "|");
		}
		System.out.println("----------------------------------------------------------------------------");
		
		System.out.println("\n");
		
		System.out.println("--------------");
		System.out.println("|   CONSOLE   |");
		System.out.println("----------------------------------------------------------------------------");
		ArrayList<String> console = Main.getInstance().getConsole();
		for(int i = 0; i < console.size(); i++)
		{
			System.out.println("|" + String.format ( "%-73s", console.get(i) ) + " " + "|");
		}
		System.out.println("----------------------------------------------------------------------------");
		
		getUserInput();

		
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
