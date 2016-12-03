package vue;/**
 * Created by Laeti on 16/11/2016.
 */

import java.awt.Button;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import controleur.ControleurAccueil;
import controleur.ControleurSudoku;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.Groupe;
import modele.Jeu;
import modele.SudokuParameters;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VueSudoku
{

    private Jeu modeleJeu;
    private ControleurSudoku controleurSudoku;
    private Scene scene;
    private SudokuParameters sudokuParameters;
    
    public VueSudoku(Jeu modeleJeu)
	{    	
    	this.modeleJeu = modeleJeu;

//      ArrayList<Text> listbloquant;
//      ArrayList<TextField> listNonBloquant;
      
      this.sudokuParameters = modeleJeu.getSudokuParameters();
      int tailleSudoku = sudokuParameters.getTailleSudoku();
      
      Text valeur[];
      valeur = new Text[tailleSudoku*tailleSudoku]; //Si sudoku de taille 9, il y a 81 case Text
      
      this.modeleJeu.addObserver(new Observer()
                                 {
                                     @Override
                                     public void update(Observable o, Object arg)
                                     {
                                         int x = 0;
                                         int y = 0;
                                         Groupe matrice[] = modeleJeu.getNombreDeLignes();
                                         //int estBloque[];
/*
                                         estBloque = modeleJeu.
*/
                                         
                                         for (int i = 0; i < tailleSudoku * tailleSudoku; i++)
                                         {
                                             valeur[i].setText(Integer.toString(matrice[x].getCaseValueFromLine(y++)));
                                             if (valeur[i].getText().compareTo("0") == 0)
                                             {
                                                 valeur[i].setText("");
                                             }
                                             if (y > tailleSudoku - 1)
                                             {
                                                 y = 0;
                                                 x++;
                                             }
                                         }

                                     }
                                 }
      );
      
      
      scene = initialisationDeLaFenetre(valeur, sudokuParameters);
	}

	public Scene initialisationDeLaFenetre(Text[] valeur, SudokuParameters sudokuParameters)
	{
		
		BorderPane border = new BorderPane();
        GridPane general = new GridPane();
        Button boutonParcourir = new Button("Parcourir...");
        
        int column = 0;
        int row = 0;
        
		for (int i = 0; i < sudokuParameters.getTailleSudoku() * sudokuParameters.getTailleSudoku(); ++i)
        {
	        
            valeur[i] = new Text();

            valeur[i].setWrappingWidth(40);
            valeur[i].setFont(Font.font("Verdana", 20));
            valeur[i].setTextAlignment(TextAlignment.CENTER);
            
            general.add(valeur[i], column++, row);
            
            if (column >= sudokuParameters.getTailleSudoku())
            {
                column = 0;
                row++;
            }
        }

		general.setGridLinesVisible(true);

        border.setCenter(general);
        
        Scene scene = new Scene(border, Color.WHITE);
        modeleJeu.init(modeleJeu.getStr());
        
        return scene;
	}
	
	public Scene getScene()
	{
		return scene;
	}
	

}
