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
        int indexDeuxiemeSudoku = 0;

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
            		tableauStringSudokuVide = new String[nombreDeLignes + 1];
            		tableauStringSudokuRempli = new String[nombreDeLignes + 1];
            		index++;
            	}
            	else
            	{
                	if (index <= nombreDeLignes)
                	{
                		tableauStringSudokuVide[index] = line;
                	}
                	else
                	{
                		//On passe � la prochaine ligne si on est sur le saut de ligne s�parant les 2 sudokus
                		if (line == "\n")
                			index++;
                		else
                		{
                			tableauStringSudokuRempli[indexDeuxiemeSudoku] = line;
                    		indexDeuxiemeSudoku++;
                		}
                	}

                    index++;
            	}
            }
            
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        sudokuParameters.setTableauStringSudokuVide(tableauStringSudokuVide);
        sudokuParameters.setTailleSudoku(nombreDeLignes);
        sudokuParameters.setTableauStringSudokuRempli(tableauStringSudokuRempli);
        this.sudokuParameters = sudokuParameters;

    }
	
	public SudokuParameters getSudokuParameters()
	{
		return this.sudokuParameters;
	}

}
