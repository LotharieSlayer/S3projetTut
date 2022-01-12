package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	private LecteurFichier lecteur;
	private Interpreteur metier;
	private IhmCui ihm;
	private static Main instance;
	static String fichier;
	int limiteLine = 0;
	
	public Main() throws IOException
	{
		instance = this;
		this.lecteur = new LecteurFichier(fichier);
		this.metier = new Interpreteur(this.lecteur.getPseudoCode());
		this.ihm = new IhmCui(this.lecteur.getPseudoCode(), this.lecteur.getTrace());
	}

	public void reloadInterpreteur(){
		this.metier = new Interpreteur(this.lecteur.getPseudoCode());
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public ArrayList<String> getConsole()
	{
		return this.metier.getConsole();
	}	

	public HashMap<String, String> getTraceur()
	{
		return this.metier.getTraceur();
	}


	public void setLimiteLine(int limiteLine)
	{
		this.limiteLine = limiteLine;
	}

	public int getLimiteLine()
	{
		return this.limiteLine;
	}

	public static void main (String[] args) throws Exception{
		fichier = "../" + args[0];
		new Main();
	}
}
//Je suis actuellement en train de coder (des trucs pas oufs mais tkrl)
