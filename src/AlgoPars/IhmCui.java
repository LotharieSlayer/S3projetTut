package AlgoPars;

import java.util.ArrayList;

public class IhmCui {
	public IhmCui(ArrayList<String> pseudoCode) {
		System.out.println("-----------");
		System.out.println("|   CODE   |");
		System.out.println("----------------------------------------------------------------------------");
		for(int i = 0; i < pseudoCode.size(); i++)
		{
			// Vérification si + de 40 lignes
			if(i > 40){
				System.out.println("|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-70s", "..." ) + " " + "|");
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
		
		
	}
	

}
