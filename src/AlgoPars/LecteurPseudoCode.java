package AlgoPars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class LecteurPseudoCode {
	static ArrayList<String> pseudoCode = new ArrayList<String>();
	
	public ArrayList<String> getPseudoCode() {
		return pseudoCode;	
	}
	
	public LecteurPseudoCode() throws IOException
	{
		 System.out.println("Veuillez indiquer le fichier a ouvrir : ");
			Scanner reader = new Scanner(System.in);
			String fichier = reader.next();
			reader.close();
			
		    try
		    {
		      // Le fichier d'entrée
		      File file = new File(fichier);    
		      // Créer l'objet File Reader
		      FileInputStream fis = new FileInputStream(file);
			  // Encoder en UTF8
			  InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		      // Créer l'objet BufferedReader        
		      BufferedReader br = new BufferedReader(isr);   
		      String line;
		      while((line = br.readLine()) != null)
		      {
		    	pseudoCode.add(line);
		      }
		      fis.close();    
		    }
		    catch(IOException e)
		    {
		      e.printStackTrace();
		    }
	}
}
