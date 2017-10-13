package fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class StandardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane standard_anchorpane;

    @FXML
    private MenuBar standard_menubar;

    @FXML
    private Menu standard_datei_menuitem;

    @FXML
    private MenuItem standard_datei_einstellungen_menuitem;

    @FXML
    private Menu standard_sichern_menuitem;

    @FXML
    private MenuItem standard_sichern_backuperstellen_menuitem;

    @FXML
    private Menu standard_hilfe_menuitem;

    @FXML
    private MenuItem standard_hilfe_hilfeanzeigen_menuitem;

    @FXML
    private Menu standard_info_menuitem;

    @FXML
    private MenuItem standard_info_ueberkms_menuitem;

    @FXML
    private SplitPane standard_splitpane;

    @FXML
    private AnchorPane standard_anchorpane_left;

    @FXML
    private Button standard_statistiken_button;

    @FXML
    private Button standard_home_button;

    @FXML
    private Button standard_bestellungen_button;

    @FXML
    private Button standard_rechnungen_button;

    @FXML
    private Button standard_produkte_button;

    @FXML
    private AnchorPane standard_anchorpane_right;
    
    private final Logger logger = Logger.getLogger(getClass());
    private AnchorPane statistikPane;
    private AnchorPane produktPane;
    private AnchorPane bestellungenPane;
    private AnchorPane rechnungenPane;
    private ProduktController produktcontroller;
    private BestellungenController bestellungencontroller;
    private RechnungenController rechnungencontroller;

    @FXML
    void handleBestellungenButtonAction(ActionEvent event) {
        FXMLLoader bestellungenloader;
        try {          
            bestellungenloader = new FXMLLoader(getClass().getResource("/fxml/bestellungen.fxml"));
            bestellungencontroller = bestellungenloader.<BestellungenController>getController();
            bestellungenPane = bestellungenloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(bestellungenPane));
            //Used for updating the HomeScreen
            bestellungenPane.prefWidthProperty().bind(standard_anchorpane_right.widthProperty());
            bestellungenPane.prefHeightProperty().bind(standard_anchorpane_right.heightProperty());
            standard_anchorpane_right.getChildren().add(bestellungenPane);
        } catch (IOException ex) {
            logger.error("Konnte Home nicht laden", ex);
        }
    }

    @FXML
    void handleHomeButtonAction(ActionEvent event) {
           //ToDO
    }

    @FXML
    void handleKMSInfoMenuItemAction(ActionEvent event) {
          //ToDO
    }

    @FXML
    void handleProdukteButtonAction(ActionEvent event) {
        FXMLLoader produktloader;
        try {          
            produktloader = new FXMLLoader(getClass().getResource("/fxml/produkte.fxml"));
            produktcontroller = produktloader.<ProduktController>getController();
            produktPane = produktloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(produktPane));
            //Used for updating the HomeScreen
            produktPane.prefWidthProperty().bind(standard_anchorpane_right.widthProperty());
            produktPane.prefHeightProperty().bind(standard_anchorpane_right.heightProperty());
            standard_anchorpane_right.getChildren().add(produktPane);
        } catch (IOException ex) {
            logger.error("Konnte Home nicht laden", ex);
        }
    }

    @FXML
    void handleRechnungenButtonAction(ActionEvent event) {
        /*
        logger.debug("Rechnung Button wurde gedrückt!");
        FXMLLoader rechnungsloader = null;
        try {
            rechnungsloader  = new FXMLLoader(getClass().getResource("/fxml/rechnung.fxml"));
            
            Stage stage = new Stage();
            stage.setResizable(true); // Für Markus Meierhofer DEBUG
            stage.setScene(new Scene((Parent) rechnungsloader.load()));
            stage.setTitle("Rechnung");
            stage.show();
            
            RechnungController controller = rechnungsloader.<RechnungController>getController();
           // controller.initLoader(rechnungsloader);
            logger.debug("Rechnung wurde geladen!");
        } catch (IOException ex) {
            logger.error("Konnte nicht laden", ex);
        }*/
        FXMLLoader rechnungenloader;
        try {          
            rechnungenloader = new FXMLLoader(getClass().getResource("/fxml/rechnungen.fxml"));
            rechnungencontroller = rechnungenloader.<RechnungenController>getController();
            rechnungenPane = rechnungenloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(rechnungenPane));
            //Used for updating the HomeScreen
            rechnungenPane.prefWidthProperty().bind(standard_anchorpane_right.widthProperty());
            rechnungenPane.prefHeightProperty().bind(standard_anchorpane_right.heightProperty());
            standard_anchorpane_right.getChildren().add(rechnungenPane);
        } catch (IOException ex) {
            logger.error("Konnte Home nicht laden", ex);
        }
    }

    @FXML
    void handleStatistikenButtonAction(ActionEvent event) {
        logger.debug("Statistiken Button wurde gedrückt!");
        try {
            if (statistikPane == null) {
                statistikPane = FXMLLoader.load(getClass().getResource("/fxml/statistiken.fxml"));
                statistikPane.prefWidthProperty().bind(standard_anchorpane_right.widthProperty());
                statistikPane.prefHeightProperty().bind(standard_anchorpane_right.heightProperty());
            }
            standard_anchorpane_right.getChildren().clear();
            standard_anchorpane_right.getChildren().add(statistikPane);
        } catch (IOException ex) {
            logger.error("Konnte Statistiken nicht laden", ex);
        }
    }

    @FXML
    void onCreateBackup(ActionEvent event) {
         //ToDO.
    }

    @FXML
    void onEinstellungenClick(ActionEvent event) {
        //ToDo.
    }

    @FXML
    void initialize() {
       
    }
}
