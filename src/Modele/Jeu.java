package Modele;

import java.io.FileNotFoundException;
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
    private String[] str;

    public Jeu()
    {

        try
        {
            str = FileReader.readFromFile("../stringSudoku.txt");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void init(String[] tableauStr)
    {
        int tailleSudoku = Integer.parseInt(tableauStr[0]);
        double racineCarre = Math.sqrt(tailleSudoku);
        this.tableauLignes = new Groupe[tailleSudoku];
        this.tableauColonnes = new Groupe[tailleSudoku];
        this.tableauCarres = new Groupe[(int) racineCarre][(int) racineCarre];

        // On remplis les tableaux.
        Outils.remplirTab(this.tableauLignes);
        Outils.remplirTab(this.tableauColonnes);
        Outils.remplirTab(this.tableauCarres);

        for (int i = 0; i < tableauStr.length - 1; ++i)
        {
            Case cases;
            if (Objects.equals(tableauStr[i + 1], "0"))
            {
                cases = new CaseNonBloquee();
            } else
            {
                cases = new CaseBloquee(tableauStr[i + 1]);
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

    public String[] getStr()
    {
        return str;
    }

    public Groupe[] getValue()
    {
        return this.tableauLignes;
    }
}
