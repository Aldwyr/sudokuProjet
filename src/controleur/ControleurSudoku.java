package controleur;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import modele.FileWriterSudoku;
import modele.Jeu;
import vue.VueSudoku;

public class ControleurSudoku implements EventHandler<ActionEvent>
{

	private Jeu modeleJeu;
	private VueSudoku vueSudoku;
	private File file;

	public ControleurSudoku(Jeu modeleJeu, VueSudoku vueSudoku) {
		this.modeleJeu = modeleJeu;
		this.vueSudoku = vueSudoku;
	}


	@Override
	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();

		// Bouton résoudre
		if (button == vueSudoku.getTableauBoutons()[0])
		{
		}
		else if (button == vueSudoku.getTableauBoutons()[1]) // bouton sauvegarder
		{
			this.file = this.vueSudoku.ouvrirGestionnaireFichier();

			FileWriterSudoku fileWriter = new FileWriterSudoku(this.modeleJeu);

			fileWriter.writeInFile(this.file.getAbsolutePath());
		}
		else if (button == vueSudoku.getTableauBoutons()[2]) // bouton abandonner
		{
			Alert boxAlert = new Alert(AlertType.CONFIRMATION);
			boxAlert.setTitle("Abandonner le sudoku ?");
			boxAlert.setHeaderText("Êtes-vous sûr de vouloir abandonner ?");
			
			ButtonType oui = new ButtonType("Oui");
			ButtonType non = new ButtonType("Non");
			boxAlert.getButtonTypes().setAll(oui, non);
			
			Optional<ButtonType> choice	= boxAlert.showAndWait();
			
			if (choice.get() == oui)
				System.exit(0);
			else
				boxAlert.close();

		} 
		else if (button == vueSudoku.getTableauBoutons()[3]) // bouton vérifier grille
		{
			if (this.vueSudoku.isActiveVueConflit())
				this.vueSudoku.setActiveVueConflit(false);
			else
				this.vueSudoku.setActiveVueConflit(true);
			this.modeleJeu.notifierLaVue();
		}

	}

}
