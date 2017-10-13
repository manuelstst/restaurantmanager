package fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class RechnungenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rechnungen_anchorpane;

    @FXML
    private TableView<?> rechnungen_tableview;

    @FXML
    private TableColumn<?, ?> rechnungen_tischnr_co;

    @FXML
    private TableColumn<?, ?> rechnungen_zeitpunkt_co;

    @FXML
    void initialize() {
        assert rechnungen_anchorpane != null : "fx:id=\"rechnungen_anchorpane\" was not injected: check your FXML file 'Rechnungen.fxml'.";
        assert rechnungen_tableview != null : "fx:id=\"rechnungen_tableview\" was not injected: check your FXML file 'Rechnungen.fxml'.";
        assert rechnungen_tischnr_co != null : "fx:id=\"rechnungen_tischnr_co\" was not injected: check your FXML file 'Rechnungen.fxml'.";
        assert rechnungen_zeitpunkt_co != null : "fx:id=\"rechnungen_zeitpunkt_co\" was not injected: check your FXML file 'Rechnungen.fxml'.";

    }
}
