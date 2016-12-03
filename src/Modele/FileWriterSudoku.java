package Modele;

import java.io.File;
import java.io.FileWriter;

public class FileWriterSudoku 
{
	private String sudokuRempli;
	private String sudokuVide;
	private Jeu jeu;
	
	public FileWriterSudoku(Jeu jeu) 
	{
		this.jeu = jeu;
	}
	
	public void writeInFile()
	{
		final String chemin = "C:/tmp.txt";
	    final File fichier =new File(chemin); 
	    try 
	    {
	        // Creation du fichier
	        fichier .createNewFile();
	        // creation d'un writer (un écrivain)
	        final FileWriter writer = new FileWriter(fichier);
	        
	        try 
	        {
	            sudokuRempli = recupererStringSudokuRempli();
	            sudokuVide = recupererStringSudokuVide();
	        } 
	        finally 
	        {
	            // quoiqu'il arrive, on ferme le fichier
	            writer.close();
	        }
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Impossible de creer le fichier");
	    }
	}

	private String recupererStringSudokuVide() 
	{
		return null;
	}

	// On recupère la chaine de caracteres représentant le sudoku rempli.
	private String recupererStringSudokuRempli() 
	{
		String aRetourner = "";
		int x = 0;
        int y = 0;
		Groupe matrice[] = jeu.getValue();
		for (int i = 0; i < 81; i++)
        {
			
            aRetourner += Integer.toString(matrice[x].getCaseValueFromLine(y++));

            if (y > 8)
            {
            	aRetourner += "\n";
                y = 0;
                x++;
            }
        }
		
		return aRetourner;
	}
	
}
