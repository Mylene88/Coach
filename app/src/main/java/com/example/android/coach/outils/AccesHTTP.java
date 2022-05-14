package com.example.android.coach.outils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Connexion en tâche de fond dans un thread séparé
 */
public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametres;
    private  String ret = null;
    public AsyncResponse delegate = null; // c'est par delegation qu'on va exécuter une methode à l'exterieur du threads
    /**
     * Constructeur de cette classe
     * pour l'envoie de la requête
     */
    public AccesHTTP(){
        parametres = new ArrayList<NameValuePair>();
    }

    /**
     * methode qui va permettre d'ajouter des parametres ailleurs dans mon application
     * android et que avant de contacter le serveur distant il va falloir que j'ajoute des paramètres
     * ajout d'un paramètre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){
        parametres.add(new BasicNameValuePair(nom, valeur));
    }

    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]); // envoie des parametres en post
        HttpGet httpget = new HttpGet(strings[0]);
        HttpResponse response = null; // Executeit
        try {
            response = cnxHttp.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        try {
            //encodage des parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //connexion et envoie de parametres, attente des reponses
            response = cnxHttp.execute(paramCnx);
            //transformation de la reponse
            ret = EntityUtils.toString(response.getEntity()); //retour de l'information
        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage", "***************: "+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur protocole", "***************: "+e.toString());
        } catch (IOException e) { //impossible de se connecter au serveur
            Log.d("Erreur I/O", "***************: "+e.toString());
        }


        return null;
    }

    @Override
    protected void onPostExecute(Long result){

        delegate.processFinish((ret));
    }
//

}
