package main;

import controleur.ControleurAccueil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import modele.Jeu;
import vue.VueMenu;
import vue.VueSudoku;

public class SudokuLauncher extends Application
{
	
	 public static void main(String[] args) 
	 {
	        Application.launch(args);
	 }

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Jeu modeleJeu = new Jeu();
		VueMenu vueMenu = new VueMenu();
		ControleurAccueil controleur = new ControleurAccueil(modeleJeu, vueMenu);
		
		
        primaryStage.setTitle("Menu Sudoku");
        primaryStage.setScene(vueMenu.getScene());
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
		
	}

}
