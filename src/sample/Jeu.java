package sample;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Jeu
{

    private Groupe tableauLignes[];
    private Groupe tableauColonnes[];
    private Groupe tableauCarres[][];
    /*
        tabL = new Groupe[9]
        tabC = new Groupe[9] -> new Groupe();
        talCarre = new Groupe[3][3]

        String[] tabData =  data.sprit(" "); // séparateur

        for (int i = 0; i <  tabData.length; ++i {
            Case case;
            if (tabData[i] == "0") {
                case  = new CaseNonBloque();
            }
            else
            {
                case = new CaseBloque(tabData[i]);
            }

            int numL = i/9;
            int numC = i%9;
            tablL[numL].add(case);
            tabC[numC].add(case);
            tabCar[numL/3][numC/3].add(case);
        }
     */


    private void remplirTab(Groupe tab[])
    {
        for (int i = 0; i < tab.length; ++i)
        {
            tab[i] = new Groupe();
        }
    }

    private void remplirTab(Groupe tab[][])
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++i)
            {
                tab[i][j] = new Groupe();
            }
        }
    }

    public Jeu(String str)
    {
        init(str);
    }

    public void init(String str)
    {
        tableauLignes = new Groupe[9];
        tableauColonnes = new Groupe[9];
        tableauCarres = new Groupe[3][3];

        // On remplis les tableaux.
        remplirTab(tableauLignes);
        remplirTab(tableauColonnes);
        remplirTab(tableauCarres);


    }

}
