package com.phillinux94.carnettrafic;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane selBtnCarnetTrafic;

    @FXML
    private Pane selBtnCalculLocator;

    @FXML
    private Pane selBtnStatistiques;

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
    private TableColumn<QSO, String> tablePrenom;

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
    private void setCarnetTrafic(){

        selBtnCarnetTrafic.setVisible(true);
        selBtnCalculLocator.setVisible(false);
        selBtnStatistiques.setVisible(false);

        paneCarnetTrafic.setVisible(true);
        paneCalculLocator.setVisible(false);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculLocator(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(true);
        selBtnStatistiques.setVisible(false);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(true);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculDistance(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(false);
        selBtnStatistiques.setVisible(false);

        paneCarnetTrafic.setVisible(false);
        paneCalculLocator.setVisible(false);
        paneCalculStatistiques.setVisible(false);
        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

    }

    @FXML
    private void setCalculStatistiques(){

        selBtnCarnetTrafic.setVisible(false);
        selBtnCalculLocator.setVisible(false);
        selBtnStatistiques.setVisible(true);

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

        Database data = new Database();
        int idQso = data.getMaxId() + 1;

        data.insertQso(idQso, fldDateQso.getValue(), fldIndicatifQso.getText(), fldDepartementQso.getText(), fldLocatorQso.getText(),
                comboBandeQso.getSelectionModel().getSelectedItem().toString(), comboModeQso.getSelectionModel().getSelectedItem().toString(),
                fldQthQso.getText(), fldRstReception.getText(), fldRstEmission.getText(), fldPrenomQso.getText());
        data.closeDatabase();

        refreshListeQso();

        paneStation.setVisible(true);
        paneAjoutQso.setVisible(false);

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
            alert.setHeaderText("Confirmation de suppression");
            alert.setContentText("Voulez-vous vraiment supprimer le QSO du " + dateQso + " avec " + indicatif + " ?");

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
                refreshListeQso();

            }



        }
        catch (Exception e){

            System.out.println(e.getMessage());

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
                    longitudeDegres, longitudeMinutes, longitudeSecondes, longitudeSituation) + " kms");

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

    private void refreshListeQso(){

        try {

            Database data = new Database();
            ArrayList locListeQso = new ArrayList();

            locListeQso = data.getListeQso();
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
                String prenom = (String) qso.get(10);

                QSO qso1 = new QSO(id, date, indicatif, departement, locator, bande, mode, qth, rst_r, rst_e, prenom);

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

            tablePrenom.setCellValueFactory(dataTable ->
            {

                return new ReadOnlyStringWrapper(dataTable.getValue().getPrenom());

            });

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
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
        refreshListeQso();
    }
}