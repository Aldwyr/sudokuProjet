package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader
{
	private SudokuParameters sudokuParameters;
	
    public FileReader(String filename)
    {    	
    	try
		{
            readFromFile(filename);
        }
    	catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void readFromFile(String filename) throws FileNotFoundException
    {
    	SudokuParameters sudokuParameters = new SudokuParameters();
    	
        int nombreDeLigne = recupererNombreDeLigne(filename);
        String[] tableauStringSudoku = new String[nombreDeLigne];
        int index = 0;

        try
        {
            InputStream inputStream = new FileInputStream(new File(filename));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                tableauStringSudoku[index] = line;
                index++;
            }
            
            bufferedReader.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        sudokuParameters.setTableauStringSudokuVide(tableauStringSudoku);
        sudokuParameters.setTailleSudoku(Integer.parseInt(tableauStringSudoku[0]));
        
        this.sudokuParameters = sudokuParameters;
        

    }

    protected int recupererNombreDeLigne(String filename) throws FileNotFoundException
    {
        int nombreDeLignes = 0;

        try
        {
            InputStream inputStream = new FileInputStream(new File(filename));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((bufferedReader.readLine()) != null)
            {
                nombreDeLignes++;
            }
            bufferedReader.close();
            return nombreDeLignes;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return 0;
    }
    
    public SudokuParameters getSudokuParameters()
	{
		return this.sudokuParameters;
	}
}
