package modele;

/**
 * Created by gregorygueux on 16/11/2016.
 */


public enum Valeur
{
    ZERO(0),
    UN (1),
    DEUX (2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    HUIT(8),
    NEUF(9);

    private int s;

    Valeur(int s)
    {
        this.s = s;
    }

    public static Valeur fromInt(int i) throws NullPointerException
    {
        for (Valeur valeurARetourner : Valeur.values())
        {
            if (valeurARetourner.toInt() == i)
                return valeurARetourner;
        }
        return null;
    }

    public int toInt()
    {
        return this.s;
    }
}
