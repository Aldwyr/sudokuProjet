package controleur;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modele.FileReader;

import modele.Jeu;
import vue.VueMenu;
import vue.VueSudoku;

/**
 * Created by Laeti on 16/11/2016.
 */
public class ControleurAccueil implements EventHandler<ActionEvent>
{
	private Jeu modeleJeu;
	private VueMenu vueMenu;
	private File file;
	private VueSudoku vueSudoku;
	
	public ControleurAccueil(Jeu modeleJeu, VueMenu vueMenu)
	{
		this.modeleJeu = modeleJeu;
		this.vueMenu = vueMenu;
	}
	
	public void	handle(ActionEvent event) 
	{
		// On regarde sur quel bouton on a cliqu� depuis l'accueil
		Button button = (Button) event.getSource();
		
		if (button == vueMenu.getBouton1())
		{
			this.file = this.vueMenu.ouvrirGestionnaireFichier();
			this.modeleJeu.creerNouveauSudoku(file.getAbsolutePath());
			
			this.vueSudoku = new VueSudoku(this.modeleJeu);
			ControleurSudoku controleurSudoku = new ControleurSudoku(this.modeleJeu, this.vueSudoku);
			
			Stage stage = new Stage();
			stage.setTitle("Nouveau Sudoku");
	        stage.setScene(vueSudoku.getScene());
	        stage.show();
		}
        else if (button == vueMenu.getBouton2())
		{
        	this.file = this.vueMenu.ouvrirGestionnaireFichier();
			this.modeleJeu.chargerSudokuCommence(file.getAbsolutePath());
			
			this.vueSudoku = new VueSudoku(this.modeleJeu);
			ControleurSudoku controleurSudoku = new ControleurSudoku(this.modeleJeu, this.vueSudoku);

			this.modeleJeu.miseAJourValeurCases();
			
			Stage stage = new Stage();
			stage.setTitle("Sudoku commenc�");
	        stage.setScene(vueSudoku.getScene());
	        stage.show();
		}
	}
}
