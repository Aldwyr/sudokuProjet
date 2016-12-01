package Vue;/**
 * Created by Laeti on 16/11/2016.
 */

import Modele.Groupe;
import Modele.Jeu;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VueSudoku extends Application
{

    private Jeu modeleJeu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.modeleJeu = new Jeu();

        BorderPane border = new BorderPane();
        GridPane general = new GridPane();

        Text valeur[];
        valeur = new Text[81];
        int column = 0;
        int row = 0;
        this.modeleJeu.addObserver(new Observer()
                                   {
                                       @Override
                                       public void update(Observable o, Object arg)
                                       {
                                           int x = 0;
                                           int y = 0;
                                           Groupe matrice[] = modeleJeu.getValue();
                                           for (int i = 0; i < 81; i++)
                                           {
                                               valeur[i].setText(Integer.toString(matrice[x].getCaseValueFromLine(y++)));
                                               if (valeur[i].getText().compareTo("0") == 0)
                                               {
                                                   valeur[i].setText("");
                                               }
                                               if (y > 8)
                                               {
                                                   y = 0;
                                                   x++;
                                               }
                                           }

                                       }
                                   }

        );
        for (int i = 0; i < 81; ++i)
        {

            valeur[i] = new Text();

            valeur[i].setWrappingWidth(40);
            valeur[i].setFont(Font.font("Verdana", 20));
            valeur[i].setTextAlignment(TextAlignment.CENTER);
            general.add(valeur[i], column++, row);
            if (column > 8)
            {
                column = 0;
                row++;
            }
        }

        general.setGridLinesVisible(true);

        border.setCenter(general);
        Scene scene = new Scene(border, Color.WHITE);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
        modeleJeu.init(modeleJeu.getStr());
    }

}
