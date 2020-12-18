package com.phillinux94.carnettrafic;

import java.lang.Math.*;


public class Locator {


    private String latitudePosition;
    private String longitudePosition;

    private double latitudeDecimal;
    private double longitudeDecimal;

    private double latitudeRadians;
    private double longitudeRadians;


    Locator(double latitudeDegres, double latitudeMinutes, double latitudeSecondes, String latitudePosition,
            double longitudeDegres, double longitudeMinutes, double longitudeSecondes, String longitudePosition){

        this.latitudePosition = latitudePosition;
        this.longitudePosition = longitudePosition;

        this.latitudeDecimal = latitudeDegres + (latitudeMinutes / 60) + (latitudeSecondes / 3600);
        this.longitudeDecimal = longitudeDegres + (longitudeMinutes / 60) + (longitudeSecondes / 3600);

        if (latitudePosition == "Nord"){

            this.latitudeRadians = latitudeDecimal / 57.29577951;

        }
        else {

            this.latitudeRadians = 0 - (latitudeDecimal / 57.29577951);

        }

        if (longitudePosition == "Ouest"){

            this.longitudeRadians = longitudeDecimal / 57.29577951;

        }
        else {

            this.longitudeRadians = 0 - (longitudeDecimal / 57.29577951);

        }
    }

    public String getLocator(){

        String locator = "";
        double Lo = 0;
        double La = 0;
        int L1 = 0;
        int L2 = 0;
        int L3 = 0;
        int L4 = 0;
        int L5 = 0;
        int L6 = 0;

        if (this.longitudePosition == "Ouest"){

            Lo = (180 - longitudeDecimal) / 20;

        }
        else {
            Lo = (180 + longitudeDecimal) / 20;
        }

        if (this.latitudePosition == "Sud"){

            La = (90 - latitudeDecimal) / 10;

        }
        else {

            La = (90 + latitudeDecimal) / 10;

        }

        L1 = (int) Lo;
        L2 = (int) La;

        L3 = (int) ((Lo - L1) * 10);
        L4 = (int) ((La - L2) * 10);

        L5 = (int) ((((Lo - L1) * 10) - L3) * 24);
        L6 = (int) ((((La - L2) * 10) - L4) * 24) ;

        locator = Character.toString((char) (L1 + 65));
        locator += Character.toString((char) (L2 + 65));

        locator += L3;
        locator += L4;

        locator += Character.toString((char) (L5 + 65));
        locator += Character.toString((char) (L6 + 65));
        
        return locator;
    }

    public String getDistance(double latitudeLocDegres, double latitudeLocMinutes, double latitudeLocSecondes, String latitudeLocPosition,
                           double longitudeLocDegres, double longitudeLocMinutes, double longitudeLocSecondes, String longitudeLocPosition){

        int distance = 0;

        double latitudeLocDecimal = latitudeLocDegres + (latitudeLocMinutes / 60) + (latitudeLocSecondes / 3600);
        double longitudeLocDecimal = longitudeLocDegres + (longitudeLocMinutes / 60) + (longitudeLocSecondes / 3600);
        double latitudeLocRadians = 0;
        double longitudeLocRadians = 0;

        if (latitudeLocPosition == "Nord"){

            latitudeLocRadians = latitudeLocDecimal / 57.29577951;

        }
        else {

            latitudeLocRadians = 0 - (latitudeLocDecimal / 57.29577951);

        }

        if (longitudeLocPosition == "Ouest"){

            longitudeLocRadians = longitudeLocDecimal / 57.29577951;

        }
        else {

            longitudeLocRadians = 0 - (longitudeLocDecimal / 57.29577951);

        }

        distance = (int) (6378 * Math.acos(Math.sin(this.latitudeRadians) * Math.sin(latitudeLocRadians) +
                Math.cos(this.latitudeRadians) * Math.cos(latitudeLocRadians) * Math.cos(longitudeLocRadians - this.longitudeRadians)));

        return String.valueOf(distance);
    }
}
