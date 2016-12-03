package modele;

public class SudokuParameters
{
	int tailleSudoku;
	String[] tableauStringSudoku;
	
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
	
	public String[] getTableauStringSudoku()
	{
		return this.tableauStringSudoku;
	}
	
	public void setTableauStringSudoku(String[] tableauStringSudoku)
	{
		this.tableauStringSudoku = tableauStringSudoku;
	}

}
