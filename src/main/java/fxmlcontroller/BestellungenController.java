package fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class BestellungenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane bestellungen_anchorpane;

    @FXML
    private TableView<?> bestellungen_tableview;

    @FXML
    private TableColumn<?, ?> bestellungen_tischnr_co;

    @FXML
    private TableColumn<?, ?> bestellungen_zeitpunkt_co;

    @FXML
    void initialize() {
        assert bestellungen_anchorpane != null : "fx:id=\"bestellungen_anchorpane\" was not injected: check your FXML file 'positionen.fxml'.";
        assert bestellungen_tableview != null : "fx:id=\"bestellungen_tableview\" was not injected: check your FXML file 'positionen.fxml'.";
        assert bestellungen_tischnr_co != null : "fx:id=\"bestellungen_tischnr_co\" was not injected: check your FXML file 'positionen.fxml'.";
        assert bestellungen_zeitpunkt_co != null : "fx:id=\"bestellungen_zeitpunkt_co\" was not injected: check your FXML file 'positionen.fxml'.";

    }
}
