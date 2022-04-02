package com.example.android.coach.vue;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.coach.R;
import com.example.android.coach.controller.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /**
         * normalement quand on crée l'objet on fait new mais ici étant donné que
         * notre classe controle on a crée un constructeur privé donc on ne peut pas
         * faire un new controle par contre on fait un système de création d'instance qui
         * permet de gérer un singleton une instance unique donc la seule chose qu'on peut
         * appelé c'est getinstance() qui est une méthode statique
         */
        this.controle = Controle.getInstance();
    }
    //propriétés
        private TextView txtPoids;
        private TextView txtTaille;
        private TextView txtAge;
        private RadioButton rdHomme;
        private TextView ibIMG;
        private ImageView imgSmiley;
        private Controle controle;

    /**
     * Initialisation des liens avec les objets graphiques
     */
      private void init(){
        txtPoids = (TextView)findViewById(R.id.txtPoids);
        txtTaille = (TextView) findViewById(R.id.txtTaille);
        txtAge = (TextView) findViewById(R.id.txtAge);
        rdHomme =(RadioButton) findViewById(R.id.rdHomme);
        ibIMG = (TextView) findViewById(R.id.ibIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        ecouteCalcul();
      }

    /**
     * écoute événement sur le button calcul
     * ecouter le clic sur le bouton calcul
     * setOnClickListener permet d'initialiser un ecouteur sur le btn calcul
     * setOnClickListener est une méthode qui attend donc une instance de ButtonOnClickListener
     * Du coup on crée un nouvel objet on fait un new Button.OnClickListener et à l'intérieur on a
     * besoin de rédéfinir une méthode parceque le OnClick impléménte une interface qui a besoin de rédéfinir
     * la méthode OnClick donc plutôt que de créer une autre classe on rédéfini à la volé la méthode OnClick et tous ce qu'on
     * mettra dans cette méthode va s'exécuter lors du clique sur le btn calcul
     *
     */
      private  void ecouteCalcul(){
          ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
              public void onClick(View v){
                  //Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
                 // Log.d("message", "clic ok sur le bouton calcul ***************");
                  int poids = 0;
                  int taille = 0;
                  int age = 0;
                  int sexe = 0;
                  /**
                   * tout ce qui va être dans le try-catch est exécuté mais si jamais ça plante le programme
                   * ne s'arrête pas violement le programme lorsqu'il y a une erreur d'exécution
                   * dans le try il éxécute ce qu'il y a dans le catch donc {}
                   * Récupération des données saisies
                   */
                  try {
                      poids = Integer.parseInt(txtPoids.getText().toString());
                      taille = Integer.parseInt(txtTaille.getText().toString());
                      age = Integer.parseInt(txtAge.getText().toString());
                  }catch (Exception e){};
                  if(rdHomme.isChecked()){
                      sexe = 1;
                  }
                  /**
                   * Controle des données saisies
                   */
                  if(poids==0 || taille==0 || age==0){
                      Toast.makeText(MainActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                  }else{
                      afficheResult(poids, taille, age, sexe);
                  }
              }
          });
      }

    /**
     * affichage de L'IMG, du messgae et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
        private void afficheResult(int poids,int taille,int age,int sexe){
            //création et récupération des informations
            this.controle.creerProfil(poids, taille,age, sexe);
            float img = this.controle.getImg();
            String message = this.controle.getMessage();
            //affichage
            if(message =="normale"){
                imgSmiley.setImageResource(R.drawable.normal);
                ibIMG.setTextColor(Color.GREEN);
            }else{
                ibIMG.setTextColor(Color.RED);
                if(message == "trop faible"){
                    imgSmiley.setImageResource(R.drawable.maigre);
                }else{
                    imgSmiley.setImageResource(R.drawable.grasse);
                }
            }
            ibIMG.setText(String.format("#,01f",img)+" : IMG "+message);
        }
}