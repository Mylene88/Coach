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
    }
    //propriétés
        private TextView txtPoids;
        private TextView txtTaille;
        private TextView txtAge;
        private EditText poidsValue;
        private EditText tailleValue;
        private EditText ageValue;
        private RadioButton rdHomme;
        private RadioButton rdFemme;
        private TextView ibIMG;
        private ImageView imgSmiley;
        private Controle controle;

    /**
     * Initialisation des liens avec les objets graphiques
     */
      private void init(){
      //  txtPoids = (TextView)findViewById(R.id.txtPoids);
        poidsValue = findViewById(R.id.editTextNumber);
       // txtTaille = (TextView) findViewById(R.id.txtTaille);
        tailleValue = findViewById(R.id.editTextNumber2);
      //  txtAge = (TextView) findViewById(R.id.txtAge);
        ageValue = findViewById(R.id.editTextNumber3);
        rdHomme =(RadioButton) findViewById(R.id.rdHomme);
        rdFemme =(RadioButton) findViewById(R.id.rdFemme);
        ibIMG = (TextView) findViewById(R.id.ibIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();
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
                  Integer poids = 0;
                  Integer taille = 0;
                  Integer age = 0;
                  Integer sexe = 0;
                  /**
                   * tout ce qui va être dans le try-catch est exécuté mais si jamais ça plante le programme
                   * ne s'arrête pas violement le programme lorsqu'il y a une erreur d'exécution
                   * dans le try il éxécute ce qu'il y a dans le catch donc {}
                   * Récupération des données saisies
                   */
                  try {
                      poids = Integer.parseInt(poidsValue.getText().toString());
                      taille = Integer.parseInt(tailleValue.getText().toString());
                      age = Integer.parseInt(ageValue.getText().toString());
                  }catch (Exception e){
                      Log.d("Erreur", e.getMessage());
                  };
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
        private void afficheResult(Integer poids,Integer taille,Integer age,Integer sexe){
            //création et récupération des informations
            this.controle.creerProfil(poids, taille,age, sexe, this);
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
            ibIMG.setText(String.format("%.01f",img)+" : IMG "+message);
        }

    /**
     * récupération du profil s'il a été serialisé
     */
    private void recupProfil(){
           if(controle.getPoids() != null){
               //txtPoids.setText(controle.getPoids().toString());
               poidsValue.setText(controle.getPoids().toString());
               //txtTaille.setText(controle.getTaille().toString());
               tailleValue.setText(controle.getTaille().toString());
             // txtAge.setText(controle.getAge().toString());
              ageValue.setText(controle.getAge().toString());
               rdFemme.setChecked(true);
               if(controle.getSexe()==1){
                   rdHomme.setChecked(true);
               }
               //simule le click sur le btn calcul
               ((Button)findViewById(R.id.btnCalc)).performClick();
           }
        }
}