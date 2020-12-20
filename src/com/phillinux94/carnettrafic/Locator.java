package com.phillinux94.carnettrafic;

import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Hashtable;


public class Locator {


    private String latitudePosition;
    private String longitudePosition;

    private double latitudeDecimal;
    private double longitudeDecimal;

    private double latitudeRadians;
    private double longitudeRadians;

    Locator(){

    }


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

    public ArrayList ConvertLocatorToCoordinates(String locator){

        // Longitude
        Hashtable<Character, Integer> longitude1 = new Hashtable<Character, Integer>();
        longitude1.put('A', 180);
        longitude1.put('B', 160);
        longitude1.put('C', 140);
        longitude1.put('D', 120);
        longitude1.put('E', 100);
        longitude1.put('F', 80);
        longitude1.put('G', 60);
        longitude1.put('H', 40);
        longitude1.put('I', 20);
        longitude1.put('J', 0);
        longitude1.put('K', 20);
        longitude1.put('L', 40);
        longitude1.put('M', 60);
        longitude1.put('N', 80);
        longitude1.put('O', 100);
        longitude1.put('P', 120);
        longitude1.put('Q', 140);
        longitude1.put('R', 160);

        Hashtable<Character, Integer> longitude2 = new Hashtable<Character, Integer>();
        longitude2.put('0', 0);
        longitude2.put('1', 2);
        longitude2.put('2', 4);
        longitude2.put('3', 6);
        longitude2.put('4', 8);
        longitude2.put('5', 10);
        longitude2.put('6', 12);
        longitude2.put('7', 14);
        longitude2.put('8', 16);
        longitude2.put('9', 18);

        Hashtable<Character, Integer> longitude3 = new Hashtable<Character, Integer>();
        longitude3.put('A', 0);
        longitude3.put('B', 0);
        longitude3.put('C', 0);
        longitude3.put('D', 0);
        longitude3.put('E', 0);
        longitude3.put('F', 0);
        longitude3.put('G', 0);
        longitude3.put('H', 0);
        longitude3.put('I', 0);
        longitude3.put('J', 0);
        longitude3.put('K', 0);
        longitude3.put('L', 0);
        longitude3.put('M', 1);
        longitude3.put('N', 1);
        longitude3.put('O', 1);
        longitude3.put('P', 1);
        longitude3.put('Q', 1);
        longitude3.put('R', 1);
        longitude3.put('S', 1);
        longitude3.put('T', 1);
        longitude3.put('U', 1);
        longitude3.put('V', 1);
        longitude3.put('W', 1);
        longitude3.put('X', 1);

        Hashtable<Character, Integer> longitude4 = new Hashtable<Character, Integer>();
        longitude4.put('A', 0);
        longitude4.put('B', 5);
        longitude4.put('C', 10);
        longitude4.put('D', 15);
        longitude4.put('E', 20);
        longitude4.put('F', 25);
        longitude4.put('G', 30);
        longitude4.put('H', 35);
        longitude4.put('I', 40);
        longitude4.put('J', 45);
        longitude4.put('K', 50);
        longitude4.put('L', 55);
        longitude4.put('M', 0);
        longitude4.put('N', 5);
        longitude4.put('O', 10);
        longitude4.put('P', 15);
        longitude4.put('Q', 20);
        longitude4.put('R', 25);
        longitude4.put('S', 30);
        longitude4.put('T', 35);
        longitude4.put('U', 40);
        longitude4.put('V', 45);
        longitude4.put('W', 50);
        longitude4.put('X', 55);

        // Latitude
        Hashtable<Character, Integer> latitude1 = new Hashtable<Character, Integer>();
        latitude1.put('A', 90);
        latitude1.put('B', 80);
        latitude1.put('C', 70);
        latitude1.put('D', 60);
        latitude1.put('E', 50);
        latitude1.put('F', 40);
        latitude1.put('G', 30);
        latitude1.put('H', 20);
        latitude1.put('I', 10);
        latitude1.put('J', 0);
        latitude1.put('K', 10);
        latitude1.put('L', 20);
        latitude1.put('M', 30);
        latitude1.put('N', 40);
        latitude1.put('O', 50);
        latitude1.put('P', 60);
        latitude1.put('Q', 70);
        latitude1.put('R', 80);

        Hashtable<Character, Integer> latitude2 = new Hashtable<Character, Integer>();
        latitude2.put('0', 0);
        latitude2.put('1', 1);
        latitude2.put('2', 2);
        latitude2.put('3', 3);
        latitude2.put('4', 4);
        latitude2.put('5', 5);
        latitude2.put('6', 6);
        latitude2.put('7', 7);
        latitude2.put('8', 8);
        latitude2.put('9', 9);

        Hashtable<Character, Integer> latitude3 = new Hashtable<Character, Integer>();
        latitude3.put('A', 0);
        latitude3.put('B', 2);
        latitude3.put('C', 5);
        latitude3.put('D', 7);
        latitude3.put('E', 10);
        latitude3.put('F', 12);
        latitude3.put('G', 15);
        latitude3.put('H', 17);
        latitude3.put('I', 20);
        latitude3.put('J', 22);
        latitude3.put('K', 25);
        latitude3.put('L', 27);
        latitude3.put('M', 30);
        latitude3.put('N', 32);
        latitude3.put('O', 35);
        latitude3.put('P', 37);
        latitude3.put('Q', 40);
        latitude3.put('R', 42);
        latitude3.put('S', 45);
        latitude3.put('T', 47);
        latitude3.put('U', 50);
        latitude3.put('V', 52);
        latitude3.put('W', 55);
        latitude3.put('X', 57);

        Hashtable<Character, Integer> latitude4 = new Hashtable<Character, Integer>();
        latitude4.put('A', 0);
        latitude4.put('B', 30);
        latitude4.put('C', 0);
        latitude4.put('D', 30);
        latitude4.put('E', 0);
        latitude4.put('F', 30);
        latitude4.put('G', 0);
        latitude4.put('H', 30);
        latitude4.put('I', 0);
        latitude4.put('J', 30);
        latitude4.put('K', 0);
        latitude4.put('L', 30);
        latitude4.put('M', 0);
        latitude4.put('N', 30);
        latitude4.put('O', 0);
        latitude4.put('P', 30);
        latitude4.put('Q', 0);
        latitude4.put('R', 30);
        latitude4.put('S', 0);
        latitude4.put('T', 30);
        latitude4.put('U', 0);
        latitude4.put('V', 30);
        latitude4.put('W', 0);
        latitude4.put('X', 30);


        locator = locator.toUpperCase();

        ArrayList coordinates = new ArrayList();

        char c1 = locator.charAt(0);
        char c2 = locator.charAt(1);
        char c3 = locator.charAt(2);
        char c4 = locator.charAt(3);
        char c5 = locator.charAt(4);
        char c6 = locator.charAt(5);

        // Traitement de la longitude
        int longitudeDegres = 0;
        int longitudeMinutes = 0;
        int longitudeSecondes = 0;

        if (EstOuest(c1) == "Est"){

            longitudeDegres = longitude1.get(c1) + longitude2.get(c3) + longitude3.get(c5);
            longitudeMinutes = longitude4.get(c5);
            longitudeSecondes = 0;

        }
        else {
            longitudeDegres = longitude1.get(c1) - longitude2.get(c3) - 2 - longitude3.get(c5);
            longitudeMinutes = longitude4.get(c5);
            longitudeSecondes = 0;
        }


        // Traitement de la latitude
        int latitudeDegres = 0;
        int latitudeMinutes = 0;
        int latitudeSecondes = 0;

        if (NordSud(c2) == "Nord"){

            latitudeDegres = latitude1.get(c2) + latitude2.get(c4);
            latitudeMinutes = latitude3.get(c6);
            latitudeSecondes = latitude4.get(c6);

        }
        else {

            latitudeDegres = latitude1.get(c2) - latitude2.get(c4) - 1;
            latitudeMinutes = 60 - latitude3.get(c6);
            latitudeSecondes = latitude4.get(c6);

        }


        coordinates.add(latitudeDegres);
        coordinates.add(latitudeMinutes);
        coordinates.add(latitudeSecondes);
        coordinates.add(NordSud(c2));

        coordinates.add(longitudeDegres);
        coordinates.add(longitudeMinutes);
        coordinates.add(longitudeSecondes);
        coordinates.add(EstOuest(c1));

        return coordinates;

    }

    private String EstOuest(char x){

        String situation = "";

        if (x == 'A' | x == 'B' | x == 'C' | x == 'D' | x == 'E' | x == 'F' | x == 'G' | x == 'H' | x == 'I'){

            situation = "Ouest";

        }
        else {

            situation = "Est";

        }
        return situation;
    }

    private String NordSud(char x){

        String situation = "";

        if (x == 'A' | x == 'B' | x == 'C' | x == 'D' | x == 'E' | x == 'F' | x == 'G' | x == 'H' | x == 'I'){

            situation = "Sud";

        }
        else {

            situation = "Nord";

        }
        return situation;
    }
}
