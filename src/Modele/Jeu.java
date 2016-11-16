package Modele;

import java.util.Objects;
import java.util.Observable;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Jeu extends Observable
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



    public Jeu()
    {
        String str = "5 3 0 0 7 0 0 0 0 6 0 0 1 9 5 0 0 0 0 9 8 0 0 0 0 6 0 8 0 0 0 6 0 0 0 3 4 0 0 8 0 3 0 0 1 7 0 0 0 2 0 0 0 6 0 6 0 0 0 0 2 8 0 0 0 0 4 1 9 0 0 5 0 0 0 0 8 0 0 7 9";
        init(str);
    }

    public void init(String str)
    {
        this.tableauLignes = new Groupe[9];
        this.tableauColonnes = new Groupe[9];
        this.tableauCarres = new Groupe[3][3];
        String tableauData[] = str.split(" "); // l'espace est le séparateur.

        // On remplis les tableaux.
        Outils.remplirTab(this.tableauLignes);
        Outils.remplirTab(this.tableauColonnes);
        Outils.remplirTab(this.tableauCarres);

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

            this.tableauLignes[numeroLigne].add(cases);
            this.tableauColonnes[numeroColonne].add(cases);
            this.tableauCarres[numeroLigne / 3][numeroColonne / 3].add(cases);
        }
    }

}
