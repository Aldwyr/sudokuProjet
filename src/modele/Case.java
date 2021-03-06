package modele;

public class Case
{
    protected Valeur valeur;
    protected Groupe groupe[]; // 3
    protected boolean conflit[];
    
    public Case()
    {
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

    public boolean[] getConflit() {
        return conflit;
    }

    public void setConflit(boolean[] conflit) {
        this.conflit = conflit;
    }

    public void majConflit() {
        if (this.valeur == Valeur.ZERO)
        {
            for (int i = 0; i < 3; i++)
            {
                this.conflit[i] = false;
            }
        } else
            for (int i = 0; i < this.groupe.length; ++i) {
                if (this.groupe[i].estEnConflit(this)) {
                    this.conflit[i] = true;
                } else
                    this.conflit[i] = false;
            }
    }

    public void MAJ(Valeur newVal) {
        valeur = newVal;
        majConflit();
        for (int i = 0; i < 3; i++)
        {
            this.groupe[i].majConflitGroupe();
        }
    }
}
