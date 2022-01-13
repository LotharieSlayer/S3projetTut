package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import AlgoPars.ihm.IhmCui;
import AlgoPars.metier.Interpreteur;
import AlgoPars.metier.LecteurFichier;

public class Main {
	private LecteurFichier lecteur;
	private Interpreteur metier;
	private IhmCui ihm;
	private static Main instance;
	static String fichier;
	int limiteLine = 0;
	
	/**
	 * Constructeur de Main, l'ensemble du programme
	 * @throws IOException
	 */
	public Main() throws IOException
	{
		instance = this;
		this.lecteur = new LecteurFichier(fichier);
		this.metier = new Interpreteur(this.lecteur.getPseudoCode());
		this.ihm = new IhmCui(this.lecteur.getPseudoCodeColore(), this.lecteur.getTrace());
	}

	/**
	 * Recharge l'interpréteur dans la classe Interpreteur
	 */
	public void reloadInterpreteur(){
		this.metier = new Interpreteur(this.lecteur.getPseudoCode());
	}
	
	/**
	 * getInstance()
	 * @return l'instance de Main en cours
	 */
	public static Main getInstance() {
		return instance;
	}
	
	/**
	 * getConsole()
	 * @return Récupère la console se trouvant dans l'interprèteur
	 */
	public ArrayList<String> getConsole()
	{
		return this.metier.getConsole();
	}	

	/**
	 * getTraceur()
	 * @return Récupère le traceur dans l'interprèteur
	 */
	public HashMap<String, String> getTraceur()
	{
		return this.metier.getTraceur();
	}

	/**
	 * Fonction qui affecte la limite de la ligne à exécuter.
	 * @param limiteLine
	 */
	public void setLimiteLine(int limiteLine)
	{
		this.limiteLine = limiteLine;
	}

	/**
	 * getLimiteLine()
	 * @return Fonction qui retourne la limite de la ligne à exécuter
	 */
	public int getLimiteLine()
	{
		return this.limiteLine;
	}

/**
 * Démarrage
 * @param args Fichier à prendre en paramètre (si c'est Exemple1.algo, alors ce sera Exemple1 à entrer)
 * @throws Exception
 */
	public static void main (String[] args) throws Exception{
		fichier = "../" + args[0];
		new Main();
	}
}
