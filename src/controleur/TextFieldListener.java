package controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import modele.Jeu;

/**
 * Created by gregorygueux on 07/12/2016.
 */
public class TextFieldListener implements ChangeListener<Boolean>
{

    private final TextField textField;
    private Jeu modeleJeu;
    private String sauvegarde;
    private int x;
    private int y;

    public TextFieldListener(TextField textField, Jeu modeleJeu, int x, int y)
    {
        this.modeleJeu = modeleJeu;
        this.textField = textField;
        System.out.println(textField.getText());
        this.x = x;
        this.y = y;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    {
        if (!newValue)
        {
            boolean erreur = false;
            int number = 0;
            try
            {
                number = Integer.parseInt(this.textField.getText());
            }
            catch (NumberFormatException e)
            {
                erreur = true;
                this.textField.setText(sauvegarde);
            }
            if (number > this.modeleJeu.getSudokuParameters().getTailleSudoku() || number < 0 && !erreur)
            {
                this.textField.setText(sauvegarde);
            } else if (number !=0)
            {
                this.sauvegarde = Integer.toString(number);
                modeleJeu.changeValeurCase(number, x, y);
            } else
                this.textField.setText(sauvegarde);
        }
    }
}
