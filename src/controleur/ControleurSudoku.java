package controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.Jeu;
import vue.VueSudoku;

public class ControleurSudoku implements EventHandler<ActionEvent>
{

	private Jeu modeleJeu;
	private VueSudoku vueSudoku;

	public ControleurSudoku(Jeu modeleJeu, VueSudoku vueSudoku)
	{
		this.modeleJeu = modeleJeu;
		this.vueSudoku = vueSudoku;
	}

	@Override
	public void handle(ActionEvent event)
	{
		// TODO: distinction entre les boutons cliqu�s
		// TODO: remplir fonction  (appuie sur le bouton sauvegarder
		System.out.println("A impl�menter");
	}



}
