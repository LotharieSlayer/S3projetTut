package AlgoPars;

import java.util.ArrayList;

public class IhmCui {
	public IhmCui(ArrayList<String> pseudoCode) {
		System.out.println("-----------");
		System.out.println("|   CODE  |");
		System.out.println("----------------------------------------------------------------------------");
		for(int i = 0; i < pseudoCode.size(); i++)
		{
			System.out.println("|" + String.format ( "%-2d", i ) + " " +  String.format ( "%-70s", pseudoCode.get(i) ) + " " + "|");
		}
		System.out.println("----------------------------------------------------------------------------");
		
		System.out.println("\n");
		
		System.out.println("--------------");
		System.out.println("|   CONSOLE  |");
		System.out.println("----------------------------------------------------------------------------");
		ArrayList<String> console = Main.getInstance().getConsole();
		for(int i = 0; i < console.size(); i++)
		{
			System.out.println("|" + String.format ( "%-73s", console.get(i) ) + " " + "|");
		}
		System.out.println("----------------------------------------------------------------------------");
		
		
	}
	

}
