/**
 * Created by Laeti on 16/11/2016.
 */


package vue;

import java.awt.Checkbox;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import controleur.ControleurSudoku;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
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
    private Button[] tableauBoutons;
    private Label affichage;
    private boolean activeVueConflit;

    public VueSudoku(Jeu modeleJeu)
    {
        this.modeleJeu = modeleJeu;
        this.activeVueConflit = false;
        this.sudokuParameters = this.modeleJeu.getSudokuParameters();
        int tailleSudoku = sudokuParameters.getTailleSudoku();

        TextField valeur[][];
        valeur = new TextField[tailleSudoku][tailleSudoku]; //Si sudoku de taille 9, il y a 81 case Text
        final int[][][] estBloque = new int[1][1][1];

        this.modeleJeu.addObserver(new Observer()
                                   {
                                       @Override
                                       public void update(Observable o, Object arg)
                                       {
                                           String save;
                                           int x = 0;
                                           int y = 0;
                                           Groupe matrice[] = modeleJeu.getNombreDeLignes();
                                           int estEnConflit[][] = modeleJeu.recupLesConfilt();
                                           estBloque[0] = modeleJeu.recupTouteLesTypesDesCases();

                                           for (int i = 0; i < tailleSudoku; i++)
                                           {
                                               for (int j = 0; j < tailleSudoku; j++) {
                                                   // Si la case est de type nonBloqué, alors on remplie le tableau
                                                   if (estBloque[0][i][j] == 1)
                                                   {
                                                       valeur[i][j].setText(Integer.toString(matrice[x].getValeurCaseColonneDonnee(y++)));
                                                       if (valeur[i][j].getText().compareTo("0") == 0)
                                                           valeur[i][j].setText("");
                                                       save = valeur[i][j].getText();
                                                       if (activeVueConflit)
                                                           switch (estEnConflit[i][j]) {
                                                               case 1:
                                                                   valeur[i][j].setStyle("-fx-background-color: #f0acac;");

                                                                   break;
                                                               case 2:
                                                                   valeur[i][j].setStyle("-fx-background-color: #f06768;");
                                                                   break;
                                                               case 3:
                                                                   valeur[i][j].setStyle("-fx-background-color: #ff0026;");
                                                                   break;
                                                               default:
                                                                   valeur[i][j].setStyle("-fx-background-color: white;");
                                                                   break;
                                                           }
                                                       else
                                                           valeur[i][j].setStyle("-fx-background-color: white;");
                                                       ajoutControleur(valeur[i][j], i, j, save);
                                                   } 
                                                   else
                                                   {
                                                       valeur[i][j].setStyle("-fx-background-color: lightgrey;");
                                                       valeur[i][j].setText(Integer.toString(matrice[x].getValeurCaseColonneDonnee(y++)));
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
        
        this.controleurSudoku = new ControleurSudoku(this.modeleJeu, this);
        
        this.scene = initialisationDeLaFenetre(valeur, this.sudokuParameters);
    }

    private Scene initialisationDeLaFenetre(TextField[][] valeur, SudokuParameters sudokuParameters)
    {
        BorderPane border = new BorderPane();
        GridPane general = new GridPane();
        HBox hboxBottom;
        GridPane gridPaneTop;
        String[] sudokuRempli = null;

        int column = 0;
        int row = 0;
        
        if (sudokuParameters.getTableauStringSudokuRempli() != null)
            sudokuRempli = sudokuParameters.getTableauStringSudokuRempli(); // TODO: N'est jamais utilié -> a supprimé ?

        for (int i = 0; i < sudokuParameters.getTailleSudoku(); ++i)
        {
            for (int j = 0; j < sudokuParameters.getTailleSudoku(); j++) 
            {
                valeur[i][j] = new TextField("");
                
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

        this.tableauBoutons = new Button[4];
        
        hboxBottom = creerHboxBottom();
        gridPaneTop = creerGridPaneTop();

        border.setCenter(general);
        border.setTop(gridPaneTop);
        border.setBottom(hboxBottom);

        Scene scene = new Scene(border, Color.WHITE);
        this.modeleJeu.init(this.modeleJeu.getStr());
        
        return scene;
    }

	private GridPane creerGridPaneTop() 
	{
		GridPane gridPaneTop = new GridPane();
		this.affichage = new Label();
		Button boutonVerifier = new Button("Vérifier grille");
		//Checkbox checkBox = new Checkbox("Vérifier grille");
		ColumnConstraints colonne1 = new ColumnConstraints();
		ColumnConstraints colonne2 = new ColumnConstraints();
		
		colonne1.setPercentWidth(50);
		colonne2.setPercentWidth(50);
		gridPaneTop.getColumnConstraints().addAll(colonne1, colonne2);

		this.affichage.setText("");
		gridPaneTop.add(this.affichage, 0, 0);


        associerControleur(boutonVerifier);
        gridPaneTop.add(boutonVerifier, 1, 0);
        
        GridPane.setHalignment(this.affichage, HPos.LEFT);
        GridPane.setHalignment(boutonVerifier, HPos.RIGHT);
        
        return gridPaneTop;
	}

	private HBox creerHboxBottom() 
	{
		HBox hbox = new HBox();
		Button boutonResoudre = new Button("Résoudre");
		
		Button boutonSauvegarder = new Button("Sauvegarder");
		Button boutonAbandonner = new Button("Abandonner/Quitter");
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);

		associerControleur(boutonResoudre);
        hbox.getChildren().add(boutonResoudre);
        
        associerControleur(boutonSauvegarder);
        hbox.getChildren().add(boutonSauvegarder);
        
        associerControleur(boutonAbandonner);
        hbox.getChildren().add(boutonAbandonner);
        
		return hbox;
	}

    private void ajoutControleur(TextField valeur, final int posx, final int posy,String save)
    {
        valeur.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                boolean erreur = false;
                int number = 0;
                try
                {
                    number = Integer.parseInt(valeur.getText());
                }
                catch (NumberFormatException e)
                {
                    erreur = true;
                    System.out.print(save);
                    valeur.setText(save);
                    affichage.setStyle("-fx-text-inner-color: red"); // TODO: NE marche pas.
                    affichage.setText("Entrez un chiffre entre 1 et 9.");
                }
                if (number > sudokuParameters.getTailleSudoku() || number < 0 && !erreur)
                {
                    affichage.setText("Valeur entre 1 et 9 inclus.");
                    valeur.setText(save);
                } else
                {
                    affichage.setText("");
                    modeleJeu.changeValeurCase(number, posx, posy);
                }
            }
        });
    }

	private void associerControleur(Button button)
    {
        //TODO : Je ne comprend pas pourquoi tu fait un talbeau.length - 1. Le tableau est toujours à 4 donc tu remplace toujours le dernier.
        int i = 0;
        while (tableauBoutons[i] != null) {
            ++i;
        }
        this.tableauBoutons[i] = button;

        button.setOnAction(this.controleurSudoku);
    }

    public boolean isActiveVueConflit() 
    {
        return activeVueConflit;
    }

    public void setActiveVueConflit(boolean activeVueConflit) 
    {
        this.activeVueConflit = activeVueConflit;
        if (this.activeVueConflit)
        	this.tableauBoutons[3].setStyle("-fx-background-color: linear-gradient(to bottom, #b4e391 0%,#61c419 50%,#b4e391 100%); -fx-text-fill: #FFFFFF");
        else
        	this.tableauBoutons[3].setStyle(null);
    }

    public Scene getScene()
    {
        return this.scene;
    }
    
    public Button[] getTableauBoutons()
	{
		return tableauBoutons;
	}
    
	public File ouvrirGestionnaireFichier()
	{
		FileChooser fileChooser	= new FileChooser();
		fileChooser.setTitle("Entrer un nom de fichier");
		File homeDir = new File(System.getProperty("user.home"));
		fileChooser.setInitialDirectory(homeDir);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(this.getScene().getWindow());
		
		return file;
	}



}
