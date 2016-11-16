package sample;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class Tools
{
    public static void remplirTab(Groupe tab[])
    {
        for (int i = 0; i < tab.length; ++i)
        {
            tab[i] = new Groupe();
        }
    }

    public static void remplirTab(Case tab[])
    {
        for (int i = 0; i < tab.length; ++i)
        {
            tab[i] = new Case();
        }
    }

    public static void remplirTab(Groupe tab[][])
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                tab[i][j] = new Groupe();
            }
        }
    }

}
