import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private CheckBox displayCarnet;
    @FXML
    private TextField textSeparator;
    @FXML
    private TextField nbPages;
    @FXML
    private TextField nbFeuilles;
    @FXML
    private Label labelNbPages;
    @FXML
    private Label labelNbCarnets;
    @FXML
    private Label labelNbFeuillesCarnet;
    @FXML
    private Label labelNbPagesCarnet;
    @FXML
    private Label labelNbPagesTotal;
    @FXML
    private Label labelNbFeuillesTotal;
    @FXML
    private TextArea resultat;

    @FXML
    void handleSend(ActionEvent event)
    {
        int nbPagesInt = 0;
        int nbFeuillesInt = 0;
        this.resultat.setWrapText(true);
        this.nbPages.setStyle("-fx-background-color: #ffffff");
        this.nbFeuilles.setStyle("-fx-background-color: #ffffff");

        if ((!isInteger(this.nbPages.getText())) || (!isInteger(this.nbFeuilles.getText()))) {
            if (!isInteger(this.nbPages.getText())) {
                this.nbPages.setStyle("-fx-background-color: #ffefa6");
            }
            if (!isInteger(this.nbFeuilles.getText())) {
                this.nbFeuilles.setStyle("-fx-background-color: #ffefa6");
            }
        }
        else {
            nbPagesInt = Integer.parseInt(this.nbPages.getText());
            nbFeuillesInt = Integer.parseInt(this.nbFeuilles.getText());

            if ((nbFeuillesInt <= 0) || (nbPagesInt <= 0))
            {
                this.resultat.setText("Erreur : Le nombre de feuilles par carnet que vous avez entré est trop grand. (Il dépasse le nombre de pages)");
                this.nbPages.setStyle("-fx-background-color: #ffefa6");
                this.nbFeuilles.setStyle("-fx-background-color: #ffefa6");

            }
            else if ((nbFeuillesInt * 4 > nbPagesInt) || (nbFeuillesInt <= 0) || (nbPagesInt <= 0))
            {
                this.resultat.setText("Erreur : Le nombre de feuille par carnet que vous avez entré est trop grand. (Il dépasse le nombre de pages)");
                this.nbFeuilles.setStyle("-fx-background-color: #ffefa6");
            }
            else if (nbPagesInt >= 30000) {
                this.resultat.setText("Erreur : Artamène ou le Grand Cyrus écrit par  Madeleine et Georges de Scudéry fait 13 095pages et n'est plus publié en raison de sa longueur, il est considéré comme le roman français le plus long ! La limite du logiciel ici est de 30 000pages, c'est déjà beaucoup !");

                this.nbPages.setStyle("-fx-background-color: #ffefa6");
            }
            else
            {
                Document livre = new Document(nbPagesInt, nbFeuillesInt);
                livre.create();
                livre.displayCarnetsConsole();
                livre.displayDebugConsole();

                this.labelNbPages.setText(Integer.toString(livre.getPagesParCarnet()));
                this.labelNbCarnets.setText(Integer.toString(livre.getNbCarnets()));
                this.labelNbFeuillesCarnet.setText(Integer.toString(livre.getCarnetFeuillesRestant()));
                this.labelNbPagesCarnet.setText(Integer.toString(livre.getCarnetRestant()));
                this.labelNbPagesTotal.setText(Integer.toString(livre.getVraiPagesTotal()));
                this.labelNbFeuillesTotal.setText(Integer.toString(livre.getVraiFeuillesTotal()));

                this.resultat.setText(livre.stringParser(this.textSeparator.getText(), this.displayCarnet.isSelected()));
            }
        }
    }

    private boolean isInteger(String s) {
        boolean isValid = true;
        try { Integer.parseInt(s);
        } catch (NumberFormatException nfe) { isValid = false; }
        return isValid;
    }
}