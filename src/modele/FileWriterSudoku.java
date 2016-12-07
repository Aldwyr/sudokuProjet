package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.omg.CORBA.Environment;

public class FileWriterSudoku 
{
	private String sudokuRempli;
	private String sudokuVide;
	private String tailleSudoku;
	private Jeu jeu;
	private SudokuParameters sudokuParameters;
	
	public FileWriterSudoku(Jeu jeu) 
	{
		this.jeu = jeu;
		this.sudokuParameters = this.jeu.getSudokuParameters();
	}
	
	public void writeInFile(String path)
	{
	    final File fichier = new File(path);
	    
	    try 
	    {
	        // Creation du fichier
	        fichier.createNewFile();
	        
	        // creation d'un writer (un �crivain)
	        BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
	        
	        try 
	        {
	        	tailleSudoku = Integer.toString(this.sudokuParameters.getTailleSudoku());
	            
	            sudokuVide = recupererStringSudokuVide();
	            System.out.println(sudokuVide);
	            
	            sudokuRempli = recupererStringSudokuRempli();
	            System.out.println(sudokuRempli);
	            
	            //writer.write(tailleSudoku);
	            //writer.newLine();
	            writer.write(sudokuVide);
	            writer.newLine();
	            writer.write(sudokuRempli);
	        }
	        finally 
	        {
	            // quoiqu'il arrive, on ferme le fichier
	            writer.close();
	        }
	    } 
	    catch (Exception e)
	    {
	        //System.out.println("Impossible de creer le fichier");
	    	e.printStackTrace();
	    }
	}

	private String recupererStringSudokuVide() 
	{
		String[] sudokuVide = this.sudokuParameters.getTableauStringSudokuVide();
		String aRetourner = "";
		
		for (int i = 0; i < sudokuVide.length; i++)
		{
			aRetourner += sudokuVide[i];
			aRetourner += System.lineSeparator();
		}
		
		return aRetourner;
	}

	// On recupère la chaine de caracteres représentant le sudoku rempli.
	//TODO: changer pour prendre les bonnes valeurs du sudoku rempli
	private String recupererStringSudokuRempli() 
	{
		String aRetourner = "";
		int x = 0;
        int y = 0;
		Groupe matrice[] = this.jeu.getNombreDeLignes();
		
		for (int i = 0; i < this.sudokuParameters.getTailleSudoku() * this.sudokuParameters.getTailleSudoku(); i++)
        {
            aRetourner += Integer.toString(matrice[x].getValeurCaseColonneDonnee(y++));

            if (y > this.sudokuParameters.getTailleSudoku() - 1)
            {
            	aRetourner += System.lineSeparator();
                y = 0;
                x++;
            }
        }
		
		return aRetourner;
	}
	
}
