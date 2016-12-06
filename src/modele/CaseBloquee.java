package modele;

/**
 * Created by gregorygueux on 16/11/2016.
 */
public class CaseBloquee extends Case
{
    public CaseBloquee(String str)
    {
        int value = Integer.parseInt(str);
        try
        {
            this.valeur = Valeur.fromInt(value);
        }
        catch (NullPointerException e)
        {
            System.out.println("Erreur lors de la récupération de la valeur.");
        }
        groupe = new Groupe[3];
        //Outils.remplirTab(groupe);
        conflit = new boolean[3];
        for (int i = 0; i < 3; i++) {
            conflit[i] = false;
        }
    }
}
