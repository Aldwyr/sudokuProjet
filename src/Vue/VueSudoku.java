package Vue;/**
 * Created by Laeti on 16/11/2016.
 */

import Modele.Jeu;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class VueSudoku extends Application implements Observer
{

    Jeu modeleJeu;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.modeleJeu = new Jeu();

        GridPane gridPane = new GridPane();


        this.modeleJeu.addObserver(new Observer()
        {
            @Override
            public void update(Observable o, Object arg)
            {

            }
        });

    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
