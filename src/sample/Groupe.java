package sample;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Groupe
{

    private Case cases[];

    public void add(Case newCase)
    {
    }

    public Groupe()
    {
        cases = new Case[9];
    }

    public boolean estEnConflit(Case c) {
//        boolean returnVal = false;

        for (Case c2 = cases)
        {
            if (c != c2 && c.getValeur() == c2.getValeur())
                return true;
        }
        return false;
    }
}
