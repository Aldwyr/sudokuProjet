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

    public Jeu()
    {
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
        setChanged();
        notifyObservers();
    }

    public Groupe[] getValue()
    {
        return this.tableauLignes;
    }
}
