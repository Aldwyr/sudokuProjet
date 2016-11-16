package sample;

import java.util.Objects;

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



    public Jeu(String str)
    {
        init(str);
    }

    public void init(String str)
    {
        tableauLignes = new Groupe[9];
        tableauColonnes = new Groupe[9];
        tableauCarres = new Groupe[3][3];
        String tableauData[] = str.split(" "); // l'espace est le séparateur.

        // On remplis les tableaux.
        Tools.remplirTab(tableauLignes);
        Tools.remplirTab(tableauColonnes);
        Tools.remplirTab(tableauCarres);

        for (int i = 0; i < tableauData.length; ++i)
        {
            Case cases;
            if (Objects.equals(tableauData[i], "0"))
            {
                cases = new CaseNonBloquee();
            } else
            {
                cases = new CaseBloquee(tableauData[i]);
            }
            int numeroLigne = i / 9;
            int numeroColonne = i % 9;
            tableauLignes[numeroLigne].add(cases);
            tableauColonnes[numeroColonne].add(cases);
            tableauCarres[numeroLigne / 3][numeroColonne / 3].add(cases);
        }
    }

}
