package modele;

public class SudokuParameters
{
	int tailleSudoku;
	String[] tableauStringSudokuVide;
	String[] tableauStringSudokuRempli;
	
	public SudokuParameters()
	{
		
	}
	
	public int getTailleSudoku()
	{
		return this.tailleSudoku;
	}
	
	public void setTailleSudoku(int tailleSudoku)
	{
		this.tailleSudoku = tailleSudoku;
	}
	
	public String[] getTableauStringSudokuVide()
	{
		return this.tableauStringSudokuVide;
	}
	
	public void setTableauStringSudokuVide(String[] tableauStringSudoku)
	{
		this.tableauStringSudokuVide = tableauStringSudoku;
	}
	
	public String[] getTableauStringSudokuRempli()
	{
		return this.tableauStringSudokuRempli;
	}
	
	public void setTableauStringSudokuRempli(String[] tableauStringSudokuRempli)
	{
		this.tableauStringSudokuRempli = tableauStringSudokuRempli;
	}

}
