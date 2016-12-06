package controleur;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.FileWriterSudoku;
import modele.Jeu;
import vue.VueSudoku;

public class ControleurSudoku implements EventHandler<ActionEvent>
{

    private Jeu modeleJeu;
    private VueSudoku vueSudoku;
    private File file;
    
    public ControleurSudoku(Jeu modeleJeu, VueSudoku vueSudoku)
    {
        this.modeleJeu = modeleJeu;
        this.vueSudoku = vueSudoku;
    }


    @Override
    public void handle(ActionEvent event)
    {
    	Button button = (Button) event.getSource();
		
    	// Bouton vérifier grille
		if (button == vueSudoku.getTableauBoutons()[0])
		{
		}
		else if (button == vueSudoku.getTableauBoutons()[1]) // bouton résoudre
		{
		}
		else if (button == vueSudoku.getTableauBoutons()[2]) // bouton abandonner
		{
			
		}
		else // bouton sauvegarder
		{
			this.file = this.vueSudoku.ouvrirGestionnaireFichier();
			
			FileWriterSudoku fileWriter = new FileWriterSudoku(this.modeleJeu);
			
			fileWriter.writeInFile(this.file.getAbsolutePath());
		}

    }

}
