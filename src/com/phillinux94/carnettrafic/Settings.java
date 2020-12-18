package com.phillinux94.carnettrafic;

import java.io.*;
import java.util.Properties;

public class Settings {

    private Properties settings;
    private OutputStream output;
    private InputStream input;

    private String pathSettings;

    Settings(){

        try {

            File f = new File("Properties/Settings.properties");
            this.settings = new Properties();
            this.pathSettings = f.getPath();

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // --------------------  Ecriture - Indicatif de la Station ----------------------------------------
    public void setIndicatifStation(String indicatif){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("indicatifStation", indicatif);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // --------------------  Ecriture - Localité de la Station ----------------------------------------
    public void setLocaliteStation(String localite){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("localiteStation", localite);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // --------------------  Ecriture - Locator de la Station ----------------------------------------
    public void setLocatorStation(String locator){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("locatorStation", locator);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // -------------------- Ecriture - Coordonnées - Latitude ----------------------------------------
    public void setLatitudeDegresStation(String latitudeDegres){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("latitudeDegresStation", latitudeDegres);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLatitudeMinutesStation(String latitudeMinutes){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("latitudeMinutesStation", latitudeMinutes);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLatitudeSecondesStation(String latitudeSecondes){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("latitudeSecondesStation", latitudeSecondes);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLatitudeSituationStation(String latitudeSituation){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("latitudeSituationStation", latitudeSituation);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // -------------------- Ecriture - Coordonnées - Longitude ----------------------------------------
    public void setLongitudeDegresStation(String longitudeDegres){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("longitudeDegresStation", longitudeDegres);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLongitudeMinutesStation(String longitudeMinutes){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("longitudeMinutesStation", longitudeMinutes);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLongitudeSecondesStation(String longitudeSecondes){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("longitudeSecondesStation", longitudeSecondes);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void setLongitudeSituationStation(String longitudeSituation){

        try {

            this.output = new FileOutputStream(this.pathSettings);
            this.settings.setProperty("longitudeSituationStation", longitudeSituation);
            this.settings.store(this.output, null);

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    // --------------------  Lecture - Indicatif de la Station ----------------------------------------
    public String getIndicatifStation(){

        String indicatifStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            indicatifStation = this.settings.getProperty("indicatifStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return indicatifStation;
    }

    // --------------------  Lecture - Localité de la Station ----------------------------------------
    public String getLocaliteStation(){

        String localiteStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            localiteStation = this.settings.getProperty("localiteStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return localiteStation;
    }

    // --------------------  Lecture - Locator de la Station ----------------------------------------
    public String getLocatorStation(){

        String locatorStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            locatorStation = this.settings.getProperty("locatorStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return locatorStation;
    }

    // -------------------- Lecture - Coordonnées - Latitude ----------------------------------------
    public String getLatitudeDegresStation(){

        String latitudeDegresStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            latitudeDegresStation = this.settings.getProperty("latitudeDegresStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return latitudeDegresStation;
    }

    public String getLatitudeMinutesStation(){

        String latitudeMinutesStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            latitudeMinutesStation = this.settings.getProperty("latitudeMinutesStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return latitudeMinutesStation;
    }

    public String getLatitudeSecondesStation(){

        String latitudeSecondesStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            latitudeSecondesStation = this.settings.getProperty("latitudeSecondesStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return latitudeSecondesStation;
    }

    public String getLatitudeSituationStation(){

        String latitudeSituationStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            latitudeSituationStation = this.settings.getProperty("latitudeSituationStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return latitudeSituationStation;
    }

    // -------------------- Lecture - Coordonnées - Longitude ----------------------------------------
    public String getLongitudeDegresStation(){

        String longitudeDegresStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            longitudeDegresStation = this.settings.getProperty("longitudeDegresStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return longitudeDegresStation;
    }

    public String getLongitudeMinutesStation(){

        String longitudeMinutesStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            longitudeMinutesStation = this.settings.getProperty("longitudeMinutesStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return longitudeMinutesStation;
    }

    public String getLongitudeSecondesStation(){

        String longitudeSecondesStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            longitudeSecondesStation = this.settings.getProperty("longitudeSecondesStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return longitudeSecondesStation;
    }

    public String getLongitudeSituationStation(){

        String longitudeSituationStation = "";

        try {

            this.input = new FileInputStream(this.pathSettings);
            this.settings.load(this.input);
            longitudeSituationStation = this.settings.getProperty("longitudeSituationStation");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

        return longitudeSituationStation;
    }

    // ------------------------------ Fermeture des connexions ------------------------------------
    public void closeInputSettings(){

        try {

            this.input.close();

        }
        catch (Exception e){

        }

    }

    public void closeOutputSettings(){

        try {

            this.output.close();

        }
        catch (Exception e){

        }
    }
}
