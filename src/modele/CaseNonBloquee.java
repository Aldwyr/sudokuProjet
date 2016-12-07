package modele;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by gregorygueux on 16/11/2016.
 */
public class CaseNonBloquee extends Case
{
    private ArrayList<Valeur> valeurPossible;
    private SudokuParameters sudokuParam;

    public CaseNonBloquee(SudokuParameters param)
    {
    	this.sudokuParam = param;
    	this.valeurPossible = new ArrayList<Valeur>();
    	
        this.groupe = new Groupe[3];
        this.conflit = new boolean[3];
        for (int i = 0; i < 3; i++) {
            conflit[i] = false;
        }
        valeur = Valeur.fromInt(0);
        
        // On rempli toutes les cases des differentes valeurs
        for (int i = 0; i < this.sudokuParam.tailleSudoku; i++)
        {
        	this.valeurPossible.add(Valeur.fromInt(i + 1));
        }
    }
    
    public void initialiserValeursPossibles()
    {
    	for (int i = 0; i < this.getGroupe().length; i++)
    	{
    		for (int j = 0; j < this.sudokuParam.getTailleSudoku(); j++)
    		{
    			Case cases = this.getGroupe()[i].getCases()[j];
    			
    			Valeur val = cases.getValeur();

				if (this.valeurPossible.contains(val))
					this.valeurPossible.remove(val);
    		}
    		
    	}
    }
    
    public ArrayList<Valeur> getValeurPossible() 
    {
		return this.valeurPossible;
	}
}
