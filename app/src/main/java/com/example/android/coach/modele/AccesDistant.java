package com.example.android.coach.modele;

import android.util.Log;

import com.example.android.coach.outils.AccesHTTP;
import com.example.android.coach.outils.AsyncResponse;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {

    //constante
    private static final  String SERVERADDR = "http://192.168.56.1/coach/serveurcoach.php";

    public  AccesDistant(){
        super();
    }
    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "**************" + output);
        //découpage du message reçu avec %
        String[] message = output.split("%");
        //dans message[0]: "enreg", "dernier", "erreur !"
        //dans message[1]: reste du message

        //s'il y a deux case
        if (message.length > 1) {
            if (message[0].equals("enreg")) {
                Log.d("enreg","************"+message[1]);
            } else {
                if (message[0].equals("dernier")) {
                    Log.d("dernier","************"+message[1]);
                } else {
                    if (message[0].equals("Erreur !")) {
                        Log.d("Erreur !","************"+message[1]);
                    }
                }

            }

            }
        }

        public void envoi(String operation, JSONArray lesDonneesJSON){
            /**
             *
             */
            AccesHTTP accesDonnees = new AccesHTTP();
            //lien de delegation
            accesDonnees.delegate = this;
            //ajout parametres
            accesDonnees.addParam("operation", operation);
            accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
            accesDonnees.execute(SERVERADDR);
        }

    }
