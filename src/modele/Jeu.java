package modele;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Observable;

import vue.VueSudoku;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Jeu extends Observable
{

    private Groupe tableauGroupeLignes[];
    private Groupe tableauGroupeColonnes[];
    private Groupe tableauGroupeCarres[][];
    private String[] strSudokuVide;
    private String[] strSudokuRempli;
    private int tailleSudoku;
    private SudokuParameters sudokuParameters;
    
    public Jeu()
    {
    	
    }
    
    public void creerNouveauSudoku(String path)
	{
    	FileReader fileReader = new FileReader(path);
    	this.sudokuParameters = fileReader.getSudokuParameters();    	
    	
        // on recup�re le tableau de string contenant les lignes du sudoku et la taille du sudoku
        this.strSudokuVide = this.sudokuParameters.getTableauStringSudokuVide();
		this.tailleSudoku = this.sudokuParameters.getTailleSudoku();
		
	}
    
    public void chargerSudokuCommence(String path)
    {
    	FileReaderStartedSudoku fileReaderStartedSudoku = new FileReaderStartedSudoku(path);
    	
    	this.sudokuParameters = fileReaderStartedSudoku.getSudokuParameters(); 
    	this.tailleSudoku = this.sudokuParameters.getTailleSudoku();
    	this.strSudokuVide = this.sudokuParameters.getTableauStringSudokuVide();
    	this.strSudokuRempli = this.sudokuParameters.getTableauStringSudokuRempli();
    	
    	for (int i = 0; i < this.strSudokuVide.length; i++)
    	{
    		
    	}
    	
    }

    public void init(String[] tableauStr)
    {
        double racineCarre = Math.sqrt(this.tailleSudoku);
        this.tableauGroupeLignes = new Groupe[this.tailleSudoku];
        this.tableauGroupeColonnes = new Groupe[this.tailleSudoku];
        this.tableauGroupeCarres = new Groupe[(int) racineCarre][(int) racineCarre];

        // On remplis les tableaux.
        Outils.remplirTab(this.tableauGroupeLignes);
        Outils.remplirTab(this.tableauGroupeColonnes);
        Outils.remplirTab(this.tableauGroupeCarres);
        
    	for (int i = 0; i < tableauStr.length - 1; ++i)
        {
            for (int j = 0; j < tableauStr.length - 1; j++)
            {
                Case cases;
                //System.out.println(tableauStr.length-1);
                String emplacementCase = tableauStr[i + 1].substring(j, j + 1);
                
                if (Objects.equals(emplacementCase, "0"))
                {
                    cases = new CaseNonBloquee();
                } 
                else
                {
                    cases = new CaseBloquee(emplacementCase);
                }
                this.tableauGroupeLignes[i].add(cases);
                this.tableauGroupeColonnes[j].add(cases);
                this.tableauGroupeCarres[i / 3][j / 3].add(cases);
            }

        }
        
        setChanged();
        notifyObservers();
    }

    public void changeValeurCase(int newValeur, int posx, int posY)
    {
        this.tableauGroupeLignes[posx].getCases()[posY].MAJ(Valeur.fromInt(newValeur));
        setChanged();
        notifyObservers();
    }

    public Groupe[] getTableauLignes()
    {
        return tableauGroupeLignes;
    }


    public int[][] recupTouteLesTypesDesCases()
    {
        int typeDesCases[][] =  new int[getTailleSudoku()][getTailleSudoku()];

        for (int i = 0; i < getTailleSudoku(); i++)
            for (int j = 0; j < getTailleSudoku(); j++)
                typeDesCases[i][j] = getTableauLignes()[i].getTypeDeCase(j);
        
        return typeDesCases;
    }
        
    public int getTailleSudoku()
    {
        return tailleSudoku;
    }

    public String[] getStr()
    {
        return this.strSudokuVide;
    }

    public Groupe[] getNombreDeLignes()
    {
        return this.tableauGroupeLignes;
    }
    
    public SudokuParameters getSudokuParameters()
	{
		return this.sudokuParameters;
	}
    
}
