package AlgoPars.metier;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LecteurFichier {
	static ArrayList<String> pseudoCode = new ArrayList<String>();
	static ArrayList<String> pseudoCodeCouleur = new ArrayList<String>();
	static ArrayList<String> varTrace = new ArrayList<String>();
	
	/**
	 * Méthode qui retourne le pseudo code coloré
	 * @return pseudo code coloré
	 */
	public ArrayList<String> getPseudoCodeColore() {
		return pseudoCodeCouleur;	
	}	

	/**
	 * Méthode qui retourne le pseudo code sans couleur
	 * @return pseudo code pur
	 */
	public ArrayList<String> getPseudoCode() {
		return pseudoCode;	
	}
	
	/**
	 * Méthode qui retourne les variables sélectionnées à tracer
	 * @return les variables sélectionnées à tracer
	 */
	public ArrayList<String> getTrace() {
		return varTrace;
	}

	/**
	 * Constructeur de LecteurFichier
	 * Permet de lire et stocker les données entrantes d'un fichier pris en paramètre
	 * @param fichier appelé en argument dans la console
	 */
	public LecteurFichier(String fichier)
	{
	
		// On essaye de lire le .algo
		try (Scanner sc = new Scanner(new FileInputStream(fichier + ".algo"), "UTF-8"))
		{
			while (sc.hasNext())
				pseudoCode.add(sc.nextLine());

			Coloration.chargerCouleurs();
			for(String i : pseudoCode)
				pseudoCodeCouleur.add(Coloration.colorierLigne(i));

		}
		catch(IOException e)
		{
			System.out.println("Aucun fichier trouvé en .algo à exécuter avec le nom spécifié.\n");
		}

		// On essaye de lire le .var
		try (Scanner sc = new Scanner(new FileInputStream(fichier + ".var"), "UTF-8"))
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
