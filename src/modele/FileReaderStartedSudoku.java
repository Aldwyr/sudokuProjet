package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderStartedSudoku
{
	
	private SudokuParameters sudokuParameters;

	public FileReaderStartedSudoku(String filename)
	{
		try
		{
			readFromFileDejaCommence(filename);
		} 
    	catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// On change la fonction de la superclasse en ajoutant la lecture de la deuxi�me partie du fichier texte
	public void readFromFileDejaCommence(String filename) throws FileNotFoundException
    {
    	SudokuParameters sudokuParameters = new SudokuParameters();
    	
    	int nombreDeLignes = -1;
        String[] tableauStringSudokuVide = null;
        String[] tableauStringSudokuRempli = null;
        int index = 0;

        try
        {
            InputStream inputStream = new FileInputStream(new File(filename));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            
            while ((line = bufferedReader.readLine()) != null)
            {
            	if (nombreDeLignes == -1)
            	{
            		nombreDeLignes = Integer.parseInt(line);
            		tableauStringSudokuVide = new String[nombreDeLignes];
            		tableauStringSudokuRempli = new String[nombreDeLignes];
            		System.out.println(nombreDeLignes);
            		
            	}
            	
            	System.out.println(line);
            	
            	if (index <= nombreDeLignes + 1)
            	{
            		
            		tableauStringSudokuVide[index] = line;
            		
            	}
            	else
            	{
            		//On passe � la prochaine ligne si on est sur le saut de ligne s�parant les 2 sudokus
            		if (line == "\n")
            			index++;
            		
            		tableauStringSudokuRempli[index] = line;
            	}
            	
                index++;
            }
            
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        sudokuParameters.setTableauStringSudokuVide(tableauStringSudokuVide);
        sudokuParameters.setTailleSudoku(Integer.parseInt(tableauStringSudokuVide[0]));
        sudokuParameters.setTableauStringSudokuRempli(tableauStringSudokuRempli);
        
        System.out.println(tableauStringSudokuRempli[0]);
        
        this.sudokuParameters = sudokuParameters;

    }
	
	public SudokuParameters getSudokuParameters()
	{
		return this.sudokuParameters;
	}

}
