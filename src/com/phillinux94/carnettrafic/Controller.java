package com.phillinux94.carnettrafic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private Pane selBtnCarnetTrafic;

    @FXML
    private Pane selBtnCalculLocator;

    @FXML
    private Pane selBtnCalculDistance;

    @FXML
    private TitledPane paneCarnetTrafic;

    @FXML
    private TitledPane paneCalculLocator;

    @FXML
    private TitledPane paneCalculDistance;

    // Coordonnées de la station
    @FXML
    private TextField fldIndicatifStation;

    @FXML
    private TextField fldLatitudeDegres;

    @FXML
    private TextField fldLatitudeMinutes;

    @FXML
    private TextField fldLatitudeSecondes;

    @FXML
    private TextField fldLongitudeDegres;

    @FXML
    private TextField fldLongitudeMinutes;

    @FXML
    private TextField fldLongitudeSecondes;

    @FXML
    private TextField fldLocatorStation;



    @FXML
    private void setCarnetTrafic(){

        selBtnCarnetTrafic.setVisible(true);
        selBtnCalculLocator.setVisible(false);
        selBtnCalculDistance.setVisible(false);

        paneCarnetTrafic.setVisible(true);
        paneCalculLocator.setVisible(false);
        paneCalculDistance.setVisible(false);

    }

    @FXML
    private void setCalculLocator(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(true);
        selBtnCalculDistance.setVisible(false);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(true);
        paneCalculDistance.setVisible(false);

    }

    @FXML
    private void setCalculDistance(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(false);
        selBtnCalculDistance.setVisible(true);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(false);
        paneCalculDistance.setVisible(true);

    }

    @FXML
    private void calculLocatorLocal(){

        fldLocatorStation.setText("");
    }
}
