package com.example.android.coach.modele;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable {

    private static final int minFemme = 15;
    private static final int maxFemme = 30;
    private static final int minHomme = 10;
    private static final int maxHomme = 25;

    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    public Profil(Date dateMesure,Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG(){
        float tailleM = ((float)taille/100);
       this.img = (float)((1.2*poids / (tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4);
        //this.img = (float)(poids/(tailleM*tailleM));
    }

    private void resultIMG(){
        int min, max;
        if(sexe==0){
            min = minFemme;
            max = maxFemme;
        }else{
            min = minHomme;
            max = maxHomme;
        }
        //message correspondant
        message = "normal";
        if(img<min){
            message = "trop faible";
        }else{
            if(img>max){
                message = "trop élévé";
            }
        }
    }

    /**
     * conversion du profil au formal JSONArray
     * @return
     */
    public JSONArray convertToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(dateMesure);
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);
        return new JSONArray(laListe);
    }

}
