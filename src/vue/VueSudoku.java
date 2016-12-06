package vue;/**
 * Created by Laeti on 16/11/2016.
 */

//import java.awt.Button;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controleur.ControleurSudoku;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import modele.Groupe;
import modele.Jeu;
import modele.SudokuParameters;

public class VueSudoku
{

    private Jeu modeleJeu;
    private ControleurSudoku controleurSudoku;
    private Scene scene;
    private SudokuParameters sudokuParameters;

    public VueSudoku(Jeu modeleJeu)
    {
        this.modeleJeu = modeleJeu;

        this.sudokuParameters = modeleJeu.getSudokuParameters();
        int tailleSudoku = sudokuParameters.getTailleSudoku();

        TextField valeur[][];
        valeur = new TextField[tailleSudoku][tailleSudoku]; //Si sudoku de taille 9, il y a 81 case Text
        final int[][][] estBloque = new int[1][1][1];

        this.modeleJeu.addObserver(new Observer()
                                   {
                                       @Override
                                       public void update(Observable o, Object arg)
                                       {
                                           int x = 0;
                                           int y = 0;
                                           Groupe matrice[] = modeleJeu.getNombreDeLignes();

                                           estBloque[0] = modeleJeu.recupTouteLesTypesDesCases();

                                           for (int i = 0; i < tailleSudoku; i++)
                                           {
                                               for (int j = 0; j < tailleSudoku; j++) {
                                                   // Si la case est de type nonBloqué, alors on remplie le tableau
                                                   if (estBloque[0][i][j] == 1)
                                                   {
                                                       valeur[i][j].setText(Integer.toString(matrice[x].getCaseValueFromLine(y++)));
                                                       if (valeur[i][j].getText().compareTo("0") == 0)
                                                           valeur[i][j].setText("");
                                                       ajoutControlleur(valeur[i][j], i, j);
                                                   } else
                                                   {

                                                       valeur[i][j].setStyle("-fx-background-color: lightgrey;");
                                                       valeur[i][j].setText(Integer.toString(matrice[x].getCaseValueFromLine(y++)));
                                                       valeur[i][j].setEditable(false);
                                                   }
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

    public Scene initialisationDeLaFenetre(TextField[][] valeur, SudokuParameters sudokuParameters)
    {
        BorderPane border = new BorderPane();
        GridPane general = new GridPane();
        HBox hbox = new HBox();
        Button boutonSauvegarder = new Button("Sauvegarder");
        String[] sudokuRempli = null;

        int column = 0;
        int row = 0;

//        System.out.println(sudokuParameters.getTableauStringSudokuRempli()[0]);

        if (sudokuParameters.getTableauStringSudokuRempli() != null)
            sudokuRempli = sudokuParameters.getTableauStringSudokuRempli();

        for (int i = 0; i < sudokuParameters.getTailleSudoku(); ++i)
        {
            for (int j = 0; j < sudokuParameters.getTailleSudoku(); j++)
            {
                valeur[i][j] = new TextField("0");

                if (sudokuParameters.getTableauStringSudokuRempli() != null)
                {
                    String caractereAMettreDansLaCase;

                    System.out.println(String.valueOf(sudokuRempli[i]));
                    caractereAMettreDansLaCase = String.valueOf(sudokuRempli[i].charAt(j));


                    if (caractereAMettreDansLaCase != "0")
                        valeur[i][j].setText(caractereAMettreDansLaCase);
                }


                valeur[i][j].setMaxWidth(40);
                valeur[i][j].setFont(Font.font("Verdana", 20));

                general.add(valeur[i][j], column++, row);
            }

            if (column >= sudokuParameters.getTailleSudoku())
            {
                column = 0;
                row++;
            }
        }

        general.setGridLinesVisible(true);

        hbox.setAlignment(Pos.CENTER);
        createControleur(boutonSauvegarder);
        hbox.getChildren().add(boutonSauvegarder);

        border.setCenter(general);
        border.setBottom(hbox);

        Scene scene = new Scene(border, Color.WHITE);
        modeleJeu.init(modeleJeu.getStr());

        return scene;
    }

    private void ajoutControlleur(TextField valeur, final int posx, final int posy)
    {
        valeur.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                int number = 0;
                try
                {
                    number = Integer.parseInt(valeur.getText());
                }
                catch (NumberFormatException e)
                {
                    valeur.setText("");
                }
                if (number > sudokuParameters.getTailleSudoku() || number < 1)
                {
                    valeur.setText("");
                } else
                {
                    modeleJeu.changeValeurCase(number, posx, posy);
                }
            }
        });
    }
    private String recupererValeurRentreePrecedemment()
    {

        return null;
    }

    private void createControleur(Button button)
    {
        this.controleurSudoku = new ControleurSudoku(this.modeleJeu, this);
        button.setOnAction(this.controleurSudoku);
    }

    public Scene getScene()
    {
        return scene;
    }


}
