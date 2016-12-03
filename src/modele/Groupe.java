package modele;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Groupe
{

    private Case cases[];
    
    //TODO: à changer.
    public Groupe()
    {
    	this.cases = new Case[9];
    }

    public void add(Case newCase)
    {
        int i = 0;
        // On parcours le tableau de case jusqu'à que l"on tombe sur un null.
        while (this.cases[i] != null)
            i++;
        this.cases[i] = newCase;

        newCase.setGroupe(this);
    }
    
    public boolean estEnConflit(Case c) 
    {
//        boolean returnVal = false;

        for (Case c2 : cases)
        {
            if (c != c2 && c.getValeur() == c2.getValeur())
                return true;
        }
        return false;
    }

    public Case[] getCases()
    {
        return cases;
    }

    public void setCases(Case[] cases)
    {
        this.cases = cases;
    }

    //    public Case getCaseType(int y) {
//
//    }
    public int getCaseValueFromLine(int y)
    {
        return this.getCases()[y].getValeur().toInt();

    }
}
