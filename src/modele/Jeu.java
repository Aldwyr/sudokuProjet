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
    private String[] str;
    private int tailleSudoku;
    private SudokuParameters sudokuParameters;

    public Jeu()
    {
    	
    }
    
    public void creerNouveauSudoku(String path)
	{
    	FileReader fileReader = new FileReader(path); // TODO: récuperer path choisi par l'utilisateur
    	this.sudokuParameters = fileReader.getSudokuParameters();    	
    	
        // on recupère le tableau de string contenant les lignes du sudoku et la taille du sudoku
        this.str = this.sudokuParameters.getTableauStringSudoku();
		this.tailleSudoku = this.sudokuParameters.getTailleSudoku();
		
	}

    public void init(String[] tableauStr)
    {
        double racineCarre = Math.sqrt(this.tailleSudoku);
        System.out.println(this.tailleSudoku);
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
                if (Objects.equals(tableauStr[i + 1].substring(j, j + 1), "0"))
                {
                    cases = new CaseNonBloquee();
                } else
                {
                    cases = new CaseBloquee(tableauStr[i + 1].substring(j, j + 1));
                }
                int numeroLigne = i;
                int numeroColonne = j;

                this.tableauGroupeLignes[numeroLigne].add(cases);
                this.tableauGroupeColonnes[numeroColonne].add(cases);
                this.tableauGroupeCarres[numeroLigne / 3][numeroColonne / 3].add(cases);
            }

        }
        setChanged();
        notifyObservers();
    }

    public Groupe[] getTableauLignes()
    {
        return tableauGroupeLignes;
    }

    /*
        public int[] getTypeOfCase() {
            Case newcase;
            for (int i = 0; i < getTailleSudoku() * getTailleSudoku(); i++)
            {
                for (int j = 0; j < getTailleSudoku(); j++)
                {
                   newcase = getTableauLignes()[i].getCase(j);
                }
            }
        }
    */
    public int getTailleSudoku()
    {
        return tailleSudoku;
    }

    public String[] getStr()
    {
        return str;
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
