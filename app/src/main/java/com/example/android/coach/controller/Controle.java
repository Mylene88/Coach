package com.example.android.coach.controller;

import android.content.Context;

import com.example.android.coach.modele.AccesDistant;
import com.example.android.coach.modele.AccesLocal;
import com.example.android.coach.modele.Profil;
import com.example.android.coach.outils.Serializer;

import org.json.JSONArray;

import java.util.Date;

public final class Controle {

    //générer une instance de la classe controle
    private static Controle instance = null; //accessible directement par la classe
    private static Profil profil;
    private static String nomFic = "saveprofil";
    //private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;


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
    public static final Controle getInstance(Context contexte){
        if(Controle.instance == null){
            Controle.instance = new Controle();
       //     accesLocal = new AccesLocal(contexte);
            accesDistant = new AccesDistant();
        //    profil = accesLocal.recupDernier();
            accesDistant.envoi("dernier", new JSONArray());
           // recupSerialize(contexte);
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
    public void creerProfil(int poids, int taille, int age, int sexe, Context contexte){
        profil = new Profil(new Date(),poids, taille, age, sexe);
      //  accesLocal.ajout(profil);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
        //Serializer.serialize(nomFic, profil, contexte);
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

    /**
     * récupération de l'objet sérialisé le profil
     * @param contexte
     */
    private static void recupSerialize(Context contexte){
        profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        }else{
            return profil.getPoids();
        }
    }
    public Integer getTaille(){
        if(profil == null){
            return null;
        }else{
            return profil.getTaille();
        }
    }
    public Integer getSexe(){
        if(profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }
    public Integer getAge() {
        if(profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }
}
