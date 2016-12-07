package vue;

import java.io.File;

import controleur.ControleurAccueil;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import modele.Jeu;

public class VueMenu
{
	private Scene scene;
	private Jeu modeleJeu;
	private ControleurAccueil controleur;
	private Button[] button;
	
	public VueMenu()
	{
		this.modeleJeu = new Jeu();
	}
	
	public Scene getScene()
	{
		this.scene = new Scene((Parent) addVBox(), Color.WHITE);
		
		return this.scene;
	}

	private Node addVBox()
	{
		VBox vBox = new VBox(40);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPrefSize(250, 200);

		Text text = new Text("Veuillez choisir une option...");
		text.setStyle("-fx-font-weight: bold");
		
		vBox.getChildren().add(text);
		
	    button = new Button[2];
	    
	    button[0] = new Button("Ouvrir un nouveau Sudoku");
	    createControleur(button[0]);
	    vBox.getChildren().add(button[0]);
	    
	    button[1] = new Button("Ouvrir un sudoku déjà commencé");
	    createControleur(button[1]);
	    vBox.getChildren().add(button[1]);
	    
	    return vBox;
	}

	private void createControleur(Button button)
	{
		this.controleur = new ControleurAccueil(this.modeleJeu, this);
		button.setOnAction(controleur);		
	}
	
	public Button getBouton1()
	{
		return this.button[0];
	}
	
	public Button getBouton2()
	{
		return this.button[1];
	}

	public File ouvrirGestionnaireFichier()
	{
		FileChooser fileChooser	= new FileChooser();
		fileChooser.setTitle("FileChooserExample");
		File homeDir = new File(System.getProperty("user.home"));
		fileChooser.setInitialDirectory(homeDir);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(this.getScene().getWindow());
		
		return file;
	}

}
