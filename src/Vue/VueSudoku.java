package Vue;/**
 * Created by Laeti on 16/11/2016.
 */

import Modele.Jeu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

        System.out.println("test");
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        int column = 0;
        int row = 0;

        for (int i = 0; i < 81; ++i)
        {

            final Text t = new Text(Integer.toString(this.modeleJeu.getValue(row, column)));
            if ()
                t.setWrappingWidth(40);
            t.setFont(Font.font("Verdana", 20));
            t.setTextAlignment(TextAlignment.CENTER);
            gridPane.add(t, column++, row);
            if (column > 8)
            {
                column = 0;
                row++;
            }
        }
        gridPane.setGridLinesVisible(true);

        border.setCenter(gridPane);
        Scene scene = new Scene(border, Color.WHITE);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
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
