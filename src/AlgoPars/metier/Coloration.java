package AlgoPars.metier;

import iut.algo.Console;
import iut.algo.CouleurConsole;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.File;


public class Coloration {
    private static HashMap<String, ArrayList<String>> couleurs;
	private static HashMap<String, Pattern> regex;

	/**
	 * chargerCouleurs()
	 * Méthode qui charge la couleur des textes via un fichier xml
	 */
	public static void chargerCouleurs()
	{
		couleurs    = new HashMap<String, ArrayList<String>>();
		regex 		= new HashMap<String, Pattern>();

		Element racine = null;
		try {
			Document doc = new SAXBuilder().build(new File( "../coloration.xml"));
			racine = doc.getRootElement();
		}
		catch( Exception e ) { e.printStackTrace(); }


		ArrayList<String> alGetXML = null;
		for( Element e: racine.getChildren() )
		{
			for( Element child: e.getChildren() )
			{
				alGetXML = new ArrayList<String>();
				alGetXML.add( e.getAttribute( "idCoul" ).getValue() );
				alGetXML.add( e.getAttribute( "poids" ).getValue() );

				couleurs.put( child.getText(), alGetXML );
				regex.put( child.getText(), Pattern.compile( "\\b" + child.getText() + "\\b(?![^\"]*\"[^\"]*(?:\"[^\"]*\"[^\"]*)*$)" ) );
			}
		}
	}


	/**
	 * Méthode qui colorie la ligne en paramètre et l'a retourne coloriée
	 * @param ligne
	 * @return la ligne coloriée
	 */
	public static String colorierLigne(String ligne)
	{
		int ligneLengthDebut = ligne.length();
		String debutLigne = ""; // Utilisée pour éviter de colorer des keywords dans les commentaires.
		String finLigne = "";

		if(ligne.contains("//"))
		{
			int debCommentaire = ligne.indexOf( "//" );
			if ( debCommentaire == 0 ) ligne = CouleurConsole.VERT.getFont() + ligne + "\033[0m";
			else
			{
				debutLigne = ligne.substring( 0, debCommentaire );
				finLigne = CouleurConsole.VERT.getFont() + ligne.substring( debCommentaire, ligne.length() ) + "\033[0m";
			}
		}
		else
			debutLigne = ligne;

		Matcher matcher = null;
		for (String mot : couleurs.keySet())
		{
			matcher = regex.get( mot ).matcher(ligne);
			if (matcher.find())
			{
				if ( mot.equals("a") && ligne.indexOf("a") < ligne.indexOf("faire") )
					debutLigne = debutLigne.replaceFirst(mot, colorierMot(mot));
				else
					debutLigne = debutLigne.replace(mot, colorierMot(mot));
			}
		}
		
		String resultat = debutLigne + finLigne;
		if(resultat.length() > 85) return resultat.substring(0, 85);
		else
			return resultat + " ".repeat(76 - ligneLengthDebut);
	}


	/**
	 * Méthode qui colorie la mot en paramètre et l'a retourne coloriée
	 * @param mot
	 * @return le mot colorié
	 */
	private static String colorierMot(String mot)
	{
		if (!couleurs.containsKey(mot)) return mot;

		// Switch case pour permettre une flexibilité dans les couleurs en fonction de l'identifiant couleur
		String couleur;
		String id = couleurs.get(mot).get( 0 );
		switch ( id ) 
		{
			case "0": 
				couleur = CouleurConsole.BLANC.getFont();
				break;
			case "1": 
				couleur = CouleurConsole.JAUNE.getFont();
				break;
			case "2": 
				couleur = CouleurConsole.CYAN.getFont();
				break;
			case "3": 
				couleur = CouleurConsole.MAUVE.getFont();
				break;
			case "4": 
				couleur = CouleurConsole.BLEU.getFont();
				break;
			case "5":
				couleur = CouleurConsole.VERT.getFont();
				break;
			case "6":
				couleur = CouleurConsole.ROUGE.getFont();
				break;
			default: 
				couleur = CouleurConsole.BLANC.getFont();
		}

		if (couleurs.get(mot).get(1).equals("true") ) 
			couleur += "\033[1m";

		return couleur + mot + "\033[0m";
	}

}
