package com.phillinux94.carnettrafic;

import java.time.LocalDate;

public class QSO {

    private int id;
    private LocalDate date;
    private String indicatif;
    private String departement;
    private String locator;
    private String bande;
    private String mode;
    private String qth;
    private String rst_r;
    private String rst_e;
    private int distance;

    QSO(){

    }

    QSO(int id, LocalDate date, String indicatif, String departement, String locator, String bande, String mode,
        String qth, String rst_r, String rst_e, int distance){

        this.id = id;
        this.date = date;
        this.indicatif = indicatif;
        this.departement = departement;
        this.locator = locator;
        this.bande = bande;
        this.mode = mode;
        this.qth = qth;
        this.rst_r = rst_r;
        this.rst_e = rst_e;
        this.distance = distance;

    }

    public String getId(){

        String localId = String.valueOf(this.id);

        return localId;

    }
    public LocalDate getDate(){

        return this.date;

    }
    public String getStringDate(){

        String localDate =  this.date.getDayOfMonth() + "/" +
                this.date.getMonthValue() + "/" +
                this.date.getYear();

        return localDate;

    }
    public String getIndicatif(){

        return this.indicatif;

    }
    public String getDepartement(){

        return this.departement;

    }
    public String getLocator(){

        return this.locator;

    }
    public String getBande(){

        return this.bande;

    }
    public String getMode(){

        return this.mode;

    }
    public String getQth(){

        return this.qth;

    }
    public String getRst_r(){

        return this.rst_r;

    }
    public String getRst_e(){

        return this.rst_e;

    }
    public String getDistance(){

        return String.valueOf(this.distance);

    }

    public void setId(int id){

        this.id = id;

    }
    public void setDate(LocalDate date){

        this.date = date;

    }
    public void setIndicatif(String indicatif){

        this.indicatif = indicatif;

    }
    public void setDepartement(String departement){

        this.departement = departement;

    }
    public void setLocator(String locator){

        this.locator = locator;

    }
    public void setBande(String bande){

        this.bande = bande;

    }
    public void setMode(String mode){

        this.mode = mode;

    }
    public void setQth(String qth){

        this.qth = qth;

    }
    public void setRst_r(String rst_r){

        this.rst_r = rst_r;

    }
    public void setRst_e(String rst_e){

        this.rst_e = rst_e;

    }
    public void setDistance(int distance){

        this.distance = distance;

    }

}