package com.example.android.coach.controller;

import com.example.android.coach.modele.Profil;

public final class Controle {

    //générer une instance de la classe controle
    private static Controle instance = null; //accessible directement par la classe
    private Profil profil;

    /**
     * constructeur private
     */
    private Controle(){
        super();
    }
    /* de l'extérieur j'appelle get instance si l'instance n'existe pas encore elle est crée
    sinon il ne se passe rien et dans tous les cas je retourne l'instance.. DOnc je retourne soit la
    nouvelle instance crée ou je retourne l'instance existante
     */
    //créer une methode accessible de l'extérieur et va permettre de remplir cette instance une seule fois
    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfil(int poids, int taille, int age, int sexe){
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * récupération img de profil
     * @return img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * récupération message de profil
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }



}
