package fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ProduktController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane produkte_anchorpane;

    @FXML
    private TableView<?> produkte_tableview;

    @FXML
    private TableColumn<?, ?> produkte_nummer_col;

    @FXML
    private TableColumn<?, ?> produkte_name_col;

    @FXML
    private TableColumn<?, ?> produkte_kategorie_co;

    @FXML
    private TableColumn<?, ?> produkte_preis_co;

    @FXML
    void initialize() {
        assert produkte_anchorpane != null : "fx:id=\"produkte_anchorpane\" was not injected: check your FXML file 'produkte.fxml'.";
        assert produkte_tableview != null : "fx:id=\"produkte_tableview\" was not injected: check your FXML file 'produkte.fxml'.";
        assert produkte_nummer_col != null : "fx:id=\"produkte_nummer_col\" was not injected: check your FXML file 'produkte.fxml'.";
        assert produkte_name_col != null : "fx:id=\"produkte_name_col\" was not injected: check your FXML file 'produkte.fxml'.";
        assert produkte_kategorie_co != null : "fx:id=\"produkte_kategorie_co\" was not injected: check your FXML file 'produkte.fxml'.";
        assert produkte_preis_co != null : "fx:id=\"produkte_preis_co\" was not injected: check your FXML file 'produkte.fxml'.";

    }
}
