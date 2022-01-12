package AlgoPars.metier;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LecteurFichier {
	static ArrayList<String> pseudoCode = new ArrayList<String>();
	static ArrayList<String> varTrace = new ArrayList<String>();
	
	public ArrayList<String> getPseudoCode() {
		return pseudoCode;	
	}
	
	public ArrayList<String> getTrace() {
		return varTrace;
	}

	public LecteurFichier(String fichier)
	{
	
		try (Scanner sc = new Scanner(new FileInputStream(fichier + ".algo")))
		{
			while (sc.hasNext())
				pseudoCode.add(sc.nextLine());
		}
		catch(IOException e)
		{
			System.out.println("Aucun fichier trouvé en .algo à exécuter avec le nom spécifié.\n");
		}

		try (Scanner sc = new Scanner(new FileInputStream(fichier + ".var")))
		{
			while (sc.hasNext())
				varTrace.add(sc.nextLine());
		}
		catch(IOException e)
		{
			System.out.println("Aucun fichier trouvé en .var pour suivre les variables.\n");
		}
	}
}
