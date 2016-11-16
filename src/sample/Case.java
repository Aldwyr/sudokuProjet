package sample;

public class Case
{
    protected Valeur valeur;
    protected Groupe groupe[]; // 3
    protected boolean conflit;

    public Case()
    {
        groupe = new Groupe[3];
        conflit = false;
        valeur = Valeur.fromInt(0);
    }

    public Case(Valeur valeur, Groupe[] groupe)
    {
        this.valeur = valeur;
        this.groupe = groupe;
    }

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

    // même principe que add de groupe, ce dernier parcour toute les occurences de this.groupe pour trouver la
    // première occurence de null et le faire pointer vers sa référence.
    public void setGroupe(Groupe groupe)
    {
        int i = 0;

        while (this.groupe[i] != null)
            i++;
        this.groupe[i] = groupe;
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
