package com.phillinux94.carnettrafic;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private Pane selBtnCarnetTrafic;

    @FXML
    private Pane selBtnCalculLocator;

    @FXML
    private Pane selBtnCalculDistance;

    @FXML
    private void setCarnetTrafic(){

        selBtnCarnetTrafic.setVisible(true);
        selBtnCalculLocator.setVisible(false);
        selBtnCalculDistance.setVisible(false);

    }

    @FXML
    private void setCalculLocator(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(true);
        selBtnCalculDistance.setVisible(false);

    }

    @FXML
    private void setCalculDistance(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(false);
        selBtnCalculDistance.setVisible(true);

    }
}
