package com.phillinux94.carnettrafic;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnCarnetTrafic;

    @FXML
    private Button btnCalculCoordonnees;

    @FXML
    private Button btnStatistiques;

    @FXML
    private Pane paneStation;

    @FXML
    private TitledPane paneCarnetTrafic;

    @FXML
    private TitledPane paneCalculLocator;

    @FXML
    private TitledPane paneCalculStatistiques;

    // Coordonnées de la station
    @FXML
    private TextField fldIndicatifStation;

    @FXML
    private TextField fldLocaliteStation;

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
    private ChoiceBox comboLatitude;

    @FXML
    private ChoiceBox comboLongitude;

    @FXML
    private Label labStatutSaisie;

    // Carnet de trafic
    @FXML
    private Pane paneAjoutQso;

    @FXML
    private DatePicker fldDateQso;

    @FXML
    private TextField fldIndicatifQso;

    @FXML
    private TextField fldDepartementQso;

    @FXML
    private TextField fldLocatorQso;

    @FXML
    private ComboBox comboBandeQso;

    @FXML
    private ComboBox comboModeQso;

    @FXML
    private TextField fldQthQso;

    @FXML
    private TextField fldPrenomQso;

    @FXML
    private TextField fldRstReception;

    @FXML
    private TextField fldRstEmission;

    @FXML
    private TableView listeCarnetTrafic;

    @FXML
    private TableColumn<QSO, String> tableId;

    @FXML
    private TableColumn<QSO, String> tableDate;

    @FXML
    private TableColumn<QSO, String> tableIndicatif;

    @FXML
    private TableColumn<QSO, String> tableDepartement;

    @FXML
    private TableColumn<QSO, String> tableLocator;

    @FXML
    private TableColumn<QSO, String> tableBande;

    @FXML
    private TableColumn<QSO, String> tableMode;

    @FXML
    private TableColumn<QSO, String> tableQth;

    @FXML
    private TableColumn<QSO, String> tableRst_r;

    @FXML
    private TableColumn<QSO, String> tableRst_e;

    @FXML
    private TableColumn<QSO, String> tableDistance;

    @FXML
    private ChoiceBox comboListeDebut;

    @FXML
    private ChoiceBox comboListeFin;

    // Calcul des locator
    @FXML
    private TextField fldCalLatitudeDegres;

    @FXML
    private TextField fldCalLatitudeMinutes;

    @FXML
    private TextField fldCalLatitudeSecondes;

    @FXML
    private TextField fldCalLongitudeDegres;

    @FXML
    private TextField fldCalLongitudeMinutes;

    @FXML
    private TextField fldCalLongitudeSecondes;

    @FXML
    private TextField fldCalLocator;

    @FXML
    private TextField fldCalDistance;

    @FXML
    private ChoiceBox comboCalLatitude;

    @FXML
    private ChoiceBox comboCalLongitude;

    @FXML
    private TextField fldLocatorDistant;

    @FXML
    private TextField fldDistanceLocator;

    // Statistiques
    @FXML
    private TextField fldStatNbQso;

    @FXML
    private PieChart chart1;

    @FXML
    private LineChart<String, Number> chart2;

    @FXML
    private ChoiceBox comboStatDebut;

    @FXML
    private ChoiceBox comboStatFin;



    @FXML
    private void setCarnetTrafic(){

        btnCarnetTrafic.setOpacity(0.5);
        btnCalculCoordonnees.setOpacity(0);
        btnStatistiques.setOpacity(0);

        paneCarnetTrafic.setVisible(true);
        paneCalculLocator.setVisible(false);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculLocator(){

        btnCarnetTrafic.setOpacity(0);
        btnCalculCoordonnees.setOpacity(0.5);
        btnStatistiques.setOpacity(0);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(true);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculDistance(){


        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(false);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculStatistiques(){

        btnCarnetTrafic.setOpacity(0);
        btnCalculCoordonnees.setOpacity(0);
        btnStatistiques.setOpacity(0.5);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(false);
        paneCalculStatistiques.setVisible(true);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);
    }

    @FXML
    private void calculLocatorLocal(){

        double latitudeDegres;
        double latitudeMinutes;
        double latitudeSecondes;
        double longitudeDegres;
        double longitudeMinutes;
        double longitudeSecondes;
        String latitudeSituation;
        String longitudeSituation;

        try {

            latitudeDegres = Double.parseDouble(fldLatitudeDegres.getText());
            latitudeMinutes = Double.parseDouble(fldLatitudeMinutes.getText());
            latitudeSecondes = Double.parseDouble(fldLatitudeSecondes.getText());

            longitudeDegres = Double.parseDouble(fldLongitudeDegres.getText());
            longitudeMinutes = Double.parseDouble(fldLongitudeMinutes.getText());
            longitudeSecondes = Double.parseDouble(fldLongitudeSecondes.getText());

            labStatutSaisie.setText("");

            latitudeSituation = comboLatitude.getSelectionModel().getSelectedItem().toString();
            longitudeSituation = comboLongitude.getSelectionModel().getSelectedItem().toString();

            Locator locatorStation = new Locator(latitudeDegres, latitudeMinutes, latitudeSecondes, latitudeSituation,
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation);

            fldLocatorStation.setText(locatorStation.getLocator());

            Settings settings = new Settings();

            settings.setIndicatifStation(fldIndicatifStation.getText());
            settings.setLocatorStation(fldLocatorStation.getText());
            settings.setLocaliteStation(fldLocaliteStation.getText());

            settings.setLatitudeDegresStation(fldLatitudeDegres.getText());
            settings.setLatitudeMinutesStation(fldLatitudeMinutes.getText());
            settings.setLatitudeSecondesStation(fldLatitudeSecondes.getText());
            settings.setLatitudeSituationStation(latitudeSituation);

            settings.setLongitudeDegresStation(fldLongitudeDegres.getText());
            settings.setLongitudeMinutesStation(fldLongitudeMinutes.getText());
            settings.setLongitudeSecondesStation(fldLongitudeSecondes.getText());
            settings.setLongitudeSituationStation(longitudeSituation);

            settings.closeOutputSettings();

        }
        catch (Exception e){

            labStatutSaisie.setText("Erreur dans la saisie des coordonnées");
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void ajoutQso(){

        paneStation.setVisible(false);
        paneAjoutQso.setVisible(true);

        fldDateQso.setValue(LocalDate.now());
        fldIndicatifQso.setText("");
        fldDepartementQso.setText("");
        fldLocatorQso.setText("");
        comboBandeQso.getSelectionModel().clearSelection();
        comboModeQso.getSelectionModel().clearSelection();
        fldQthQso.setText("");
        fldRstEmission.setText("");
        fldRstReception.setText("");
        fldPrenomQso.setText("");
    }

    @FXML
    private void saveQso(){

        // Calcul de la distance
        ArrayList coordonnees = null;

        double latitudeDegres;
        double latitudeMinutes;
        double latitudeSecondes;
        double longitudeDegres;
        double longitudeMinutes;
        double longitudeSecondes;
        String latitudeSituation;
        String longitudeSituation;

        double latitudeDistDegres = 0;
        double latitudeDistMinutes = 0;
        double latitudeDistSecondes = 0;
        double longitudeDistDegres = 0;
        double longitudeDistMinutes = 0;
        double longitudeDistSecondes = 0;
        String latitudeDistSituation = "";
        String longitudeDistSituation = "";

        Database data = new Database();

        try {

            latitudeDegres = Double.parseDouble(fldLatitudeDegres.getText());
            latitudeMinutes = Double.parseDouble(fldLatitudeMinutes.getText());
            latitudeSecondes = Double.parseDouble(fldLatitudeSecondes.getText());

            longitudeDegres = Double.parseDouble(fldLongitudeDegres.getText());
            longitudeMinutes = Double.parseDouble(fldLongitudeMinutes.getText());
            longitudeSecondes = Double.parseDouble(fldLongitudeSecondes.getText());

            latitudeSituation = comboLatitude.getSelectionModel().getSelectedItem().toString();
            longitudeSituation = comboLongitude.getSelectionModel().getSelectedItem().toString();

            Locator calculDistance = new Locator(latitudeDegres, latitudeMinutes, latitudeSecondes, latitudeSituation,
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation);

            if (fldLocatorQso.getText().length() == 6){

                coordonnees = calculDistance.ConvertLocatorToCoordinates(fldLocatorQso.getText());

                latitudeDistDegres = Double.valueOf(coordonnees.get(0).toString());
                latitudeDistMinutes = Double.valueOf(coordonnees.get(1).toString());
                latitudeDistSecondes = Double.valueOf(coordonnees.get(2).toString());
                latitudeDistSituation = coordonnees.get(3).toString();

                longitudeDistDegres = Double.valueOf(coordonnees.get(4).toString());
                longitudeDistMinutes = Double.valueOf(coordonnees.get(5).toString());
                longitudeDistSecondes = Double.valueOf(coordonnees.get(6).toString());
                longitudeDistSituation = coordonnees.get(7).toString();

            }

            int idQso = data.getMaxId() + 1;
            String distance = "0";

            if (fldLocatorQso.getText().length() == 6){

                distance = calculDistance.getDistance(latitudeDistDegres, latitudeDistMinutes, latitudeDistSecondes, latitudeDistSituation,
                        longitudeDistDegres, longitudeDistMinutes, longitudeDistSecondes, longitudeDistSituation);

            }


            data.insertQso(idQso, fldDateQso.getValue(), fldIndicatifQso.getText(), fldDepartementQso.getText(), fldLocatorQso.getText(),
                    comboBandeQso.getSelectionModel().getSelectedItem().toString(), comboModeQso.getSelectionModel().getSelectedItem().toString(),
                    fldQthQso.getText(), fldRstReception.getText(), fldRstEmission.getText(), Integer.valueOf(distance));


        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
        data.closeDatabase();

        periodesStatistiques();
        refreshListeQso();
        calStatistiques();


        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void copyright(){

        Alert copyright = new Alert(Alert.AlertType.INFORMATION);
        copyright.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        copyright.setHeaderText("Licence");
        String texte = "Logiciel libre développé sous Licence MIT. \n";
        texte += "Copyright © Philippe Labbe - 12/2020 - phlabbe94@gmail.com \n \n";
        texte += "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files ";
        texte += "(the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, ";
        texte += "publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do ";
        texte += "so, subject to the following conditions: \n \n";
        texte += "The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. \n \n";
        texte += "The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of ";
        texte += "merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders X be ";
        texte += "liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in ";
        texte += "connection with the software or the use or other dealings in the Software. \n \n";
        texte += "Except as contained in this notice, the name of the Philippe Labbe shall not be used in advertising or otherwise to promote ";
        texte += "the sale, use or other dealings in this Software without prior written authorization from the Philippe Labbe.";

        copyright.setContentText(texte);

        ButtonType buttonOK = new ButtonType("OK");

        copyright.getButtonTypes().setAll(buttonOK);

        Optional<ButtonType> result = copyright.showAndWait();
    }

    @FXML
    private void deleteQso(){

        try {

            // Lecture du thème sélectionné
            ObservableList<QSO> listeQso = listeCarnetTrafic.getItems();
            int id = Integer.parseInt(listeQso.get(listeCarnetTrafic.getSelectionModel().getSelectedIndex()).getId());
            String dateQso = listeQso.get(listeCarnetTrafic.getSelectionModel().getSelectedIndex()).getStringDate();
            String indicatif = listeQso.get(listeCarnetTrafic.getSelectionModel().getSelectedIndex()).getIndicatif();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Suppression");
            alert.setContentText("QSO du " + dateQso + " avec " + indicatif + " ?");

            ButtonType buttonOui = new ButtonType("Oui");
            ButtonType buttonNon = new ButtonType("Non");

            alert.getButtonTypes().setAll(buttonOui, buttonNon);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonOui){

                // Connexion à la base de données
                Database database = new Database();
                database.deleteQso(id);
                database.closeDatabase();

                // Rafraichissement de la liste
                periodesStatistiques();
                refreshListeQso();
                calStatistiques();


            }



        }
        catch (Exception e){

            System.out.println(e.getMessage());
            refreshListeQso();

        }
    }

    @FXML
    private void calculLocator(){

        double latitudeDegres;
        double latitudeMinutes;
        double latitudeSecondes;
        double longitudeDegres;
        double longitudeMinutes;
        double longitudeSecondes;
        String latitudeSituation;
        String longitudeSituation;

        double latitudeLocDegres;
        double latitudeLocMinutes;
        double latitudeLocSecondes;
        double longitudeLocDegres;
        double longitudeLocMinutes;
        double longitudeLocSecondes;
        String latitudeLocSituation;
        String longitudeLocSituation;

        try {

            // Station distante
            latitudeDegres = Double.parseDouble(fldCalLatitudeDegres.getText());
            latitudeMinutes = Double.parseDouble(fldCalLatitudeMinutes.getText());
            latitudeSecondes = Double.parseDouble(fldCalLatitudeSecondes.getText());

            longitudeDegres = Double.parseDouble(fldCalLongitudeDegres.getText());
            longitudeMinutes = Double.parseDouble(fldCalLongitudeMinutes.getText());
            longitudeSecondes = Double.parseDouble(fldCalLongitudeSecondes.getText());


            latitudeSituation = comboCalLatitude.getSelectionModel().getSelectedItem().toString();
            longitudeSituation = comboCalLongitude.getSelectionModel().getSelectedItem().toString();

            Locator locatorStation = new Locator(latitudeDegres, latitudeMinutes, latitudeSecondes, latitudeSituation,
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation);

            fldCalLocator.setText(locatorStation.getLocator());

            // Station locale
            latitudeLocDegres = Double.parseDouble(fldLatitudeDegres.getText());
            latitudeLocMinutes = Double.parseDouble(fldLatitudeMinutes.getText());
            latitudeLocSecondes = Double.parseDouble(fldLatitudeSecondes.getText());

            longitudeLocDegres = Double.parseDouble(fldLongitudeDegres.getText());
            longitudeLocMinutes = Double.parseDouble(fldLongitudeMinutes.getText());
            longitudeLocSecondes = Double.parseDouble(fldLongitudeSecondes.getText());

            latitudeLocSituation = comboLatitude.getSelectionModel().getSelectedItem().toString();
            longitudeLocSituation = comboLongitude.getSelectionModel().getSelectedItem().toString();

            // Calcul distance
            Locator distanceStation = new Locator(latitudeLocDegres, latitudeLocMinutes, latitudeLocSecondes, latitudeLocSituation,
                    longitudeLocDegres, longitudeLocMinutes, longitudeLocSecondes, longitudeLocSituation);

            fldCalDistance.setText(distanceStation.getDistance(latitudeDegres, latitudeMinutes, latitudeSecondes, latitudeSituation,
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation));

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void razCalculLocator(){

        fldCalLatitudeDegres.setText("");
        fldCalLatitudeMinutes.setText("");
        fldCalLatitudeSecondes.setText("");

        fldCalLongitudeDegres.setText("");
        fldCalLongitudeMinutes.setText("");
        fldCalLongitudeSecondes.setText("");
        fldCalLocator.setText("");
        fldCalDistance.setText("");

    }


    @FXML
    private void calculDistanceLocator(){

        ArrayList coordonnees = null;

        double latitudeDegres;
        double latitudeMinutes;
        double latitudeSecondes;
        double longitudeDegres;
        double longitudeMinutes;
        double longitudeSecondes;
        String latitudeSituation;
        String longitudeSituation;

        double latitudeDistDegres;
        double latitudeDistMinutes;
        double latitudeDistSecondes;
        double longitudeDistDegres;
        double longitudeDistMinutes;
        double longitudeDistSecondes;
        String latitudeDistSituation;
        String longitudeDistSituation;

        try {

            latitudeDegres = Double.parseDouble(fldLatitudeDegres.getText());
            latitudeMinutes = Double.parseDouble(fldLatitudeMinutes.getText());
            latitudeSecondes = Double.parseDouble(fldLatitudeSecondes.getText());

            longitudeDegres = Double.parseDouble(fldLongitudeDegres.getText());
            longitudeMinutes = Double.parseDouble(fldLongitudeMinutes.getText());
            longitudeSecondes = Double.parseDouble(fldLongitudeSecondes.getText());

            latitudeSituation = comboLatitude.getSelectionModel().getSelectedItem().toString();
            longitudeSituation = comboLongitude.getSelectionModel().getSelectedItem().toString();

            Locator calculDistance = new Locator(latitudeDegres, latitudeMinutes, latitudeSecondes, latitudeSituation,
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation);

            coordonnees = calculDistance.ConvertLocatorToCoordinates(fldLocatorDistant.getText());

            latitudeDistDegres = Double.valueOf(coordonnees.get(0).toString());
            latitudeDistMinutes = Double.valueOf(coordonnees.get(1).toString());
            latitudeDistSecondes = Double.valueOf(coordonnees.get(2).toString());
            latitudeDistSituation = coordonnees.get(3).toString();

            longitudeDistDegres = Double.valueOf(coordonnees.get(4).toString());
            longitudeDistMinutes = Double.valueOf(coordonnees.get(5).toString());
            longitudeDistSecondes = Double.valueOf(coordonnees.get(6).toString());
            longitudeDistSituation = coordonnees.get(7).toString();

            String distance = calculDistance.getDistance(latitudeDistDegres, latitudeDistMinutes, latitudeDistSecondes, latitudeDistSituation,
                    longitudeDistDegres, longitudeDistMinutes, longitudeDistSecondes, longitudeDistSituation);

            fldDistanceLocator.setText(distance);


        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void setStatByDistance(){

        chart1.setVisible(true);
        chart2.setVisible(false);

    }

    @FXML
    private void setStatByMois(){

        chart1.setVisible(false);
        chart2.setVisible(true);

    }

    @FXML
    private void filterListeQso(){

        refreshListeQso();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialisation des combos coordonnées géographiques
        comboLatitude.getItems().add("Nord");
        comboLatitude.getItems().add("Sud");

        comboLongitude.getItems().add("Est");
        comboLongitude.getItems().add("Ouest");

        comboCalLatitude.getItems().add("Nord");
        comboCalLatitude.getItems().add("Sud");

        comboCalLongitude.getItems().add("Est");
        comboCalLongitude.getItems().add("Ouest");



        // Initialisation des combos QSO
        comboBandeQso.getItems().add("27 Mhz");
        comboBandeQso.getItems().add("446 Mhz");

        comboModeQso.getItems().add("DIG");
        comboModeQso.getItems().add("AM");
        comboModeQso.getItems().add("FM");
        comboModeQso.getItems().add("USB");
        comboModeQso.getItems().add("LSB");

        // Récupération des coordonnées de la station
        Settings settings = new Settings();
        fldIndicatifStation.setText(settings.getIndicatifStation());
        fldLocatorStation.setText(settings.getLocatorStation());
        fldLocaliteStation.setText(settings.getLocaliteStation());

        fldLatitudeDegres.setText(settings.getLatitudeDegresStation());
        fldLatitudeMinutes.setText(settings.getLatitudeMinutesStation());
        fldLatitudeSecondes.setText(settings.getLatitudeSecondesStation());
        comboLatitude.getSelectionModel().select(settings.getLatitudeSituationStation());

        fldLongitudeDegres.setText(settings.getLongitudeDegresStation());
        fldLongitudeMinutes.setText(settings.getLongitudeMinutesStation());
        fldLongitudeSecondes.setText(settings.getLongitudeSecondesStation());
        comboLongitude.getSelectionModel().select(settings.getLongitudeSituationStation());

        settings.closeInputSettings();

        // Rafraichissement de la liste
        periodesStatistiques();
        refreshListeQso();
        calStatistiques();


    }
    private void periodesStatistiques(){

        Database db = new Database();
        ArrayList listePeriodes = db.getListePeriodes();

        db.closeDatabase();

        comboStatDebut.getItems().clear();
        comboStatFin.getItems().clear();

        comboListeDebut.getItems().clear();
        comboListeFin.getItems().clear();

        for (int x = 0; x < listePeriodes.size(); x++){

            comboStatDebut.getItems().add(listePeriodes.get(x).toString());
            comboStatFin.getItems().add(listePeriodes.get(x).toString());

            comboListeDebut.getItems().add(listePeriodes.get(x).toString());
            comboListeFin.getItems().add(listePeriodes.get(x).toString());

        }

        try {

            comboStatDebut.getSelectionModel().select(0);
            comboStatFin.getSelectionModel().select(listePeriodes.size() - 1);

            comboListeDebut.getSelectionModel().select(0);
            comboListeFin.getSelectionModel().select(listePeriodes.size() - 1);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void calStatistiques(){

        try {

            // Calcul statistiques
            Database db = new Database();

            ArrayList statByTranche = db.getStatisticsByDistance(comboStatDebut.getSelectionModel().getSelectedItem().toString(),
                    comboStatFin.getSelectionModel().getSelectedItem().toString());

            int nbQso = db.getNbQso(comboStatDebut.getSelectionModel().getSelectedItem().toString(),
                    comboStatFin.getSelectionModel().getSelectedItem().toString());

            ArrayList statByDate = db.getStatisticsByDate(comboStatDebut.getSelectionModel().getSelectedItem().toString(),
                    comboStatFin.getSelectionModel().getSelectedItem().toString());

            db.closeDatabase();

            // Nombre total de QSO
            fldStatNbQso.setText(String.valueOf(nbQso));

            // Nombre de QSO par tranche de distance
            ArrayList tranche = new ArrayList();

            chart1.getData().clear();


            for (int i = 0; i < statByTranche.size(); i++){

                tranche = (ArrayList) statByTranche.get(i);
                String libTranche = (String) tranche.get(0);
                int nbQsoTranche = (int) tranche.get(1);

                PieChart.Data slice = new PieChart.Data(libTranche, nbQsoTranche);
                chart1.getData().add(slice);

            }
            chart1.setAnimated(false);
            chart1.setLegendVisible(true);
            chart1.setLabelsVisible(false);
            chart1.setLegendSide(Side.LEFT);
            chart1.setTitle("Nb de QSO par tranche de distance");
            chart1.setStartAngle(90);

            // Evolution mensuelle du nombre de QSO
            ArrayList date = new ArrayList();

            chart2.getData().clear();


            final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName("Evolution mensuelle du nb de QSO");
            chart2.setAnimated(false);


            for (int i = 0; i < statByDate.size(); i++){

                date = (ArrayList) statByDate.get(i);
                String libDate = (String) date.get(0);
                int nbQsoDate = (int) date.get(1);

                series1.getData().add(new XYChart.Data<>(libDate, nbQsoDate));

            }
            chart2.getData().add(series1);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }


    }

    private void refreshListeQso(){

        try {

            Database data = new Database();
            ArrayList locListeQso = new ArrayList();

            locListeQso = data.getListeQso(comboListeDebut.getSelectionModel().getSelectedItem().toString(),
                    comboListeFin.getSelectionModel().getSelectedItem().toString());
            data.closeDatabase();

            ArrayList qso = new ArrayList();
            ObservableList<QSO> qsoDatas = FXCollections.observableArrayList();

            for (int i = 0; i < locListeQso.size(); i++){

                qso = (ArrayList) locListeQso.get(i);

                int id = (int) qso.get(0);
                LocalDate date = (LocalDate) qso.get(1);
                String indicatif = (String) qso.get(2);
                String departement = (String) qso.get(3);
                String locator = (String) qso.get(4);
                String bande = (String) qso.get(5);
                String mode = (String) qso.get(6);
                String qth = (String) qso.get(7);
                String rst_r = (String) qso.get(8);
                String rst_e = (String) qso.get(9);
                int distance = (int) qso.get(10);

                QSO qso1 = new QSO(id, date, indicatif, departement, locator, bande, mode, qth, rst_r, rst_e, distance);

                qsoDatas.add(qso1);

            }

            listeCarnetTrafic.getItems().clear();
            listeCarnetTrafic.getItems().addAll(qsoDatas);

            // Insertion dans le tableau
            tableId.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getId());

            });

            tableDate.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getStringDate());

            });

            tableIndicatif.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getIndicatif());

            });

            tableDepartement.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getDepartement());

            });

            tableLocator.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getLocator());

            });

            tableBande.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getBande());

            });

            tableMode.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getMode());

            });

            tableQth.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getQth());

            });

            tableRst_r.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getRst_r());

            });

            tableRst_e.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getRst_e());

            });

            tableDistance.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getDistance());

            });

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }
}