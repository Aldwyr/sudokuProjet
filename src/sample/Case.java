package sample;

public class Case
{
    private Valeur valeur;
    private Groupe groupe[]; // 3
    private boolean conflit;

    public Valeur getValeur()
    {
        return valeur;
    }

    public void setValeur(Valeur valeur)
    {
        this.valeur = valeur;
    }

    public Groupe[] getGroupe()
    {
        return groupe;
    }

    public void setGroupe(Groupe[] groupe)
    {
        this.groupe = groupe;
    }

    public boolean isConflit()
    {
        return conflit;
    }

    public void setConflit(boolean conflit)
    {
        this.conflit = conflit;
    }

    public void MAJ(Valeur newVal) {
        valeur = newVal;
        boolean conflit = false;

        for (int i = 0; i < groupe.length; ++i) {
            if (groupe[i].estEnConflit(this)){
                conflit = true;
            }
        }
    }
}
