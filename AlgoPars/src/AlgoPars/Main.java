package AlgoPars;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	private LecteurPseudoCode lecteur;
	private Interpreteur metier;
	private IhmCui ihm;
	private static Main instance;
	
	public Main() throws IOException
	{
		instance = this;
		this.lecteur = new LecteurPseudoCode();
		this.metier = new Interpreteur(this.lecteur.getPseudoCode());
		this.ihm = new IhmCui(this.lecteur.getPseudoCode());
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public ArrayList<String> getConsole()
	{
		return this.metier.getConsole();
	}
	
	public static void main (String[] args) throws Exception{
		new Main();
	}
}
