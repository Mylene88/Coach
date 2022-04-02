package com.example.android.coach.modele;

public class Profil {

    private static final int minFemme = 15;
    private static final int maxFemme = 30;
    private static final int minHomme = 10;
    private static final int maxHomme = 25;

    private int poids, taille, age, sexe;
    private float img;
    private String message;

    public Profil(int poids, int taille, int age, int sexe) {
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

    private void calculIMG(){
        float tailleM = ((float)taille/100);
        this.img = (float)((1.2*poids/(tailleM*tailleM))+(0.23*age)-(10.83*sexe)-5.4);
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
}
