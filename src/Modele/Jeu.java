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
    private int tailleSudoku;

    public Jeu()
    {

        try
        {
            str = FileReader.readFromFile("../stringSudoku.txt");
            this.tailleSudoku = Integer.parseInt(str[0]);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void init(String[] tableauStr)
    {
        double racineCarre = Math.sqrt(getTailleSudoku());
        this.tableauLignes = new Groupe[getTailleSudoku()];
        this.tableauColonnes = new Groupe[getTailleSudoku()];
        this.tableauCarres = new Groupe[(int) racineCarre][(int) racineCarre];

        // On remplis les tableaux.
        Outils.remplirTab(this.tableauLignes);
        Outils.remplirTab(this.tableauColonnes);
        Outils.remplirTab(this.tableauCarres);

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

                this.tableauLignes[numeroLigne].add(cases);
                this.tableauColonnes[numeroColonne].add(cases);
                this.tableauCarres[numeroLigne / 3][numeroColonne / 3].add(cases);
            }

        }
        setChanged();
        notifyObservers();
    }

    public Groupe[] getTableauLignes()
    {
        return tableauLignes;
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

    public Groupe[] getValue()
    {
        return this.tableauLignes;
    }
}
