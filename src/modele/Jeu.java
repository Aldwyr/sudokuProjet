package modele;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Observable;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
    private boolean sudokuResolu;
    private SudokuParameters sudokuParameters;
    
    public Jeu()
    {
    	
    }
    
    public void creerNouveauSudoku(String path)
	{
        this.sudokuResolu = false;
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
                
                String emplacementCase = tableauStr[i + 1].substring(j, j + 1);
                
                if (Objects.equals(emplacementCase, "0"))
                {
                    cases = new CaseNonBloquee(this.sudokuParameters);
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
    	
    	initialiserTableauValeursPossibles();
        
        setChanged();
        notifyObservers();
    }

	private void initialiserTableauValeursPossibles() 
	{
		// On recupère les groupes de cases		
		for (int i = 0; i< this.tableauGroupeLignes.length; i++)
    	{
			Groupe groupe = this.tableauGroupeLignes[i];
    		// on parcourt chaque case du groupe
    		for (int j = 0; j < groupe.getCases().length; j++)
    		{
    			Case cases = groupe.getCases()[j];
    			
    			if (cases instanceof CaseNonBloquee)
    			{
    				CaseNonBloquee caseNonBloquee = (CaseNonBloquee) cases;
    				caseNonBloquee.initialiserValeursPossibles();
    			}
    		}
    	}
	}

	private void majValeurPossible()
    {
        // On recupère les groupes de cases
        for (int i = 0; i< this.tableauGroupeLignes.length; i++)
        {
            Groupe groupe = this.tableauGroupeLignes[i];
            // on parcourt chaque case du groupe
            for (int j = 0; j < groupe.getCases().length; j++)
            {
                Case cases = groupe.getCases()[j];

                if (cases instanceof CaseNonBloquee && cases.getValeur() == Valeur.ZERO)
                {
                    CaseNonBloquee caseNonBloquee = (CaseNonBloquee) cases;
                    caseNonBloquee.initialiserValeursPossibles();
                }
            }
        }

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

    public int[][] recupLesConfilt() {
        //NOTE: 3 niveau : 1 -> 1 conflit, 2 -> 2 conflit, 3...
        int conflit[][] = new int[this.getTailleSudoku()][this.getTailleSudoku()];
        boolean resultatConflit[];
        for (int i = 0; i < this.getTailleSudoku(); i++) {
            for (int j = 0; j < this.getTailleSudoku(); j++) {
                resultatConflit = this.tableauGroupeLignes[i].getCases()[j].getConflit();
                for (int k = 0; k < 3; k++) {
                    if (resultatConflit[k])
                        conflit[i][j]++;
                }
            }
        }
        return conflit;
    }

    public int[][] recupTouteLesTypesDesCases()
    {
        int typeDesCases[][] =  new int[getTailleSudoku()][getTailleSudoku()];

        for (int i = 0; i < getTailleSudoku(); i++)
            for (int j = 0; j < getTailleSudoku(); j++)
                typeDesCases[i][j] = getTableauLignes()[i].getTypeDeCase(j);
        
        return typeDesCases;
    }

    public void miseAJourValeurCases()
    {
        String[] strSudokuCommence = sudokuParameters.getTableauStringSudokuRempli();

        for (int i = 0; i < strSudokuCommence.length - 1; ++i)
        {
            for (int j = 0; j < strSudokuCommence.length - 1; j++)
            {
                String emplacementCase = strSudokuCommence[i + 1].substring(j, j + 1);

                if (!(Objects.equals(emplacementCase, "0")))
                {
                    this.tableauGroupeLignes[i].getCases()[j].setValeur(Valeur.fromInt(Integer.parseInt(emplacementCase)));
                }
            }
        }
        
        notifierLaVue();

    }


    public int getTailleSudoku()
    {
        return tailleSudoku;
    }

    public boolean isSudokuResolu()
    {
        return sudokuResolu;
    }

    public void setSudokuResolu(boolean sudokuResolu)
    {
        this.sudokuResolu = sudokuResolu;
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

    public void notifierLaVue() {
        setChanged();
        notifyObservers();
    }

    public void viderSudoku()
    {

        for (int i = 0; i < this.getTailleSudoku(); i++)
        {
            for (int j = 0; j < this.getTailleSudoku(); j++)
            {
                if (this.tableauGroupeLignes[i].getCases()[j] instanceof CaseNonBloquee)
                    this.tableauGroupeLignes[i].getCases()[j].setValeur(Valeur.ZERO);
                this.tableauGroupeLignes[i].majConflitGroupe();
            }
        }
    }

	public void resoudreSudoku() 
	{

        viderSudoku();
        boolean caseAvecUneValeur = false;
		// On recupère les groupes de cases		
		for (int i = 0; i< this.tableauGroupeLignes.length; i++)
    	{
			Groupe groupe = this.tableauGroupeLignes[i];
    		// on parcourt chaque case du groupe
    		for (int j = 0; j < groupe.getCases().length; j++)
    		{
    			Case cases = groupe.getCases()[j];
    			
    			if (cases instanceof CaseNonBloquee)
    			{
    				CaseNonBloquee caseNonBloquee = (CaseNonBloquee) cases;
    				if (caseNonBloquee.getValeurPossible().size() == 1 && caseNonBloquee.getValeur() == Valeur.ZERO)
    				{
    					caseNonBloquee.valeur = caseNonBloquee.getValeurPossible().get(0);
                        caseAvecUneValeur = true;
    				}
    			}
    		}
    		if (caseAvecUneValeur) {
                majValeurPossible();
                i = -1;
                caseAvecUneValeur = false;
            }
            if (this.sudokuResolu)
                caseAvecUneValeur = false;
    	}
		
	}
}
